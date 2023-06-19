package PPS.scalopoly.engine

import PPS.scalopoly.BaseTest
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.*
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
class TestGameEngine extends BaseTest:

  @BeforeEach
  override def setup(): Unit =
    super.setup()
    GameEngine.newGame()
    game.players.foreach(p => GameEngine.addPlayer(p))
    GameEngine.startGame()

  @Test
  def testStartGame(): Unit =
    assertEquals(3, GameEngine.players.length)
    assertTrue(GameEngine.players.contains(player1))
    assertTrue(GameEngine.players.contains(player2))
    assertTrue(GameEngine.players.contains(player3))
    assertTrue(GameEngine.currentPlayer.isDefined)

  @Test
  def testEndTurn(): Unit =
    game.players = GameEngine.players
    GameEngine.endTurn()
    assertTrue(GameEngine.currentPlayer.get.equals(game.players(1)))
    GameEngine.endTurn()
    assertTrue(GameEngine.currentPlayer.get.equals(game.players(2)))
    GameEngine.endTurn()
    assertTrue(GameEngine.currentPlayer.get.equals(game.players(0)))

  @Test
  def testMoveCurrentPlayer(): Unit =
    val startPosition = GameEngine.currentPlayer.get.actualPosition
    GameEngine.moveCurrentPlayer()
    assertEquals(
      startPosition + GameEngine.dice.sum(),
      GameEngine.currentPlayer.get.actualPosition
    )

  @Test
  def testCurrentPlayerQuit(): Unit =
    var deletedPlayer = GameEngine.currentPlayer.get
    GameEngine.currentPlayerQuit()
    assertEquals(2, GameEngine.players.length)
    assertTrue(!GameEngine.players.contains(deletedPlayer))
    deletedPlayer = GameEngine.currentPlayer.get
    GameEngine.currentPlayerQuit()
    assertEquals(1, GameEngine.players.length)
    assertTrue(!GameEngine.players.contains(deletedPlayer))

  @Test
  def testGetSpaceNameFromPlayerPosition(): Unit =
    assertEquals(
      GameEngine.gameBoard.gameBoardMap(0),
      GameEngine.getSpaceNameFromPlayerPosition(GameEngine.currentPlayer.get)
    )
