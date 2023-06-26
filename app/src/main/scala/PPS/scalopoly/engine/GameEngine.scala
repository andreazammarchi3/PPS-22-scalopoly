package PPS.scalopoly.engine

import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils

/** Object that represents the game engine.
  */
object GameEngine:

  val MIN_PLAYERS = 2
  private val MAX_PLAYERS = 6

  private val diceManager: DiceManager = DiceManager()

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

  /** Moves the current player.
    * @return
    *   the result of the dice roll.
    */
  def moveCurrentPlayer(): (Int, Int) =
    val (dice1, dice2) = diceManager.roll()
    Game.players = Game.players.updated(
      Game.currentPlayer,
      currentPlayer.move(dice1 + dice2)
    )
    (dice1, dice2)

  /** Removes the current player from the game, if there is only one player left
    * the game ends.
    */
  def currentPlayerQuit(): Unit =
    val playerToDelete = currentPlayer
    endTurn()
    val nextPlayer = currentPlayer
    Game.removePlayer(playerToDelete)
    Game.currentPlayer = Game.players.indexOf(nextPlayer)
    if EndgameLogicEngine.checkVictoryForSurrender(Game.players) then
      Game.winner = Some(Game.players.head)
      EndgameLogicEngine.showVictory()

  /** Returns the name of the space where the player is.
    * @param player
    *   the player.
    * @return
    *   the name of the space where the player is.
    */
  def getSpaceNameFromPlayerPosition(player: Player): SpaceName =
    GameBoard.gameBoardList(player.actualPosition)
