package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player

import scala.util.Random

class LuxuryTaxSpace(
    name: String,
    spaceValue: Int
) extends NotPurchasableSpace(name, spaceValue):

  override def action(player: Player): Player =
    player.pay(spaceValue)

object LuxuryTaxSpace:

  def apply(
      name: String,
      spaceValue:Int
  ): LuxuryTaxSpace = new LuxuryTaxSpace(name, spaceValue)
