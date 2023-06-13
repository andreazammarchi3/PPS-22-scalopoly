package PPS.scalopoly.utils

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
    case _ if _ < 10 => (10 - _, 10)
    case _ if _ < 20 => (0, 20 - _)
    case _ if _ < 30 => (_ - 20, 0)
    case _ if _ < 40 => (10, _ - 30)
    
  def getPositionFromCoordinate(coordinate: (Int, Int)): Int = 
