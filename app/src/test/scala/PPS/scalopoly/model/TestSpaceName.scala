package PPS.scalopoly.model

import PPS.scalopoly.Utils
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
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
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, Int, SpaceName](
        SpaceName.fromOrdinal,
        SpaceName.values.length + 1
      )
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
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, String, SpaceName](
        SpaceName.valueOf,
        "NOT_EXIST"
      )
    )
