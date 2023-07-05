package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player
import PPS.scalopoly.model.space.notPurchasable.ChanceSpace

import scala.util.Random

class CommunityChestSpace(
    name: String,
    spaceValue: Int
) extends NotPurchasableSpace(name, spaceValue):

  override def action(player: Player): (Player, Int) =
    val playerMoney = player.money
    val updatePlayer = player.pay(spaceValue)
    (updatePlayer, updatePlayer.money - playerMoney)

object CommunityChestSpace:

  def apply(
      name: String,
      spaceValue: Int
  ): CommunityChestSpace = new CommunityChestSpace(name, spaceValue)
