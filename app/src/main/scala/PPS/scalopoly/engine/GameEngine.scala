package PPS.scalopoly.engine

import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils
import PPS.scalopoly.engine.Game
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import PPS.scalopoly.model.space.purchasable.{BuildableSpace, PurchasableSpace}

/** Game engine that manages the [[Game]] and offers methods to interact with it.
  */
object GameEngine:

  val MIN_PLAYERS = 2
  private val MAX_PLAYERS = 6

  def botIsPlaying: Boolean = Game.botIsPlaying

  def gameBoard: GameBoard = Game.gameBoard

  /** Returns the list of players.
    * @return
    *   the list of players.
    */
  def players: List[Player] = Game.players

  /** Returns the current player.
    * @return
    *   the current player.
    */
  def currentPlayer: Player = players(Game.currentPlayer)

  /** Returns the list of available tokens.
    * @return
    *   the list of available tokens.
    */
  def availableTokens: List[Token] =
    Game.availableTokens

  /** Returns the winner of the game.
    * @return
    *   the winner of the game.
    */
  def winner: Option[Player] = Game.winner

  /** Adds a player to the game.
    * @param player
    *   the player to add.
    */
  def addPlayer(player: Player): Unit =
    Game.addPlayer(player)

  /** Removes a player from the game.
    * @param player
    *   the player to remove.
    */
  def removePlayer(player: Player): Unit =
    Game.removePlayer(player)

  /** Check if the game can start, at least 2 players are needed.
    * @return
    *   true if the game can start, false otherwise.
    */
  def canStartGame: Boolean =
    Game.players.length match
      case i if i < MIN_PLAYERS => false
      case _                    => true

  /** Check if a player can be added to the game, at most 6 players are allowed.
    * @return
    *   true if a player can be added, false otherwise.
    */
  def canAddPlayer: Boolean =
    Game.players.length match
      case i if i < MAX_PLAYERS => true
      case _                    => false

  /** Starts the game and shuffles the players.
    */
  def startGame(): Unit =
    Game.players = GameUtils.shufflePlayers(Game.players)
    if currentPlayer.isBot then botPlays()

  /** Resets the game.
    */
  def newGame(): Unit =
    Game.reset()

  /** Exits the game.
    */
  def exitGame(): Unit =
    sys.exit(0)

  /** Ends the turn of the current player.
    */
  def endTurn(): Unit =
    Game.currentPlayer = (Game.currentPlayer + 1) % Game.players.length
    if currentPlayer.isBot then botPlays()

  /** Moves the current player.
    * @param steps
    *   the number of steps to move.
    */
  def moveCurrentPlayer(steps: Int): Unit =
    EngineUtils.updatePlayerWith(Game.currentPlayer, currentPlayer.move(steps))

  /** Removes the current player from the game, if there is only one player left the game ends.
    */
  def currentPlayerQuit(): Unit =
    val playerToDelete = currentPlayer
    endTurn()
    val nextPlayer = currentPlayer
    Game.removePlayer(playerToDelete)
    Game.currentPlayer = Game.players.indexOf(nextPlayer)

  /** Check the status of the space where the current player is.
    * @return
    *   the status of the space where the current player is.
    */
  def checkSpaceStatus: SpaceStatus =
    val purchasableSpace = GameUtils.getPurchasableSpaceFromPlayerPosition(currentPlayer)
    purchasableSpace match
      case Some(purchasableSpace) => checkPropertyStatus(purchasableSpace)
      case _                      => SpaceStatus.NOT_PURCHASABLE

  private def checkPropertyStatus(purchasableSpace: PurchasableSpace): SpaceStatus = purchasableSpace match
    case purchasableSpace if GameUtils.propertyIsAlreadyOwned(purchasableSpace) =>
      purchasableSpace match
        case _ if currentPlayer.owns(purchasableSpace) => SpaceStatus.OWNED_BY_CURRENT_PLAYER
        case _                                         => SpaceStatus.OWNED_BY_ANOTHER_PLAYER
    case _ => SpaceStatus.PURCHASABLE

  private def botPlays(): Unit =
    Game.botIsPlaying = true
    BotEngine.play()
    Game.botIsPlaying = false
