package PPS.scalopoly.model

import PPS.scalopoly.BaseTest
import PPS.scalopoly.model.SpaceName.VIA
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
class TestGame extends BaseTest:

  @BeforeEach
  override def setup(): Unit = super.setup()

  @Test
  def testTokensAvailability(): Unit =
    assertEquals(game.availableTokens.length, Token.values.length)
    val player4 = Player("P4", Token.DITALE)
    game.addPlayer(player4)
    assertEquals(game.availableTokens, Token.values.filter(_ != Token.DITALE).toList)
    game.removePlayer(player4)
    assertEquals(game.availableTokens, Token.values.toList)

  @Test
  def testAddPlayer(): Unit =
    val player4 = Player("P4", Token.DITALE)
    game.addPlayer(player4)
    assertEquals(List(player4, player1, player2, player3), game.players)

  @Test
  def testRemovePlayer(): Unit =
    game.players = game.removePlayer(player1)
    assertEquals(List(player2, player3), game.players)


