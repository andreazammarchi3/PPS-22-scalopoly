package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameTest {

  @Test
  def testAddPlayer(): Unit = {
    val game = new Game
    assertEquals(List.empty, game.players)
    val carlo = Player("Carlo", Token.FUNGO)
    game.addPlayer(carlo)
    assertEquals(List(carlo), game.players)
  }

  @Test
  def testStartGame(): Unit = {
    val game = new Game
    
  }

  @Test
  def testEndTurn(): Unit = {

  }
}
