package PPS.scalopoly.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class TestPlayer:
  val player: Player = Player("player", Token.CANE)

  @Test
  def testApply(): Unit =
    assertEquals(0, player.actualPosition)

  @Test
  def testMove(): Unit =
    val STEPS = 10
    assertEquals(STEPS, player.move(STEPS).actualPosition)

  @Test
  def testFromProduct(): Unit =
    val playerData = (player.nickname, player.token, player.actualPosition)
    assertEquals(player, Player.fromProduct(playerData))
