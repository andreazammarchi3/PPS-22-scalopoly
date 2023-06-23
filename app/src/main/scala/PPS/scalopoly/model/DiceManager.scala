package PPS.scalopoly.model

import scala.util.Random

/**
 * Represents a dice manager that can roll two dice.
 * @param dice1 first dice
 * @param dice2 second dice
 */
case class DiceManager(dice1: Int, dice2: Int):
  /**
   * Rolls the dice and returns the result.
   * @return a tuple containing the result of the two dice
   */
  def roll(): (Int, Int) = (DiceManager.rollDice(), DiceManager.rollDice())

/**
 * Companion object of the DiceManager class.
 */
object DiceManager:
  /**
   * Creates a new DiceManager with two random dice.
   * @return a new DiceManager
   */
  def apply(): DiceManager = new DiceManager(rollDice(), rollDice())
  
  private def rollDice(): Int = Random.between(1, 7)
