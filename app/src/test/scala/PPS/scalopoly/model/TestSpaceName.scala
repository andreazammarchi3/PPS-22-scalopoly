package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.{
  assertEquals,
  assertFalse,
  assertThrows,
  assertTrue
}
import org.junit.jupiter.api.Test

class TestSpaceName:

  private val VICOLO_CORTO_POSITION = 0
  private val PIAZZA_UNIVERSITA_POSITION = 7
  private val NUM_SPACES = 36
  @Test
  def testFromOrdinal(): Unit =
    assertEquals(
      SpaceName.VICOLO_CORTO,
      SpaceName.fromOrdinal(VICOLO_CORTO_POSITION)
    )
    assertEquals(
      SpaceName.PIAZZA_UNIVERSITA,
      SpaceName.fromOrdinal(PIAZZA_UNIVERSITA_POSITION)
    )
    assertThrows(
      classOf[NoSuchElementException],
      () => SpaceName.fromOrdinal(SpaceName.values.length + 1)
    )

  @Test
  def testValues(): Unit =
    assertEquals(NUM_SPACES, SpaceName.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- SpaceName.values.indices)
      assertEquals(
        SpaceName.fromOrdinal(i),
        SpaceName.valueOf(SpaceName.fromOrdinal(i).toString)
      )
    assertThrows(
      classOf[IllegalArgumentException],
      () => SpaceName.valueOf("NOT_EXIST")
    )

  @Test
  def testName(): Unit =
    assertEquals("Vicolo Corto", SpaceName.VICOLO_CORTO.name)

  @Test
  def testIsPurchasable(): Unit =
    assertTrue(SpaceName.VICOLO_CORTO.isPurchasable)
    assertFalse(SpaceName.PRIGIONE_TRANSITO.isPurchasable)
