package PPS.scalopoly.engine

import GameReader.players
import PPS.scalopoly.model.*

/** Reader that allows to read the [[Game]] state.
  */
object GameReader:

  val MIN_PLAYERS = 2
  private val MAX_PLAYERS = 6

  /** Returns the list of players.
    *
    * @return
    *   the list of players.
    */
  def players: List[Player] = Game.players

  /** Returns the winner of the game.
    *
    * @return
    *   the winner of the game.
    */
  def winner: Option[Player] = Game.winner

  /** Returns the list of available tokens.
    *
    * @return
    *   the list of available tokens.
    */
  def availableTokens: List[Token] =
    Game.availableTokens

  /** Returns the game board.
    *
    * @return
    *   the game board.
    */
  def gameBoard: GameBoard = Game.gameBoard

  /** Returns if it is the turn of the bot.
    *
    * @return
    *   true if it is the turn of the bot, false otherwise.
    */
  def botIsPlaying: Boolean = Game.botIsPlaying

  /** Check if the game can start, at least 2 players are needed.
    *
    * @return
    *   true if the game can start, false otherwise.
    */
  def canStartGame: Boolean =
    Game.players.length match
      case i if i < MIN_PLAYERS => false
      case _                    => true

  /** Check if a player can be added to the game, at most 6 players are allowed.
    *
    * @return
    *   true if a player can be added, false otherwise.
    */
  def canAddPlayer: Boolean =
    Game.players.length match
      case i if i < MAX_PLAYERS => true
      case _                    => false

  /** Returns the current player.
    *
    * @return
    *   the current player.
    */
  def currentPlayer: Player = players(Game.currentPlayer)
