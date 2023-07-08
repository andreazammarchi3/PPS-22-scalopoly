package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.BaseTest
import PPS.scalopoly.engine.{GameEngine, GameReader, PlayerActionsEngine}
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.{BeforeEach, Test}

class TestCompanySpace extends BaseTest:

  private val societaElettrica = GameReader.gameBoard.companySpaces(0)
  private val societaAcqua = GameReader.gameBoard.companySpaces(1)
  private val rents = List(25)
  private val SINGLE_MULTIPLIER = 4
  private val DOUBLE_MULTIPLIER = 10

  @BeforeEach
  override def setup(): Unit =
    GameEngine.newGame()
    players.foreach(p => GameEngine.addPlayer(p))
    GameEngine.startGame()

  @Test
  def testCalculateRent(): Unit =
    assertEquals(SINGLE_MULTIPLIER * rents(0), societaElettrica.calculateRent)
    PlayerActionsEngine.playerBuysPurchasableSpace(player1, societaElettrica)
    assertEquals(SINGLE_MULTIPLIER * rents(0), societaElettrica.calculateRent)
    GameReader.players
      .find(p => p.token == player1.token)
      .foreach(
        PlayerActionsEngine.playerBuysPurchasableSpace(_, societaAcqua)
      )
    assertEquals(DOUBLE_MULTIPLIER * rents(0), societaElettrica.calculateRent)
    assertEquals(DOUBLE_MULTIPLIER * rents(0), societaAcqua.calculateRent)
