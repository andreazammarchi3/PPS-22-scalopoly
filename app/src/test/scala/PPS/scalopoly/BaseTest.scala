package PPS.scalopoly

import PPS.scalopoly.model.{Game, Player, Token}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
abstract class BaseTest:
  var game: Game = new Game
  var player1: Player = Player("", null)
  var player2: Player = Player("", null)
  var player3: Player = Player("", null)
  
  def setup(): Unit =
    game = new Game
    player1 = Player("P1", Token.FUNGO)
    player2 = Player("P2", Token.NAVE)
    player3 = Player("P3", Token.GATTO)
    game.players = List(player1, player2, player3)