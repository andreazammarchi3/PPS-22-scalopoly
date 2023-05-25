package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class TestDice {
  @Test
  def testRollDice(): Unit =
    val dice = new Dice
    for (_ <- 1 to 10)
      dice.rollDice()
      assertTrue(dice.sum() >= 2 && dice.sum() <= 12)
}
