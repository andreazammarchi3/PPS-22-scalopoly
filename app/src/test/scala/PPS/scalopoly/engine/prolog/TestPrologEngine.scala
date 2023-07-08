package PPS.scalopoly.engine.prolog

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.{assertDoesNotThrow, assertEquals}

class TestPrologEngine:

  @Test
  def testChance() : Unit =
    assertDoesNotThrow(() => PrologEngine.calculateChanceValue(2, 5, 15))
    assertEquals(1, PrologEngine.calculateChanceValue(2, 5, 20))
    assertEquals(1, PrologEngine.calculateChanceValue(5, 2, 4))
    assertEquals(0, PrologEngine.calculateChanceValue(2, 2, 2))

