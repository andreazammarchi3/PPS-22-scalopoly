package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player

import scala.util.Random

class LuxuryTaxSpace(
    name: String,
    spaceValue: Int
) extends NotPurchasableSpace(name, spaceValue):

  override def action(player: Player): (Player, Int) =
    val playerMoney = player.money
    val updatePlayer = player.pay(spaceValue)
    (updatePlayer, updatePlayer.money - playerMoney)

object LuxuryTaxSpace:

  def apply(
      name: String,
      spaceValue: Int
  ): LuxuryTaxSpace = new LuxuryTaxSpace(name, spaceValue)
