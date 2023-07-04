package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player

import scala.util.Random

class ChanceSpace(
    name: String,
    action: Player => Player
) extends NotPurchasableSpace(name, action)

object ChanceSpace:

  private val CHANGE_MONEY_FOR_ACTION = 100
  private def action(player: Player): Player =
    if (Random.nextBoolean()) player.pay(CHANGE_MONEY_FOR_ACTION)
    else player.cashIn(CHANGE_MONEY_FOR_ACTION)
  def apply(
      name: String
  ): ChanceSpace = new ChanceSpace(name, action)
