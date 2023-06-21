package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*

class TestGameBoard:
  @Test
  def testGameBoard(): Unit =
    assertEquals(40, GameBoard.size)
