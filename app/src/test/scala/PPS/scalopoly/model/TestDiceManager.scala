package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class TestDiceManager:
  val diceManager: DiceManager = DiceManager()
  @Test
  def testApply(): Unit =
    assertTrue(diceManager.dice1 >= 1 && diceManager.dice1 <= 6)
    assertTrue(diceManager.dice2 >= 1 && diceManager.dice2 <= 6)
    val newDiceManager = DiceManager(1, 2)
    assertEquals(1, newDiceManager.dice1)
    assertEquals(2, newDiceManager.dice2)

  @Test
  def testFromProduct(): Unit =
    val diceData = (diceManager.dice1, diceManager.dice2)
    assertEquals(diceManager, DiceManager.fromProduct(diceData))

  @Test
  def testRoll(): Unit =
    val diceManager: DiceManager = DiceManager()
    for (_ <- 1 to 10)
      diceManager.roll()
      val sum = diceManager.dice1 + diceManager.dice2
      assertTrue(sum >= 2 && sum <= 12)
