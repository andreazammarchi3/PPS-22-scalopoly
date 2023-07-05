package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.Space

/** Trait that represents a purchasable space. A purchasable space is a space
  * that can be bought by a player.
  * @param name
  *   the name of the space
  * @param sellingPrice
  *   the price of the space
  * @param rents
  *   the rents of the space
  * @param spaceGroup
  *   the group of the space
  */
trait PurchasableSpace(
    override val name: String,
    val sellingPrice: Int,
    val rents: List[Int],
    val spaceGroup: SpaceGroup
) extends Space:

  /** Calculates the rent of the space.
    * @return
    *   the rent of the space
    */
  def calculateRent: Int
