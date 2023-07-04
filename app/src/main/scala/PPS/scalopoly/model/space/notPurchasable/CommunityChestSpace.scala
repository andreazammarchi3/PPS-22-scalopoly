package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player
import PPS.scalopoly.model.space.notPurchasable.ChanceSpace

import scala.util.Random

class CommunityChestSpace(
    name: String,
    spaceValue: Int
) extends NotPurchasableSpace(name, spaceValue):

  override def action(player: Player): Player =
    player.pay(spaceValue)

object CommunityChestSpace:

  def apply(
      name: String,
      spaceValue: Int
  ): ChanceSpace = new ChanceSpace(name, spaceValue)
