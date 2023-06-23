package PPS.scalopoly.model

import PPS.scalopoly.BaseTest
import PPS.scalopoly.model.SpaceName.VIA
import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
class TestGame extends BaseTest:

  @BeforeEach
  override def setup(): Unit =
    Game.reset()
    players.foreach(p => Game.addPlayer(p))

  @Test
  def testTokensAvailability(): Unit =
    assertEquals(
      Game.availableTokens.length,
      Token.values.length - players.length
    )
    Game.addPlayer(player4)
    assertFalse(Game.availableTokens.contains(player4.token))
    Game.removePlayer(player4)
    assertTrue(Game.availableTokens.contains(player4.token))

  @Test
  def testAddPlayer(): Unit =
    Game.addPlayer(player4)
    assertEquals(List(player4, player3, player2, player1), Game.players)

  @Test
  def testRemovePlayer(): Unit =
    Game.removePlayer(player1)
    assertEquals(List(player3, player2), Game.players)
