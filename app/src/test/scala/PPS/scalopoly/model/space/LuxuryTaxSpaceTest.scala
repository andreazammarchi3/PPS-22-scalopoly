package PPS.scalopoly.model.space

import PPS.scalopoly.model.{Player, Token}
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class LuxuryTaxSpaceTest:

  private val LUXURY_TAX = 200
  private val LUXURY_TAX_SPACE_NAME = "Luxury tax"
  private val luxuryTaxSpace: LuxuryTaxSpace = LuxuryTaxSpace(
    LUXURY_TAX_SPACE_NAME
  )
  private val player: Player = Player("player", Token.CILINDRO)
  @Test
  def testAction(): Unit =
    val playerStartingMoney = player.money
    assertEquals(
      luxuryTaxSpace.action(player).money,
      playerStartingMoney - LUXURY_TAX
    )

  @Test
  def testName(): Unit =
    assertEquals(luxuryTaxSpace.name, LUXURY_TAX_SPACE_NAME)
