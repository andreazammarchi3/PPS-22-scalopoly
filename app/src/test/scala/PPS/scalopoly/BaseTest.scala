package PPS.scalopoly

import PPS.scalopoly.model.{Game, Player, Token}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
abstract class BaseTest:
  val player1: Player = Player("P1", Token.CARRIOLA)
  val player2: Player = Player("P2", Token.NAVE)
  val player3: Player = Player("P3", Token.GATTO)
  val players: List[Player] = List(player1, player2, player3)

  def setup(): Unit
