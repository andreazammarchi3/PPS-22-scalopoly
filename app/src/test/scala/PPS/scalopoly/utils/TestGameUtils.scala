package PPS.scalopoly.utils

import PPS.scalopoly.model.GameBoard
import PPS.scalopoly.utils.GameUtils
import PPS.scalopoly.Utils
import org.junit.jupiter.api.Assertions.{
  assertEquals,
  assertThrows,
  assertTrue,
  fail
}
import org.junit.jupiter.api.Test

class TestGameUtils:
  @Test
  def testAddSumToPosition(): Unit =
    assertEquals(5, GameUtils.addSumToPosition(4, 1))
    assertEquals(0, GameUtils.addSumToPosition(1, GameBoard.size - 1))

  @Test
  def testGetCoordinateFromPosition(): Unit =
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, Int, (Int, Int)](
        GameUtils.getCoordinateFromPosition,
        -1
      )
    )
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, Int, (Int, Int)](
        GameUtils.getCoordinateFromPosition,
        40
      )
    )
    assertEquals((10, 10), GameUtils.getCoordinateFromPosition(0))
    assertEquals((9, 10), GameUtils.getCoordinateFromPosition(1))
    assertEquals((0, 10), GameUtils.getCoordinateFromPosition(10))
    assertEquals((0, 9), GameUtils.getCoordinateFromPosition(11))
    assertEquals((0, 0), GameUtils.getCoordinateFromPosition(20))
    assertEquals((1, 0), GameUtils.getCoordinateFromPosition(21))
    assertEquals((10, 0), GameUtils.getCoordinateFromPosition(30))
    assertEquals((10, 1), GameUtils.getCoordinateFromPosition(31))
    assertEquals((10, 9), GameUtils.getCoordinateFromPosition(39))

  @Test
  def testGetNthCellInGrid(): Unit =
    assertTrue(
      Utils.testCatchException[
        IllegalArgumentException,
        (Int, (Int, Int), (Int, Int)),
        (Int, Int)
      ](GameUtils.getNthCellInGrid, (1, (0, 1), (0, 0)))
    )
    assertTrue(
      Utils.testCatchException[
        IllegalArgumentException,
        (Int, (Int, Int), (Int, Int)),
        (Int, Int)
      ](GameUtils.getNthCellInGrid, (1, (1, 0), (0, 0)))
    )
    assertTrue(
      Utils.testCatchException[
        IllegalArgumentException,
        (Int, (Int, Int), (Int, Int)),
        (Int, Int)
      ](GameUtils.getNthCellInGrid, (0, (1, 1), (0, 0)))
    )
    assertTrue(
      Utils.testCatchException[
        IllegalArgumentException,
        (Int, (Int, Int), (Int, Int)),
        (Int, Int)
      ](GameUtils.getNthCellInGrid, (10, (3, 3), (0, 0)))
    )
    assertTrue(
      Utils.testCatchException[
        IllegalArgumentException,
        (Int, (Int, Int), (Int, Int)),
        (Int, Int)
      ](GameUtils.getNthCellInGrid, (13, (4, 3), (0, 0)))
    )
    assertEquals((0, 0), GameUtils.getNthCellInGrid(1, (4, 3), (0, 0)))
    assertEquals((3, 0), GameUtils.getNthCellInGrid(4, (4, 3), (0, 0)))
    assertEquals((1, 1), GameUtils.getNthCellInGrid(6, (4, 3), (0, 0)))
    assertEquals((3, 2), GameUtils.getNthCellInGrid(12, (4, 3), (0, 0)))