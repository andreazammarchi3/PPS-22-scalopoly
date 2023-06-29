package PPS.scalopoly.engine

import PPS.scalopoly.engine.SpaceStatus
import org.junit.jupiter.api.Assertions.{assertEquals, assertThrows}
import org.junit.jupiter.api.Test

class TestSpaceStatus:
  private val PURCHASABLE_POSITION = 3
  private val NUM_STATUS = 4
  @Test
  def testFromOrdinal(): Unit =
    assertEquals(
      SpaceStatus.PURCHASABLE,
      SpaceStatus.fromOrdinal(PURCHASABLE_POSITION)
    )
    assertThrows(
      classOf[NoSuchElementException],
      () => SpaceStatus.fromOrdinal(SpaceStatus.values.length + 1)
    )

  @Test
  def testValues(): Unit =
    assertEquals(NUM_STATUS, SpaceStatus.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- SpaceStatus.values.indices)
      assertEquals(
        SpaceStatus.fromOrdinal(i),
        SpaceStatus.valueOf(SpaceStatus.fromOrdinal(i).toString)
      )
    assertThrows(
      classOf[IllegalArgumentException],
      () => SpaceStatus.valueOf("NOT_EXIST")
    )
