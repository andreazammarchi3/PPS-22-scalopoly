package PPS.scalopoly.model

import PPS.scalopoly.utils.GameUtils

import scala.util.Random

class Game:
  private[this] var _currentPlayer: Option[Player] = None
  private[this] var _players: List[Player] = List.empty
  private[this] val _gameBoard: GameBoard = new GameBoard

  def currentPlayer: Option[Player] = _currentPlayer

  def currentPlayer_=(value: Option[Player]): Unit =
    _currentPlayer = value

  def players: List[Player] = _players

  def players_=(value: List[Player]): Unit =
    _players = value

  def gameBoard: GameBoard = _gameBoard

  def addPlayer(newPlayer: Player): Unit =
    players = newPlayer :: players

  def startGame(): Unit =
    players = GameUtils.shufflePlayers(players)
    currentPlayer = Some(players.head)

  def endTurn(): Unit =
    val currentIndex = players.indexOf(currentPlayer.get)
    val newIndex = (currentIndex + 1) % players.length
    currentPlayer = Some(players(newIndex))