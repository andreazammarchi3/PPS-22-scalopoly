package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class TestDiceManager:

  val diceManager: DiceManager = DiceManager()

  @Test
  def testApply(): Unit =
    assertTrue(
      diceManager.dice1 >= DiceManager.MIN_DICE_VALUE && diceManager.dice1 <= DiceManager.MAX_DICE_VALUE
    )
    assertTrue(
      diceManager.dice2 >= DiceManager.MIN_DICE_VALUE && diceManager.dice2 <= DiceManager.MAX_DICE_VALUE
    )
    val DICE1_VALUE = 1
    val DICE2_VALUE = 2
    val newDiceManager = DiceManager(DICE1_VALUE, DICE2_VALUE)
    assertEquals(DICE1_VALUE, newDiceManager.dice1)
    assertEquals(DICE2_VALUE, newDiceManager.dice2)

  @Test
  def testFromProduct(): Unit =
    val diceData = (diceManager.dice1, diceManager.dice2)
    assertEquals(diceManager, DiceManager.fromProduct(diceData))

  @Test
  def testRoll(): Unit =
    val diceManager: DiceManager = DiceManager()
    val TRIES = 10
    for (_ <- 1 to TRIES)
      diceManager.roll()
      val sum = diceManager.dice1 + diceManager.dice2
      assertTrue(
        sum >= DiceManager.MIN_DICE_VALUE * 2 && sum <= DiceManager.MAX_DICE_VALUE * 2
      )
