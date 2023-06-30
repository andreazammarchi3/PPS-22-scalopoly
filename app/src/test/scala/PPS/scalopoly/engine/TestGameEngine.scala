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
    assertEquals(players.length, GameEngine.players.length)
    players.foreach(p => assertTrue(GameEngine.players.contains(p)))

  @Test
  def testEndTurn(): Unit =
    val shuffledPlayer = GameEngine.players
    for i <- 1 until players.length
    do
      GameEngine.endTurn()
      assertTrue(GameEngine.currentPlayer.equals(shuffledPlayer(i)))

    GameEngine.endTurn()
    assertTrue(GameEngine.currentPlayer.equals(shuffledPlayer(0)))

  @Test
  def testMoveCurrentPlayer(): Unit =
    val DEFAULT_STARTING_POSITION = 0
    val startPosition = GameEngine.currentPlayer.actualPosition
    assertEquals(DEFAULT_STARTING_POSITION, startPosition)
    val dicePair = DiceManager().roll()
    val sum = dicePair._1 + dicePair._2
    GameEngine.moveCurrentPlayer(sum)
    assertTrue(dicePair._1.isValidInt && dicePair._2.isValidInt)
    assertEquals(
      startPosition + sum,
      GameEngine.currentPlayer.actualPosition
    )

  @Test
  def testCurrentPlayerQuit(): Unit =
    for i <- players.length - 1 to 1
    do
      val deletedPlayer = GameEngine.currentPlayer
      GameEngine.currentPlayerQuit()
      assertEquals(i, GameEngine.players.length)
      assertTrue(!GameEngine.players.contains(deletedPlayer))
    GameEngine.currentPlayerQuit()

  @Test
  def testAvailableTokens(): Unit =
    assertEquals(
      Token.values.length - players.length,
      GameEngine.availableTokens.length
    )
    players.foreach(p =>
      assertFalse(GameEngine.availableTokens.contains(p.token))
    )

  @Test
  def testCanStartGame(): Unit =
    assertTrue(GameEngine.canStartGame)
    players.foreach(p => GameEngine.removePlayer(p))
    assertFalse(GameEngine.canStartGame)
    GameEngine.addPlayer(player1)
    assertFalse(GameEngine.canStartGame)
    GameEngine.addPlayer(player2)
    assertTrue(GameEngine.canStartGame)

  @Test
  def testCanAddPlayer(): Unit =
    assertTrue(GameEngine.canAddPlayer)
    GameEngine.addPlayer(player4)
    assertTrue(GameEngine.canAddPlayer)
    GameEngine.addPlayer(player5)
    assertTrue(GameEngine.canAddPlayer)
    GameEngine.addPlayer(player6)
    assertFalse(GameEngine.canAddPlayer)

  @Test
  def testWinner(): Unit =
    assertEquals(None, GameEngine.winner)
    Game.winner = Some(player1)
    assertEquals(Some(player1), GameEngine.winner)

  @Test
  def testCheckSpaceStatus(): Unit =
    // VICOLO_CORTO must be PURCHASABLE
    GameEngine.moveCurrentPlayer(1)
    assertEquals(SpaceStatus.PURCHASABLE, GameEngine.checkSpaceStatus)
    GameEngine.playerBuysPurchasableSpace(
      GameEngine.currentPlayer,
      PurchasableSpace.VICOLO_CORTO
    )

    // PROBABILITA' must be NOT_PURCHASABLE
    GameEngine.moveCurrentPlayer(1)
    assertEquals(SpaceStatus.NOT_PURCHASABLE, GameEngine.checkSpaceStatus)

    // VICOLO_CORTO must be OWNED_BY_CURRENT_PLAYER
    GameEngine.moveCurrentPlayer(39)
    println(GameEngine.currentPlayer.actualPosition)
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
