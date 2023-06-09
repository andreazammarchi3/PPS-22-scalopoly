package PPS.scalopoly.controller

import PPS.scalopoly.BaseTest
import PPS.scalopoly.model.*
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
class TestGameController extends BaseTest:

  @BeforeEach
  override def setup(): Unit =
    super.setup()
    GameController.newGame()
    game.players.foreach(p => GameController.addPlayer(p))
    GameController.startGame()

  @Test
  def testStartGame(): Unit =
    assertEquals(3, GameController.players.length)
    assertTrue(GameController.players.contains(player1))
    assertTrue(GameController.players.contains(player2))
    assertTrue(GameController.players.contains(player3))
    assertTrue(GameController.currentPlayer.isDefined)

  @Test
  def testEndTurn(): Unit =
    game.players = GameController.players
    GameController.endTurn()
    assertTrue(GameController.currentPlayer.get.equals(game.players(1)))
    GameController.endTurn()
    assertTrue(GameController.currentPlayer.get.equals(game.players(2)))
    GameController.endTurn()
    assertTrue(GameController.currentPlayer.get.equals(game.players(0)))

  @Test
  def testMoveCurrentPlayer(): Unit =
    val startPosition = GameController.currentPlayer.get.actualPosition
    GameController.moveCurrentPlayer()
    assertEquals(
      startPosition + GameController.dice.sum(),
      GameController.currentPlayer.get.actualPosition
    )

  @Test
  def testCurrentPlayerQuit(): Unit =
    var deletedPlayer = GameController.currentPlayer.get
    GameController.currentPlayerQuit()
    assertEquals(2, GameController.players.length)
    assertTrue(!GameController.players.contains(deletedPlayer))
    deletedPlayer = GameController.currentPlayer.get
    GameController.currentPlayerQuit()
    assertEquals(1, GameController.players.length)
    assertTrue(!GameController.players.contains(deletedPlayer))

  @Test
  def testGetSpaceNameFromPlayerPosition(): Unit =
    assertEquals(
      GameController.gameBoard.gameBoardMap(0),
      GameController.getSpaceNameFromPlayerPosition(GameController.currentPlayer.get)
    )
