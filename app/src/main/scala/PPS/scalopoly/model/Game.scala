package PPS.scalopoly.model

import PPS.scalopoly.utils.GameUtils

import scala.util.Random

class Game:
  private var _currentPlayer: Option[Player] = None
  private var _players: List[Player] = List.empty
  private val _gameBoard: GameBoard = new GameBoard
  private val dice = new Dice

  def currentPlayer: Option[Player] = _currentPlayer

  def currentPlayer_=(value: Option[Player]): Unit = {
    _currentPlayer = value
  }

  def players: List[Player] = _players

  def players_=(value: List[Player]): Unit = {
    _players = value
  }
  
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

  def movePlayer(): Unit =
    currentPlayer.get.actualPosition = 
      GameUtils.addSumToPosition(dice.sum(), currentPlayer.get.actualPosition, gameBoard)
