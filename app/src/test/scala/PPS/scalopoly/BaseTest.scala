package PPS.scalopoly

import PPS.scalopoly.model.{Player, Token}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
abstract class BaseTest:
  val player1: Player = Player("P1", Token.CARRIOLA)
  val player2: Player = Player("P2", Token.NAVE)
  val player3: Player = Player("P3", Token.GATTO)
  val player4: Player = Player("P4", Token.CANE)
  val player5: Player = Player("P5", Token.CILINDRO)
  val player6: Player = Player("P6", Token.DITALE)
  val players: List[Player] = List(player1, player2, player3)

  def setup(): Unit
