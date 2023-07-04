package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.{Player, Token}
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class IncomeTaxSpaceTest:

  private val INCOME_TAX_PERC = 10
  private val INCOME_TAX_SPACE_NAME = "Income tax"
  private val incomeTaxSpace: IncomeTaxSpace = IncomeTaxSpace(
    INCOME_TAX_SPACE_NAME
  )
  private val player: Player = Player("player", Token.CILINDRO)
  @Test
  def testAction(): Unit =
    // TODO
    assertTrue(true)

  @Test
  def testName(): Unit =
    assertEquals(incomeTaxSpace.name, INCOME_TAX_SPACE_NAME)
