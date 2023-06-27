package PPS.scalopoly.engine

import PPS.scalopoly.BaseTest
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.*
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue, assertFalse}
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
    val startPosition = GameEngine.currentPlayer.actualPosition
    assertEquals(Player.DEFAULT_STARTING_POSITION, startPosition)
    val (dice1, dice2) = GameEngine.moveCurrentPlayer()
    assertEquals(
      startPosition + dice1 + dice2,
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
  def testGetSpaceNameFromPlayerPosition(): Unit =
    assertEquals(
      GameBoard.gameBoardList(0),
      GameEngine.getSpaceNameFromPlayerPosition(GameEngine.currentPlayer)
    )

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
  def testGetRealEstateBySpaceName(): Unit =
    assertEquals(
      SpaceName.VIA,
      Game.getRealEstateBySpaceName(SpaceName.VIA).spaceName
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
