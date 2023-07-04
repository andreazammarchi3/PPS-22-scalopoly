package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestGameBoard:

  private val GAMEBOARD_SIZE = 40
  @Test
  def testGameBoard(): Unit =
    assertEquals(GAMEBOARD_SIZE, GameBoard().size)
