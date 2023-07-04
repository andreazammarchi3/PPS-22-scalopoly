package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.BaseTest
import PPS.scalopoly.engine.GameEngine
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

class TestStationSpace extends BaseTest:

  private val stazioneNord = GameEngine.gameBoard.stationSpaces(2)
  private val stazioneSud = GameEngine.gameBoard.stationSpaces(0)
  private val rents = List(25, 50, 100, 200)

  @BeforeEach
  override def setup(): Unit =
    GameEngine.newGame()
    players.foreach(p => GameEngine.addPlayer(p))
    GameEngine.startGame()

  @Test
  def testCalculateRent(): Unit =
    assertEquals(0, stazioneNord.calculateRent())
    GameEngine.playerBuysPurchasableSpace(player1, stazioneNord)
    assertEquals(rents(0), stazioneNord.calculateRent())
    GameEngine.players
      .find(p => p.token == player1.token)
      .foreach(
        GameEngine.playerBuysPurchasableSpace(_, stazioneSud)
      )
    assertEquals(rents(1), stazioneNord.calculateRent())
    assertEquals(rents(1), stazioneSud.calculateRent())
