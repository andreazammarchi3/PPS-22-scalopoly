package PPS.scalopoly.model

import scala.util.Random

case class DiceManager(dice1: Int, dice2: Int):
  def roll(): (Int, Int) = (DiceManager.rollDice(), DiceManager.rollDice())

object DiceManager:
  def apply(): DiceManager = new DiceManager(rollDice(), rollDice())

  private def rollDice(): Int = Random.between(1, 7)
