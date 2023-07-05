package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.CompanySpace.{
  DOUBLE_MULTIPLIER,
  SINGLE_MULTIPLIER
}
import PPS.scalopoly.utils.GameUtils

/** Represents a type of purchasable space that can be bought by a player.
  * @param name
  *   the name of the space
  * @param sellingPrice
  *   the price of the space
  * @param rents
  *   the rents of the space
  * @param spaceGroup
  *   the group of the space
  */
class CompanySpace(
    name: String,
    sellingPrice: Int,
    rents: List[Int],
    spaceGroup: SpaceGroup
) extends PurchasableSpace(name, sellingPrice, rents, spaceGroup):

  override def calculateRent: Int =
    GameUtils.checkIfPlayerOwnsAllPropertiesOfSameGroup(spaceGroup) match
      case true => DOUBLE_MULTIPLIER * rents(0)
      case _    => SINGLE_MULTIPLIER * rents(0)

/** Companion object to the CompanySpace class
  */
object CompanySpace:

  private val SINGLE_MULTIPLIER = 4
  private val DOUBLE_MULTIPLIER = 10

  /** Creates a CompanySpace
    * @param name
    *   the name of the space
    * @param sellingPrice
    *   the price of the space
    * @param rents
    *   the rents of the space
    * @param spaceGroup
    *   the group of the space
    * @return
    *   a new CompanySpace
    */
  def apply(
      name: String,
      sellingPrice: Int,
      rents: List[Int],
      spaceGroup: SpaceGroup
  ): CompanySpace = new CompanySpace(name, sellingPrice, rents, spaceGroup)
