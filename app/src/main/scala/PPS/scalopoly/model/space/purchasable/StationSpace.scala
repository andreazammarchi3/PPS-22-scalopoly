package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.PurchasableSpace
import PPS.scalopoly.utils.GameUtils

/** Represents a purchasable space of type Station.
  * @param name
  *   the name of the space
  * @param sellingPrice
  *   the price of the space
  * @param rents
  *   the rents of the space
  * @param spaceGroup
  *   the group of the space
  */
class StationSpace(
    name: String,
    sellingPrice: Int,
    rents: List[Int],
    spaceGroup: SpaceGroup
) extends PurchasableSpace(name, sellingPrice, rents, spaceGroup):

  override def calculateRent: Int =
    GameUtils.getOwnerFromPurchasableSpace(this) match
      case Some(owner) =>
        GameUtils.getNumStationFromOwner(spaceGroup, owner) match
          case 1 => rents(0)
          case 2 => rents(1)
          case 3 => rents(2)
          case 4 => rents(3)
          case _ => 0
      case _ => 0

/** Companion object for StationSpace class
  */
object StationSpace:

  /** Factory method for StationSpace class
    * @param name
    *   the name of the space
    * @param sellingPrice
    *   the price of the space
    * @param rents
    *   the rents of the space
    * @param spaceGroup
    *   the group of the space
    * @return
    *   a new StationSpace
    */
  def apply(
      name: String,
      sellingPrice: Int,
      rents: List[Int],
      spaceGroup: SpaceGroup
  ): StationSpace = new StationSpace(name, sellingPrice, rents, spaceGroup)
