package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.BuildableSpace.MAX_HOUSES
import PPS.scalopoly.utils.GameUtils

class BuildableSpace(
    name: String,
    sellingPrice: Int,
    rents: List[Int],
    spaceGroup: SpaceGroup,
    _numHouse: Int,
    _buildingCost: Int
) extends PurchasableSpace(name, sellingPrice, rents, spaceGroup):

  def numHouse: Int = _numHouse

  def buildingCost: Int = _buildingCost

  def buildHouse: BuildableSpace =
    if numHouse < MAX_HOUSES then
      BuildableSpace(
        name,
        sellingPrice,
        rents,
        spaceGroup,
        numHouse + 1,
        buildingCost
      )
    else this

  override def calculateRent: Int = numHouse match
    case _
        if numHouse == 0 && GameUtils.checkIfPlayerOwnsAllPropertiesOfSameGroup(
          spaceGroup
        ) =>
      rents(0) * 2
    case _ if numHouse == 0 => rents(0)
    case _                  => rents(numHouse)

object BuildableSpace:

  val MAX_HOUSES = 5

  def apply(
      name: String,
      sellingPrice: Int,
      rents: List[Int],
      spaceGroup: SpaceGroup,
      numHouse: Int,
      buildingCost: Int
  ): BuildableSpace = new BuildableSpace(
    name,
    sellingPrice,
    rents,
    spaceGroup,
    numHouse,
    buildingCost
  )
