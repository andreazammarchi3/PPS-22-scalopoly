package PPS.scalopoly.view

import PPS.scalopoly.model.*
import PPS.scalopoly.view.*
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.api.{AfterAll, AfterEach, BeforeAll, BeforeEach, Disabled, Test, TestInstance}
import org.junit.jupiter.api.extension.ExtensionContext

import java.io.{ByteArrayInputStream, InputStream, PrintStream, PrintWriter, StringReader}
import java.security.Permission

/**
 * CLI testing
 */
@TestInstance(Lifecycle.PER_CLASS)
@Disabled
class CLIUITest:

  var game = new Game
  var cli = new CLIUI
  var testIn: ByteArrayInputStream = null

  //region System.exit JUnit handling
  sealed case class ExitException(status: Int) extends SecurityException("System.exit() handler")
  sealed class NoExitSecurityManager extends SecurityManager:
    override def checkPermission(perm: Permission): Unit = {}
    override def checkPermission(perm: Permission, context: Object): Unit = {}
    override def checkExit(status: Int): Unit =
      super.checkExit(status)
      throw ExitException(status)
  //endregion

  //region SetUp/TearDown
  @BeforeAll
  def setUpAll(): Unit =
    System.setSecurityManager(new NoExitSecurityManager())
  @BeforeEach
  def setUp(): Unit =
    val game = new Game
    val cli = CLIUI()

  @AfterAll
  def tearDownAll(): Unit =
    System.setSecurityManager(null)

  //endregion

  private def initGameWithOnePlayerAndStartGame(game: Game, start: Boolean = true) : Unit =
    val player1 = Player("P1", Token.FUNGO)
    val player2 = Player("P2", Token.NAVE)
    val player3 = Player("P3", Token.GATTO)
    game.players = List(player1, player2, player3)
    if (start) game.startGame()
  private def provideInput(data: String): Unit =
    testIn = new ByteArrayInputStream(data.getBytes())
    System.setIn(testIn)

  @Test
  def testShowGameBoard(): Unit =
    cli.showGameBoard(game)

  @Test
  def testShowGameBoard_WithPlayer_NotStarted(): Unit =
    initGameWithOnePlayerAndStartGame(game, false)
    cli.showGameBoard(game)

  @Test
  def testShowGameBoard_WithPlayer_Started(): Unit =
    initGameWithOnePlayerAndStartGame(game: Game)
    cli.showGameBoard(game)
  @Test
  def testShowGameStart_Play(): Unit =
    provideInput("G\n")
    cli.showGameStart(game)

  @Test
  def testShowGameStart_Exit(): Unit =
    provideInput("E\n")
    try
      cli.showGameStart(game)
    catch
      case e: ExitException =>
        assertEquals( 0, e.status)

  @Test
  def testShowGameStart_ExitWithWrongInputThanExit(): Unit =
    provideInput("d\ne\n")
    try
      cli.showGameStart(game)
    catch
      case e: ExitException =>
        assertEquals(0, e.status)

  @Test
  def testShowGameAddPlayerOrStartPlay_AddPlayerAndStart(): Unit =
    provideInput("A\nMario\n4\np\n")
    cli.showGameAddPlayerOrStartPlay(game)

  @Test
  def testShowGameAddPlayer_AddTwoPlayersAndStart(): Unit =
    provideInput("Mario\n1\nGiovanni\n2\np\n")
    cli.showGameAddPlayers(game)

  @Test
  def testShowGameAddPlayer_AndExit(): Unit =
    try
      provideInput("Mario\n1\nE\n")
      cli.showGameAddPlayers(game)
    catch
      case e: ExitException =>
        assertEquals(0, e.status)

  @Test
  def testShowGameAddPlayer_EmptyInput(): Unit =
    provideInput("\n")
    try
      cli.showGameAddPlayers(game)
    catch
      case e: ExitException =>
        assertEquals(0, e.status)

  @Test
  def testShowAskCurrentPlayerRollDiceOrQuit_Quit(): Unit =
    initGameWithOnePlayerAndStartGame(game: Game)
    provideInput("2\n")
    try
      cli.showAskCurrentUserToRollDiceOrQuit(game)
    catch
      case e: ExitException =>
        assertEquals(0, e.status)

  @Test
  def testShowAskCurrentPlayerRollDiceOrQuit_Roll(): Unit =
    initGameWithOnePlayerAndStartGame(game: Game)
    provideInput("1\n")
    cli.showAskCurrentUserToRollDiceOrQuit(game)
  @Test
  def testShowAskCurrentPlayerEndTurnOrOrQuit_Quit(): Unit =
    initGameWithOnePlayerAndStartGame(game: Game)
    provideInput("2\n")
    try
      cli.showAskCurrentPlayerEndTurnOrOrQuit(game)
    catch
      case e: ExitException =>
        assertEquals(0, e.status)

  @Test
  def testShowAskCurrentPlayerEndTurnOrOrQuit_EndTurn(): Unit =
    initGameWithOnePlayerAndStartGame(game: Game)
    provideInput("1\n")
    cli.showAskCurrentPlayerEndTurnOrOrQuit(game)