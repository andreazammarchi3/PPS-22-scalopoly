package PPS.scalopoly.controller

import PPS.scalopoly.BaseTest
import PPS.scalopoly.model.*
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
class TestGameController extends BaseTest:
  var gameController: GameController = _

  @BeforeEach
  override def setup(): Unit =
    super.setup()
    gameController = new GameController(game, null)

  @Test
  def testStartGame(): Unit =
    gameController.startGame()
    assertEquals(3, game.players.length)
    assertTrue(game.players.contains(player1))
    assertTrue(game.players.contains(player2))
    assertTrue(game.players.contains(player3))
    assertTrue(game.currentPlayer.isDefined)

  @Test
  def testEndTurn(): Unit =
    game.currentPlayer = Some(player1)
    gameController.endTurn()
    assertTrue(game.currentPlayer.get.equals(player2))
    gameController.endTurn()
    assertTrue(game.currentPlayer.get.equals(player3))
    gameController.endTurn()
    assertTrue(game.currentPlayer.get.equals(player1))

  @Test
  def testMoveCurrentPlayer(): Unit =
    gameController.startGame()
    gameController.moveCurrentPlayer()
    assertEquals(gameController.dice.sum(), game.currentPlayer.get.actualPosition)

  @Test
  def testCurrentPlayerQuit(): Unit =
    gameController.startGame()
    gameController.currentPlayerQuit()
    assertEquals(2, game.players.length)
    gameController.currentPlayerQuit()
    assertEquals(1, game.players.length)


  @Test
  def testGetSpaceNameFromPlayerPosition(): Unit =
    assertEquals(gameController.gameBoard.gameBoardMap(0), gameController.getSpaceNameFromPlayerPosition(player1))
