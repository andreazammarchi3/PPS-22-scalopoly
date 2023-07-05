package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player

import scala.util.Random

class ChanceSpace(
    name: String,
    spaceValue: Int
) extends NotPurchasableSpace(name, spaceValue):

  override def action(player: Player): (Player, Int) =
    val playerMoney = player.money
    val updatedPlayer = if Random.nextBoolean() then
      player.pay(spaceValue)
    else
      player.cashIn(spaceValue)

    (updatedPlayer, updatedPlayer.money - playerMoney)

object ChanceSpace:

  def apply(
      name: String,
      spaceValue: Int
  ): ChanceSpace = new ChanceSpace(name, spaceValue)
