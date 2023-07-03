package PPS.scalopoly.model.space

import PPS.scalopoly.model.space.BuildableSpace.MAX_HOUSES
import PPS.scalopoly.model.space.{BuildableSpace, PurchasableSpace2}
import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.utils.GameUtils

class BuildableSpace(
    name: String,
    sellingPrice: Int,
    rents: List[Int],
    spaceGroup: SpaceGroup,
    numHouse: Int,
    buildingCost: Int
) extends PurchasableSpace2(name, sellingPrice, rents, spaceGroup):

  def buildHouse(): BuildableSpace =
    if numHouse < MAX_HOUSES then
      BuildableSpace(name, sellingPrice, rents, spaceGroup, numHouse + 1, buildingCost)
    else
      this

  override def calculateRent(): Int = numHouse match
    case _ if numHouse == 0 && GameUtils.checkIfPlayerOwnsAllPropertiesOfSameGroup(spaceGroup) => rents(0) * 2
    case _ if numHouse == 0 => rents(0)
    case _ => rents(numHouse)

object BuildableSpace:

  private val MAX_HOUSES = 5

  def apply(
      name: String,
      sellingPrice: Int,
      rents: List[Int],
      spaceGroup: SpaceGroup,
      numHouse: Int,
      buildingCost: Int
  ): BuildableSpace = new BuildableSpace(name, sellingPrice, rents, spaceGroup, numHouse, buildingCost)