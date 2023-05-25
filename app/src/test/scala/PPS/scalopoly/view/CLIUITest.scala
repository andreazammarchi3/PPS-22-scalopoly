package PPS.scalopoly.view

import PPS.scalopoly.model.*
import PPS.scalopoly.view.*
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

/**
 * CLI testing
 */
class CLIUITest:

  @Test
  def testShowGameBoard(): Unit = {
    val game = new Game
    val board = getBoard
    game.gameBoard = board
    val cli = CLIUI()
    cli.showGameBoard(game)
  }
  private def getBoard: Map[Int, SpaceName] = {
    SpaceName.values.zipWithIndex.map(_.swap).toMap
  }

