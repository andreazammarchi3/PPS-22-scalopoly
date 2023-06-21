package PPS.scalopoly.model

import PPS.scalopoly.Utils
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class TestSpaceName:
  @Test
  def testFromOrdinal(): Unit =
    assertEquals(SpaceName.VICOLO_CORTO, SpaceName.fromOrdinal(0))
    assertEquals(SpaceName.PIAZZA_UNIVERSITA, SpaceName.fromOrdinal(7))
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, Int, SpaceName](
        SpaceName.fromOrdinal,
        SpaceName.values.length + 1
      )
    )

  @Test
  def testValues(): Unit =
    assertEquals(36, SpaceName.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- SpaceName.values.indices)
      assertEquals(
        SpaceName.fromOrdinal(i),
        SpaceName.valueOf(SpaceName.fromOrdinal(i).toString)
      )
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, String, SpaceName](
        SpaceName.valueOf,
        "NOT_EXIST"
      )
    )
