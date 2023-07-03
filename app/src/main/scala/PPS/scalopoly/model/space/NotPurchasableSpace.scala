package PPS.scalopoly.model.space

import PPS.scalopoly.model.Player
trait NotPurchasableSpace(
    override val name: String,
    val action: (Player, Int) => Player
) extends Space
