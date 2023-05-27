package PPS.scalopoly.view

import PPS.scalopoly.model.*
import PPS.scalopoly.view.*
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

import java.io.{ByteArrayInputStream, InputStream, PrintStream, PrintWriter, StringReader}

/**
 * CLI testing
 */
class CLIUITest:

  @Test
  def testShowGameBoard(): Unit =
    val game = new Game
    val cli = CLIUI()
    cli.showGameBoard(game)

  @Test
  def testShowGameStartGame(): Unit =
    val game = new Game
    val cli = CLIUI()

    val in = new ByteArrayInputStream("G".getBytes());
    System.setIn(in);
    cli.showGameStart(game)
  @Test
  def testShowGameStartExit(): Unit =
    val game = new Game
    val cli = CLIUI()

    val in = new ByteArrayInputStream("E".getBytes());
    System.setIn(in);

    cli.showGameStart(game)

  @Test
  def testShowGameAddPlayerMarioOrStartPlay_AddPlayer(): Unit =
    val game = new Game
    val cli = CLIUI()

    val in = new ByteArrayInputStream("AMario".getBytes());
    System.setIn(in);

    cli.showGameAddPlayerOrStartPlay(game)
  @Test
  def testShowGameAddPlayerMario(): Unit =
    val game = new Game
    val cli = CLIUI()

    val in = new ByteArrayInputStream("Mario".getBytes());
    System.setIn(in);

    cli.showGameAddPlayers(game)


