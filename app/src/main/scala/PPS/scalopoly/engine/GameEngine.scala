package PPS.scalopoly.engine

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils

import scala.util.Random

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
    checkRealEstateForPlayer(currentPlayer)
    (dice1, dice2)

  /** Removes the current player from the game, if there is only one player left
    * the game ends.
    */
  def currentPlayerQuit(): Unit =
    val playerToDelete = currentPlayer
    endTurn()
    val nextPlayer = currentPlayer
    Game.players.length match
      case players if players == MIN_PLAYERS - 1 => exitGame()
      case _ =>
        Game.removePlayer(playerToDelete)
        Game.currentPlayer = Game.players.indexOf(nextPlayer)

  /** Returns the name of the space where the player is.
    * @param player
    *   the player.
    * @return
    *   the name of the space where the player is.
    */
  def getSpaceNameFromPlayerPosition(player: Player): SpaceName =
    GameBoard.gameBoardList(player.actualPosition)

  private def checkRealEstateForPlayer(player: Player): Unit =
    Game.getRealEstateBySpaceName(getSpaceNameFromPlayerPosition(player)) match
      case realEstate: RealEstate =>
        realEstate.owner match
          case Some(owner) =>
            if !owner.eq(player) then playerPaysRent(player, realEstate)
          case None => playerBuysRealEstate(player, realEstate)

  private def playerBuysRealEstate(
      player: Player,
      realEstate: RealEstate
  ): Unit =
    realEstate.isBoughtBy(player)
    // TODO: trace in UI
    Console.println(s"Player ${player.nickname} bought ${realEstate.spaceName}")

  private def playerPaysRent(player: Player, realEstate: RealEstate): Unit =
    realEstate.calculateRent()
    // TODO: remove money from player / trace in UI
    Console.println(
      s"Player ${player.nickname} pays ${realEstate.calculateRent()} rent to ${realEstate.owner}"
    )

  protected[engine] object Game:

    private val DEFAULT_CURRENT_PLAYER: Int = 0

    private var _currentPlayer: Int = DEFAULT_CURRENT_PLAYER
    private var _players: List[Player] = List.empty
    private var _winner: Option[Player] = None
    private var _availableTokens: List[Token] = Token.values.toList
    private var _realEstatesBySpaceName: Map[SpaceName, RealEstate] =
      SpaceName.values.map(x => (x, RealEstate(x))).toMap

    /** Returns the current player.
     *
     * @return
     *   the current player.
     */
    def currentPlayer: Int = _currentPlayer

    /** Sets the current player.
     * @param value
     *   the new current player position in the players list.
     */
    def currentPlayer_=(value: Int): Unit =
      _currentPlayer = value

    /** Returns the list of players.
     * @return
     *   the list of players.
     */
    def players: List[Player] = _players

    /** Sets the list of players.
     * @param value
     *   the new list of players.
     */
    def players_=(value: List[Player]): Unit =
      _players = value

    /** Returns the winner of the game.
     * @return
     *   the winner of the game.
     */
    def winner: Option[Player] = _winner

    /** Sets the winner of the game.
     * @param value
     *   the new winner of the game.
     */
    def winner_=(value: Option[Player]): Unit =
      _winner = value

    /** Returns the list of available tokens.
     * @return
     *   the list of available tokens.
     */
    def availableTokens: List[Token] = _availableTokens

    /** Sets the list of available tokens.
     * @param value
     *   the new list of available tokens.
     */
    def availableTokens_=(value: List[Token]): Unit =
      _availableTokens = value

    /** Adds a player to the game and removes the token from the list of available
     * tokens.
     * @param player
     *   the player to add.
     */
    def addPlayer(player: Player): Unit =
      players = player :: players
      availableTokens = availableTokens.filter(_ != player.token)

    /** Removes a player from the game and adds the token to the list of available
     * tokens.
     * @param player
     *   the player to remove.
     */
    def removePlayer(player: Player): Unit =
      availableTokens = player.token :: availableTokens
      players = players.filter(_ != player)

    /** Resets the game.
     */
    def reset(): Unit =
      currentPlayer = DEFAULT_CURRENT_PLAYER
      players = List.empty
      availableTokens = Token.values.toList
      winner = None

    /** Get Real estate by space name
     *
     * @param spaceName
     * @return
     * the real estate of the given Space name
     */
    def getRealEstateBySpaceName(spaceName: SpaceName): RealEstate =
      _realEstatesBySpaceName(spaceName)
