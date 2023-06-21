package PPS.scalopoly.utils

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.{GameBoard, Player}

import scala.util.Random

object GameUtils:
  def shufflePlayers(players: List[Player]): List[Player] =
    val shuffledList = Random.shuffle(players)
    shuffledList

  def addSumToPosition(sum: Int, position: Int, gameBoard: GameBoard): Int =
    sum + position match
      case result if result >= gameBoard.size => result - gameBoard.size
      case result                             => result

  def getCoordinateFromPosition(position: Int): (Int, Int) = position match
    case _ if position < 0 => throw new IllegalArgumentException("Position cannot be negative")
    case _ if position >= GameEngine.gameBoard.size => throw new IllegalArgumentException("Position cannot be greater than board size")
    case _ if position < 10 => (10 - position, 10)
    case _ if position < 20 => (0, 20 - position)
    case _ if position < 30 => (position - 20, 0)
    case _ => (10, position - 30)

  def getNthCellInGrid(n: Int, gridSize: (Int, Int), startingCell: (Int, Int)): (Int, Int) = n match
    case _ if gridSize._1 <= 0 => throw new IllegalArgumentException("Grid columns must be positive")
    case _ if gridSize._2 <= 0 => throw new IllegalArgumentException("Grid rows must be positive")
    case _ if n <= 0 => throw new IllegalArgumentException("N must be positive")
    case _ if n > gridSize._1 * gridSize._2 => throw new IllegalArgumentException("N cannot be greater than grid size")
    case _ if n % gridSize._1 != 0 => (n % gridSize._1 - 1, n / gridSize._1)
    case _ => (gridSize._1 - 1, n / gridSize._1 - 1)

  def getPlayerId(player: Player): String =
    player.nickname + " " + player.token
