package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player
import PPS.scalopoly.model.space.Space
trait NotPurchasableSpace(
    override val name: String,
    val action: (Player, Int) => Player
) extends Space
