package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

class TestGame {
  var game: Game = new Game
  var player1: Player = Player("", null)
  var player2: Player = Player("", null)
  var player3: Player = Player("", null)

  @BeforeEach
  def setup(): Unit =
    game = new Game
    player1 = Player("P1", Token.FUNGO)
    player2 = Player("P2", Token.NAVE)
    player3 = Player("P3", Token.GATTO)
    game.players = List(player1, player2, player3)

  @Test
  def testAddPlayer(): Unit =
    val player4 = Player("P4", Token.DITALE)
    game.addPlayer(player4)
    assertEquals(List(player4, player1, player2, player3), game.players)

  @Test
  def testStartGame(): Unit =
    game.startGame()
    assertEquals(3, game.players.length)
    assertTrue(game.players.contains(player1))
    assertTrue(game.players.contains(player2))
    assertTrue(game.players.contains(player3))
    assertTrue(game.currentPlayer.isDefined)

  @Test
  def testEndTurn(): Unit =
    game.currentPlayer = Some(player1)
    game.endTurn()
    assertTrue(game.currentPlayer.get.equals(player2))
    game.endTurn()
    assertTrue(game.currentPlayer.get.equals(player3))
    game.endTurn()
    assertTrue(game.currentPlayer.get.equals(player1))
}
