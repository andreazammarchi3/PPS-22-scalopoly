package PPS.scalopoly.model

import PPS.scalopoly.Utils
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class TestToken:
  @Test
  def testFromOrdinal(): Unit =
    assertEquals(Token.DITALE, Token.fromOrdinal(0))
    assertEquals(Token.CANE, Token.fromOrdinal(7))
    assertTrue(Utils.testCatchException[IllegalArgumentException, Int, Token](Token.fromOrdinal, Token.values.length + 1))

  @Test
  def testValues(): Unit =
    assertEquals(8, Token.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- Token.values.indices)
      assertEquals(Token.fromOrdinal(i), Token.valueOf(Token.fromOrdinal(i).toString))
    assertTrue(Utils.testCatchException[IllegalArgumentException, String, Token](Token.valueOf, "NOT_EXIST"))