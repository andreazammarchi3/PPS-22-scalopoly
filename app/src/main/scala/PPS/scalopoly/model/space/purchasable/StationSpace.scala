package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.PurchasableSpace
import PPS.scalopoly.utils.GameUtils

class StationSpace(
    name: String,
    sellingPrice: Int,
    rents: List[Int],
    spaceGroup: SpaceGroup
) extends PurchasableSpace(name, sellingPrice, rents, spaceGroup):

  override def calculateRent: Int = rents(1)
  // TODO: implement calculateRent

object StationSpace:

  def apply(
      name: String,
      sellingPrice: Int,
      rents: List[Int],
      spaceGroup: SpaceGroup
  ): StationSpace = new StationSpace(name, sellingPrice, rents, spaceGroup)
