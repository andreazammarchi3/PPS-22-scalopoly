package PPS.scalopoly.utils

import PPS.scalopoly.model.{GameBoard, Player}

import scala.util.Random

object GameUtils:
  def shufflePlayers(players: List[Player]): List[Player] =
    val shuffledList = Random.shuffle(players)
    shuffledList

  def addSumToPosition(sum: Int, position: Int, gameBoard: GameBoard): Int = sum + position match {
    case result if result >= gameBoard.size => result - gameBoard.size
    case result => result
  }
