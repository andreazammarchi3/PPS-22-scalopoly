package PPS.scalopoly.utils

import PPS.scalopoly.model.Player

import scala.util.Random

object GameUtils:
  def shufflePlayers(players: List[Player]): List[Player] =
    val shuffledList = Random.shuffle(players)
    shuffledList