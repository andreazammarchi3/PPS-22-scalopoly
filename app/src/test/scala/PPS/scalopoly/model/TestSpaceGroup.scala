package PPS.scalopoly.model

import PPS.scalopoly.model.SpaceGroup
import org.junit.jupiter.api.Assertions.{
  assertEquals,
  assertFalse,
  assertThrows,
  assertTrue
}
import org.junit.jupiter.api.Test

class TestSpaceGroup:
  private val ROSA_POSITION = 0
  private val NUM_GROUPS = 10
  @Test
  def testFromOrdinal(): Unit =
    assertEquals(
      SpaceGroup.ROSA,
      SpaceGroup.fromOrdinal(ROSA_POSITION)
    )
    assertThrows(
      classOf[NoSuchElementException],
      () => SpaceGroup.fromOrdinal(SpaceGroup.values.length + 1)
    )

  @Test
  def testValues(): Unit =
    assertEquals(NUM_GROUPS, SpaceGroup.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- SpaceGroup.values.indices)
      assertEquals(
        SpaceGroup.fromOrdinal(i),
        SpaceGroup.valueOf(SpaceGroup.fromOrdinal(i).toString)
      )
    assertThrows(
      classOf[IllegalArgumentException],
      () => SpaceGroup.valueOf("NOT_EXIST")
    )
