package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player

import scala.util.Random

class ChanceSpace(
    name: String,
    spaceValue: Int
) extends NotPurchasableSpace(name, spaceValue):

  override def action(player: Player): Player =
    if (Random.nextBoolean()) player.pay(spaceValue)
    else player.cashIn(spaceValue)

object ChanceSpace:

  def apply(
      name: String,
      spaceValue: Int
  ): ChanceSpace = new ChanceSpace(name, spaceValue)
