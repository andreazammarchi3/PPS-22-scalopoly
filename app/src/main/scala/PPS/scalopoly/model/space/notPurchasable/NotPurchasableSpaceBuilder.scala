package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player
import scala.util.Random

/** Represents a not purchasable space builder. It's used to build a [[NotPurchasableSpace]]
  * @param name
  *   the name of the space of the [[NotPurchasableSpace]] to build
  * @param notPurchasableSpaceType
  *   the [[NotPurchasableSpaceType]] of the [[NotPurchasableSpace]] to build
  * @param spaceType
  *   the spaceValue of the [[NotPurchasableSpace]] to build
  * @param action
  *   the action associated with the space of the [[NotPurchasableSpace]] to build
  */
case class NotPurchasableSpaceBuilder(name: String, notPurchasableSpaceType: NotPurchasableSpaceType, spaceValue: Int):
  private def payAction(player: Player): (Player, Int) =
    val playerMoney = player.money
    val updatePlayer = player pay spaceValue
    (updatePlayer, updatePlayer.money - playerMoney)

  private def chanceAction(player: Player): (Player, Int) =
    val playerMoney = player.money
    val updatedPlayer =
      if Random.nextBoolean() then player pay spaceValue
      else player cashIn spaceValue
    (updatedPlayer, updatedPlayer.money - playerMoney)

  private def noAction(player: Player): (Player, Int) = (player, 0)

  /** Build the [[NotPurchasableSpace]]
    *
    * @return
    *   the [[NotPurchasableSpace]] builded
    */
  def build(): NotPurchasableSpace =
    val action = notPurchasableSpaceType match
      case NotPurchasableSpaceType.CHANCE          => chanceAction
      case NotPurchasableSpaceType.COMMUNITY_CHEST => payAction
      case NotPurchasableSpaceType.INCOME_TAX      => payAction
      case NotPurchasableSpaceType.LUXURY_TAX      => payAction
      case _                                       => noAction

    new NotPurchasableSpace(name, spaceValue, notPurchasableSpaceType, action)
