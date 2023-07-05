package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.Space

trait PurchasableSpace(
    override val name: String,
    val sellingPrice: Int,
    val rents: List[Int],
    val spaceGroup: SpaceGroup
) extends Space:

  def calculateRent: Int
