package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.PurchasableSpace
import PPS.scalopoly.model.space.purchasable.StationSpace.MAX_NUM_STATIONS
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

  override def calculateRent: Int = GameUtils.getOwnerFromPurchasableSpace(this) match
    case Some(owner) =>
      GameUtils.getNumStationFromOwner(spaceGroup, owner) match
        case i if i <= MAX_NUM_STATIONS && i > 0 => rents(i - 1)
        case _                                   => 0
    case _ => 0

/** Companion object for StationSpace class
  */
object StationSpace:

  val MAX_NUM_STATIONS = 4

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
