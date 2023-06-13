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
    case _ if position < 10 => (10 - position, 10)
    case _ if position < 20 => (0, 20 - position)
    case _ if position < 30 => (position - 20, 0)
    case _ => (10, position - 30)

  def getCoordinateFromOneNumber(number: Int, columns: Int): (Int, Int) =
    if number % columns != 0 then
      (number % columns - 1, number / columns + 1)
    else
      (columns - 1, number / columns)
