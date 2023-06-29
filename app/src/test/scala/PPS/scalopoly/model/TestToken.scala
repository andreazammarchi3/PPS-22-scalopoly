package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.{assertEquals, assertThrows, assertTrue}
import org.junit.jupiter.api.Test

class TestToken:

  private val DITALE_POSITION = 0
  private val CANE_POSITION = 7
  private val NUM_TOKENS = 8
  @Test
  def testFromOrdinal(): Unit =
    assertEquals(Token.DITALE, Token.fromOrdinal(DITALE_POSITION))
    assertEquals(Token.CANE, Token.fromOrdinal(CANE_POSITION))
    assertThrows(
      classOf[NoSuchElementException],
      () => Token.fromOrdinal(Token.values.length + 1)
    )

  @Test
  def testValues(): Unit =
    assertEquals(NUM_TOKENS, Token.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- Token.values.indices)
      assertEquals(
        Token.fromOrdinal(i),
        Token.valueOf(Token.fromOrdinal(i).toString)
      )
    assertThrows(
      classOf[IllegalArgumentException],
      () => Token.valueOf("NOT_EXIST")
    )
