package PPS.scalopoly.engine

import PPS.scalopoly.BaseTest
import PPS.scalopoly.engine.{GameEngine, EndgameLogicEngine}
import org.junit.jupiter.api.Assertions.{assertTrue, assertFalse}
import org.junit.jupiter.api.{BeforeEach, Test}

class TestEndGameLogicEngine extends BaseTest:

  @BeforeEach
  override def setup(): Unit =
    GameEngine.newGame()
    players.foreach(p => GameEngine.addPlayer(p))
    GameEngine.startGame()

  @Test
  def testCheckVictory(): Unit =
    assertFalse(EndgameLogicEngine.checkVictory())
    for (_ <- GameReader.MIN_PLAYERS to players.length)
      GameEngine.currentPlayerQuit()
    assertTrue(EndgameLogicEngine.checkVictory())
