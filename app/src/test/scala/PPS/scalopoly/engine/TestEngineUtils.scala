package PPS.scalopoly.engine

import PPS.scalopoly.BaseTest
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

class TestEngineUtils extends BaseTest:

  @BeforeEach
  override def setup(): Unit =
    GameEngine.newGame()
    players.foreach(p => GameEngine.addPlayer(p))
    GameEngine.startGame()

  @Test
  def testUpdatePlayerWith(): Unit =
    val CASH_IN = 500
    val player = GameReader.currentPlayer
    EngineUtils.updatePlayerWith(GameReader.players.indexOf(player), player.cashIn(CASH_IN))
    assertTrue(GameReader.currentPlayer.money == player.money + CASH_IN)

  @Test
  def testUpdateBuildableSpacesWith(): Unit =
    val VICOLO_CORTO = GameReader.gameBoard.buildableSpaces(0)
    EngineUtils.updateBuildableSpacesWith(VICOLO_CORTO.buildHouse)
    GameReader.gameBoard.buildableSpaces
      .find(_.name == VICOLO_CORTO.name)
      .foreach(space => assertEquals(VICOLO_CORTO.numHouse + 1, space.numHouse))
