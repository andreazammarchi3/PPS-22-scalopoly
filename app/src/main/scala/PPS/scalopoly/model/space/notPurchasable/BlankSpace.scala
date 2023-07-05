package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player

class BlankSpace(
    name: String,
    spaceValue: Int
) extends NotPurchasableSpace(name, spaceValue):

  override def action(player: Player): Player = player

object BlankSpace:

  def apply(
      name: String,
      spaceValue: Int
  ): BlankSpace = new BlankSpace(name, spaceValue)
