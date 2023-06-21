package PPS.scalopoly.model

import PPS.scalopoly.utils.GameUtils

import scala.util.Random

object Game:
  private var _currentPlayer: Int = 0
  private var _players: List[Player] = List.empty
  private var _availableTokens: List[Token] = Token.values.toList

  def currentPlayer: Int = _currentPlayer

  def currentPlayer_=(value: Int): Unit =
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

  def removePlayer(player: Player): Unit =
    availableTokens = player.token :: availableTokens
    players = players.filter(_ != player)

  def reset(): Unit =
    currentPlayer = 0
    players = List.empty
    availableTokens = Token.values.toList
