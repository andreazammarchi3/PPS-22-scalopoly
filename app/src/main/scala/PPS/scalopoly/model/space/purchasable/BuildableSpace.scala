package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.BuildableSpace.MAX_HOUSES
import PPS.scalopoly.utils.GameUtils

/** Represents a purchasable space that can be built on.
  * @param name
  *   the name of the space
  * @param sellingPrice
  *   the selling price of the space
  * @param rents
  *   the rents of the space
  * @param spaceGroup
  *   the group of the space
  * @param _numHouse
  *   the number of houses built on the space
  * @param _buildingCost
  *   the cost of building a house on the space
  */
class BuildableSpace(
    name: String,
    sellingPrice: Int,
    rents: List[Int],
    spaceGroup: SpaceGroup,
    _numHouse: Int,
    _buildingCost: Int
) extends PurchasableSpace(name, sellingPrice, rents, spaceGroup):

  /** The number of houses built on the space
    * @return
    *   the number of houses built on the space
    */
  def numHouse: Int = _numHouse

  /** The cost of building a house on the space
    * @return
    *   the cost of building a house on the space
    */
  def buildingCost: Int = _buildingCost

  /** Builds a house on the space
    * @return
    *   the space with a house built on it
    */
  def buildHouse: BuildableSpace =
    if numHouse < MAX_HOUSES then BuildableSpace(name, sellingPrice, rents, spaceGroup, numHouse + 1, buildingCost)
    else this

  /** Checks if a house can be built on the space
    * @return
    *   true if a house can be built on the space, false otherwise
    */
  def canBuildHouse: Boolean = numHouse < MAX_HOUSES

  override def calculateRent: Int = numHouse match
    case _ if numHouse == 0 && GameUtils.checkIfPlayerOwnsAllPropertiesOfSameGroup(spaceGroup) => rents(0) * 2
    case _ if numHouse == 0                                                                    => rents(0)
    case _                                                                                     => rents(numHouse)

/** Companion object for the BuildableSpace class
  */
object BuildableSpace:

  /** The maximum number of houses that can be built on a space
    */
  val MAX_HOUSES = 5

  /** Factory method for the BuildableSpace class
    * @param name
    *   the name of the space
    * @param sellingPrice
    *   the selling price of the space
    * @param rents
    *   the rents of the space
    * @param spaceGroup
    *   the group of the space
    * @param numHouse
    *   the number of houses built on the space
    * @param buildingCost
    *   the cost of building a house on the space
    * @return
    *   a new BuildableSpace
    */
  def apply(
      name: String,
      sellingPrice: Int,
      rents: List[Int],
      spaceGroup: SpaceGroup,
      numHouse: Int,
      buildingCost: Int
  ): BuildableSpace = new BuildableSpace(name, sellingPrice, rents, spaceGroup, numHouse, buildingCost)
