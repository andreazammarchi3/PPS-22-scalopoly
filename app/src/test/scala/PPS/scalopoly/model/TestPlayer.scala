package PPS.scalopoly.model

import PPS.scalopoly.engine.GameEngine
import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse, assertTrue}
import org.junit.jupiter.api.Test

class TestPlayer:

  private val player: Player = Player("player", Token.CANE)

  @Test
  def testApply(): Unit =
    assertEquals(0, player.actualPosition)

  @Test
  def testMove(): Unit =
    val STEPS = 10
    assertEquals(STEPS, player.move(STEPS).actualPosition)

  @Test
  def testCanPayOrBuy(): Unit =
    val AFFORDABLE_PRICE = 1000
    val NOT_AFFORDABLE_PRICE = 3000
    assertTrue(player.canAfford(AFFORDABLE_PRICE))
    assertFalse(player.canAfford(NOT_AFFORDABLE_PRICE))

  @Test
  def testPay(): Unit =
    val MONEY_TO_PAY = 1000
    assertEquals(
      new Player(
        player.nickname,
        player.token,
        player.actualPosition,
        player.money - MONEY_TO_PAY,
        player.ownedProperties,
        false
      ),
      player.pay(MONEY_TO_PAY)
    )

  @Test
  def testBuy(): Unit =
    val PROPERTY_TO_BUY = GameEngine.gameBoard.purchasableSpaces(0)
    assertEquals(
      new Player(
        player.nickname,
        player.token,
        player.actualPosition,
        player.money - PROPERTY_TO_BUY.sellingPrice,
        player.ownedProperties :+ PROPERTY_TO_BUY,
        false
      ),
      player.buy(PROPERTY_TO_BUY)
    )

  @Test
  def testFromProduct(): Unit =
    val STARTING_MONEY = 2000
    val STARTING_OWNED_PROPERTIES = List.empty
    val playerData = (
      player.nickname,
      player.token,
      player.actualPosition,
      STARTING_MONEY,
      STARTING_OWNED_PROPERTIES,
      false
    )
    assertEquals(player, Player.fromProduct(playerData))
