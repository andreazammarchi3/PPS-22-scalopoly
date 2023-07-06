package PPS.scalopoly.engine

import PPS.scalopoly.model.{GameBoard, Player, Token}

/** Game object that contains the current player, the list of players, the winner and the list of available tokens.
  */
protected[engine] object Game:

  private val DEFAULT_CURRENT_PLAYER: Int = 0

  private var _currentPlayer: Int = DEFAULT_CURRENT_PLAYER
  private var _players: List[Player] = List.empty
  private var _winner: Option[Player] = None
  private var _availableTokens: List[Token] = Token.values.toList
  private var _gameBoard = GameBoard()

  def gameBoard: GameBoard = _gameBoard

  def gameBoard_=(value: GameBoard): Unit =
    _gameBoard = value

  /** Returns the current player.
    *
    * @return
    *   the current player.
    */
  def currentPlayer: Int = _currentPlayer

  /** Sets the current player.
    *
    * @param value
    *   the new current player position in the players list.
    */
  def currentPlayer_=(value: Int): Unit =
    _currentPlayer = value

  /** Returns the list of players.
    *
    * @return
    *   the list of players.
    */
  def players: List[Player] = _players

  /** Sets the list of players.
    *
    * @param value
    *   the new list of players.
    */
  def players_=(value: List[Player]): Unit =
    _players = value

  /** Returns the winner of the game.
    *
    * @return
    *   the winner of the game.
    */
  def winner: Option[Player] = _winner

  /** Sets the winner of the game.
    *
    * @param value
    *   the new winner of the game.
    */
  def winner_=(value: Option[Player]): Unit =
    _winner = value

  /** Returns the list of available tokens.
    *
    * @return
    *   the list of available tokens.
    */
  def availableTokens: List[Token] = _availableTokens

  /** Sets the list of available tokens.
    *
    * @param value
    *   the new list of available tokens.
    */
  def availableTokens_=(value: List[Token]): Unit =
    _availableTokens = value

  /** Adds a player to the game and removes the token from the list of available tokens.
    *
    * @param player
    *   the player to add.
    */
  def addPlayer(player: Player): Unit =
    players = player :: players
    availableTokens = availableTokens.filter(_ != player.token)

  /** Removes a player from the game and adds the token to the list of available tokens.
    *
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
    gameBoard = GameBoard()
