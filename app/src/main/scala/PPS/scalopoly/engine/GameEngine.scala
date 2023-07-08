package PPS.scalopoly.engine

import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils
import PPS.scalopoly.engine.Game
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import PPS.scalopoly.model.space.purchasable.{BuildableSpace, PurchasableSpace}

/** Game engine that manages the [[Game]] and offers methods to interact with it.
  */
object GameEngine:

  /** Adds a player to the game.
    * @param player
    *   the player to add.
    */
  def addPlayer(player: Player): Unit =
    Game addPlayer player

  /** Removes a player from the game.
    * @param player
    *   the player to remove.
    */
  def removePlayer(player: Player): Unit =
    Game removePlayer player

  /** Resets the game.
    */
  def newGame(): Unit =
    Game.reset()

  /** Starts the game and shuffles the players.
    */
  def startGame(): Unit =
    Game.players = GameUtils shufflePlayers Game.players

  /** Exits the game.
    */
  def exitGame(): Unit =
    sys.exit(0)

  /** Ends the turn of the current player.
    */
  def endTurn(): Unit =
    Game.currentPlayer = (Game.currentPlayer + 1) % Game.players.length
    if GameReader.currentPlayer.isBot then botPlays()

  /** Moves the current player.
    * @param steps
    *   the number of steps to move.
    */
  def moveCurrentPlayer(steps: Int): Unit =
    EngineUtils.updatePlayerWith(Game.currentPlayer, GameReader.currentPlayer.move(steps))

  /** Removes the current player from the game, if there is only one player left the game ends.
    */
  def currentPlayerQuit(): Unit =
    val playerToDelete = GameReader.currentPlayer
    endTurn()
    val nextPlayer = GameReader.currentPlayer
    Game removePlayer playerToDelete
    Game.currentPlayer = Game.players.indexOf(nextPlayer)

  /** Check the status of the space where the current player is.
    * @return
    *   the status of the space where the current player is.
    */
  def checkSpaceStatus: SpaceStatus =
    val purchasableSpace = GameUtils getPurchasableSpaceFromPlayerPosition GameReader.currentPlayer
    purchasableSpace match
      case Some(purchasableSpace) => checkPropertyStatus(purchasableSpace)
      case _                      => SpaceStatus.NOT_PURCHASABLE

  /** The current bot plays.
    */
  def botPlays(): Unit =
    Game.botIsPlaying = true
    BotEngine.play()
    Game.botIsPlaying = false

  private def checkPropertyStatus(purchasableSpace: PurchasableSpace): SpaceStatus = purchasableSpace match
    case purchasableSpace if GameUtils propertyIsAlreadyOwned purchasableSpace =>
      purchasableSpace match
        case _ if GameReader.currentPlayer owns purchasableSpace => SpaceStatus.OWNED_BY_CURRENT_PLAYER
        case _                                                   => SpaceStatus.OWNED_BY_ANOTHER_PLAYER
    case _ => SpaceStatus.PURCHASABLE
