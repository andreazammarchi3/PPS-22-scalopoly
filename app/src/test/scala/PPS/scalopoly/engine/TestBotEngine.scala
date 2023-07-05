package PPS.scalopoly.engine

import PPS.scalopoly.BaseTest
import PPS.scalopoly.model.{Player, Token}
import org.junit.jupiter.api.{BeforeEach, Test}
import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse}

class TestBotEngine:
  private val p1: Player = Player("p1", Token.DITALE)
  private val bot1: Player = Player("bot1", Token.CANE, true)
  @BeforeEach
  def setup(): Unit =
    GameEngine.newGame()
    GameEngine.addPlayer(p1)
    GameEngine.addPlayer(bot1)

  @Test
  def testPlay(): Unit =
    GameEngine.currentPlayer match
      case bot if bot == bot1 =>
        Game.botIsPlaying = true
        BotEngine.play()
        Game.botIsPlaying = false
      case _ => GameEngine.endTurn()
    assertEquals(p1.token, GameEngine.currentPlayer.token)
    GameEngine.players.find(p => p.token == bot1.token).foreach(b => assertFalse(b.actualPosition == 0))
