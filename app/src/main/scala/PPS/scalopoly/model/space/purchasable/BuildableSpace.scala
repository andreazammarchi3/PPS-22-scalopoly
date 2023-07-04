package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.BuildableSpace.MAX_HOUSES
import PPS.scalopoly.utils.GameUtils

class BuildableSpace(
    name: String,
    sellingPrice: Int,
    rents: List[Int],
    spaceGroup: SpaceGroup,
    numHouse: Int,
    buildingCost: Int
) extends PurchasableSpace(name, sellingPrice, rents, spaceGroup):

  def buildHouse(): BuildableSpace =
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

  override def calculateRent(): Int = numHouse match
    case _
        if numHouse == 0 && GameUtils.checkIfPlayerOwnsAllPropertiesOfSameGroup(
          spaceGroup
        ) =>
      rents(0) * 2
    case _ if numHouse == 0 => rents(0)
    case _                  => rents(numHouse)

  override def toString: String =
    s"""
       |Name: $name
       |Selling price: $sellingPrice
       |Rents: $rents
       |Space group: $spaceGroup
       |Number of houses: $numHouse
       |Building cost: $buildingCost
       |""".stripMargin

object BuildableSpace:

  private val MAX_HOUSES = 5

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
