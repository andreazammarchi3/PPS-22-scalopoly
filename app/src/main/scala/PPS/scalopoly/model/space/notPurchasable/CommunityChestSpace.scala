package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player
import PPS.scalopoly.model.space.notPurchasable.ChanceSpace

import scala.util.Random

class CommunityChestSpace(
    name: String,
    action: Player => Player
) extends NotPurchasableSpace(name, action)

object CommunityChestSpace:

  private val COMMUNITY_CHEST_MONEY_FOR_ACTION = 100
  private def action(player: Player): Player =
    player.cashIn(COMMUNITY_CHEST_MONEY_FOR_ACTION)
  def apply(
      name: String
  ): ChanceSpace = new ChanceSpace(name, action)
