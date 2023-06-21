package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class TestDice:
  @Test
  def testRollDice(): Unit =
    for (_ <- 1 to 10)
      Dice.rollDice()
      assertTrue(Dice.sum() >= 2 && Dice.sum() <= 12)

  @Test
  def testCheckSame(): Unit =
    for (_ <- 1 to 10)
      Dice.rollDice()
      assertEquals(Dice.dice1 == Dice.dice2, Dice.checkSame())

  @Test
  def testSum(): Unit =
    for (_ <- 1 to 10)
      Dice.rollDice()
      assertEquals(Dice.dice1 + Dice.dice2, Dice.sum())
