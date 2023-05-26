package PPS.scalopoly.model

import PPS.scalopoly.utils.GameUtils
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class TestGameUtils:
  @Test
  def testAddSumToPosition(): Unit =
    val gameBoard = new GameBoard
    assertEquals(5, GameUtils.addSumToPosition(4, 1, gameBoard))
    assertEquals(0, GameUtils.addSumToPosition(1, gameBoard.size - 1, gameBoard))

