package PPS.scalopoly

import PPS.scalopoly.model.{Game, Player, Token}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
abstract class BaseTest:
  var player1: Player = Player("", null)
  var player2: Player = Player("", null)
  var player3: Player = Player("", null)
  var players: List[Player] = _

  def setup(): Unit =
    player1 = Player("P1", Token.CARRIOLA)
    player2 = Player("P2", Token.NAVE)
    player3 = Player("P3", Token.GATTO)
    players = List(player1, player2, player3)
