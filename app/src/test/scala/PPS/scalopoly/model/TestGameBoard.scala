package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*

class TestGameBoard {
  @Test
  def testGameBoard(): Unit =
    val gameBoard = new GameBoard
    assertEquals(40, gameBoard.size)
}
