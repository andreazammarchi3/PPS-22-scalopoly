package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.CompanySpace.{
  DOUBLE_MULTIPLIER,
  SINGLE_MULTIPLIER
}
import PPS.scalopoly.utils.GameUtils

class CompanySpace(
    name: String,
    sellingPrice: Int,
    rents: List[Int],
    spaceGroup: SpaceGroup
) extends PurchasableSpace(name, sellingPrice, rents, spaceGroup):

  override def calculateRent(): Int =
    GameUtils.checkIfPlayerOwnsAllPropertiesOfSameGroup(spaceGroup) match
      case true => DOUBLE_MULTIPLIER * rents(0)
      case _    => SINGLE_MULTIPLIER * rents(0)

object CompanySpace:

  private val SINGLE_MULTIPLIER = 4
  private val DOUBLE_MULTIPLIER = 10

  def apply(
      name: String,
      sellingPrice: Int,
      rents: List[Int],
      spaceGroup: SpaceGroup
  ): CompanySpace = new CompanySpace(name, sellingPrice, rents, spaceGroup)
