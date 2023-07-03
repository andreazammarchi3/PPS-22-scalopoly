package PPS.scalopoly.model.space

import PPS.scalopoly.model.Player

import scala.util.Random

class CommunityChestSpace(
    name: String,
    action: (Player, Int) => Player
) extends NotPurchasableSpace(name, action)

object CommunityChestSpace:

  private val COMMUNITY_CHEST_MONEY_FOR_ACTION = 100
  private def action(player: Player, position: Int): Player =
    player.cashIn(COMMUNITY_CHEST_MONEY_FOR_ACTION)
  def apply(
      name: String
  ): ChanceSpace = new ChanceSpace(name, action)
