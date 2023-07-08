package PPS.scalopoly.engine

import PPS.scalopoly.BaseTest
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils
import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
class TestGameEngine extends BaseTest:

  @BeforeEach
  override def setup(): Unit =
    GameEngine.newGame()
    players.foreach(p => GameEngine.addPlayer(p))
    GameEngine.startGame()

  @Test
  def testStartGame(): Unit =
    assertEquals(players.length, GameReader.players.length)
    players.foreach(p => assertTrue(GameReader.players.contains(p)))

  @Test
  def testEndTurn(): Unit =
    val shuffledPlayer = GameReader.players
    for i <- 1 until players.length
    do
      GameEngine.endTurn()
      assertTrue(GameReader.currentPlayer.equals(shuffledPlayer(i)))

    GameEngine.endTurn()
    assertTrue(GameReader.currentPlayer.equals(shuffledPlayer(0)))

  @Test
  def testMoveCurrentPlayer(): Unit =
    val DEFAULT_STARTING_POSITION = 0
    val startPosition = GameReader.currentPlayer.actualPosition
    assertEquals(DEFAULT_STARTING_POSITION, startPosition)
    val dicePair = DiceManager().roll()
    val sum = dicePair._1 + dicePair._2
    GameEngine.moveCurrentPlayer(sum)
    assertTrue(dicePair._1.isValidInt && dicePair._2.isValidInt)
    assertEquals(
      startPosition + sum,
      GameReader.currentPlayer.actualPosition
    )

  @Test
  def testCurrentPlayerQuit(): Unit =
    for i <- players.length - 1 to 1
    do
      val deletedPlayer = GameReader.currentPlayer
      GameEngine.currentPlayerQuit()
      assertEquals(i, GameReader.players.length)
      assertTrue(!GameReader.players.contains(deletedPlayer))
    GameEngine.currentPlayerQuit()

  @Test
  def testAvailableTokens(): Unit =
    assertEquals(
      Token.values.length - players.length,
      GameReader.availableTokens.length
    )
    players.foreach(p => assertFalse(GameReader.availableTokens.contains(p.token)))

  @Test
  def testCanStartGame(): Unit =
    assertTrue(GameReader.canStartGame)
    players.foreach(p => GameEngine.removePlayer(p))
    assertFalse(GameReader.canStartGame)
    GameEngine.addPlayer(player1)
    assertFalse(GameReader.canStartGame)
    GameEngine.addPlayer(player2)
    assertTrue(GameReader.canStartGame)

  @Test
  def testCanAddPlayer(): Unit =
    assertTrue(GameReader.canAddPlayer)
    GameEngine.addPlayer(player4)
    assertTrue(GameReader.canAddPlayer)
    GameEngine.addPlayer(player5)
    assertTrue(GameReader.canAddPlayer)
    GameEngine.addPlayer(player6)
    assertFalse(GameReader.canAddPlayer)

  @Test
  def testWinner(): Unit =
    assertEquals(None, GameReader.winner)
    Game.winner = Some(player1)
    assertEquals(Some(player1), GameReader.winner)

  @Test
  def testCheckSpaceStatus(): Unit =
    // VICOLO_CORTO must be PURCHASABLE
    GameEngine.moveCurrentPlayer(1)
    assertEquals(SpaceStatus.PURCHASABLE, GameEngine.checkSpaceStatus)
    GameUtils
      .getPurchasableSpaceFromPlayerPosition(GameReader.currentPlayer)
      .foreach(p => PlayerActionsEngine.playerBuysPurchasableSpace(GameReader.currentPlayer, p))

    // PROBABILITA' must be NOT_PURCHASABLE
    GameEngine.moveCurrentPlayer(1)
    assertEquals(SpaceStatus.NOT_PURCHASABLE, GameEngine.checkSpaceStatus)

    // VICOLO_CORTO must be OWNED_BY_CURRENT_PLAYER
    GameEngine.moveCurrentPlayer(39)
    assertEquals(
      SpaceStatus.OWNED_BY_CURRENT_PLAYER,
      GameEngine.checkSpaceStatus
    )

    // VICOLO_CORTO must be OWNED_BY_ANOTHER_PLAYER
    GameEngine.endTurn()
    GameEngine.moveCurrentPlayer(1)
    assertEquals(
      SpaceStatus.OWNED_BY_ANOTHER_PLAYER,
      GameEngine.checkSpaceStatus
    )
