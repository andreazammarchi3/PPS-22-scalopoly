package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player

import scala.util.Random

class LuxuryTaxSpace(
    name: String,
    action: Player => Player
) extends NotPurchasableSpace(name, action)

object LuxuryTaxSpace:

  private val LUXURY_TAX = 200
  private def action(player: Player): Player =
    player.pay(LUXURY_TAX)
  def apply(
      name: String
  ): LuxuryTaxSpace = new LuxuryTaxSpace(name, action)