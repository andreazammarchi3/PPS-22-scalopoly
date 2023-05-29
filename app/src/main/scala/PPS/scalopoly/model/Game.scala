package PPS.scalopoly.model

import PPS.scalopoly.utils.GameUtils

import scala.util.Random

class Game:
  private var _currentPlayer: Option[Player] = None
  private var _players: List[Player] = List.empty
  private var _availableTokens: List[Token] = Token.values.toList

  def currentPlayer: Option[Player] = _currentPlayer

  def currentPlayer_=(value: Option[Player]): Unit =
    _currentPlayer = value

  def players: List[Player] = _players

  def players_=(value: List[Player]): Unit =
    _players = value

  def availableTokens: List[Token] = _availableTokens

  def availableTokens_=(value: List[Token]): Unit =
    _availableTokens = value

  def addPlayer(player: Player): Unit =
    players = player :: players
    availableTokens = availableTokens.filter(_ != player.token)

  def removePlayer(player: Player): List[Player] =
    availableTokens = player.token :: availableTokens
    players.filter(_ != player)
