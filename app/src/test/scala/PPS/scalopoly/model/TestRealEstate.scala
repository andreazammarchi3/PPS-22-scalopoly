package PPS.scalopoly.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue, assertFalse}

class TestRealEstate:

  private val realEstateVia: RealEstate = RealEstate(SpaceName.VIA)
  private val player1: Player = Player("Player1", Token.CILINDRO)
  private val player2: Player = Player("Player2", Token.CILINDRO)

  @Test
  def testCanBuy(): Unit =
    assertTrue(realEstateVia.canBuy)

  @Test
  def testBuy(): Unit =
    assertTrue(realEstateVia.isBoughtBy(player1))

  @Test
  def testBuyCantBuy(): Unit =
    realEstateVia.isBoughtBy(player1)
    assertFalse(realEstateVia.isBoughtBy(player2))

  @Test
  def testCalculateRent(): Unit =
    assertEquals(
      realEstateVia.spaceName.sellingPrice / realEstateVia.RENT_SELLING_PRICE_PERC,
      realEstateVia.calculateRent()
    )
