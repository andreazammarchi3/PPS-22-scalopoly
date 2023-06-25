package PPS.scalopoly.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue, assertFalse}

class TestRealEstate:

  private val REAL_ESTATE_SELLING_PRICE = 100

  private val realEstateVia: RealEstate = RealEstate(SpaceName.VIA, REAL_ESTATE_SELLING_PRICE)
  private val player1: Player = Player("Player1", Token.CILINDRO)
  private val player2: Player = Player("Player2", Token.CILINDRO)

  @Test
  def testCanBuy(): Unit =
    assertTrue(realEstateVia.canBuy)

  @Test
  def testBuy(): Unit =
    assertTrue(realEstateVia.buy(player1))

  @Test
  def testBuyCantBuy(): Unit =
    realEstateVia.buy(player1)
    assertFalse(realEstateVia.buy(player2))

  @Test
  def testCalculateRent(): Unit =
    assertEquals(realEstateVia.sellingPrice / realEstateVia.RENT_SELLING_PRICE_PERC, realEstateVia.calculateRent())



