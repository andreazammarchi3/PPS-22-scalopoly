package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.Player
import PPS.scalopoly.model.space.Space

/** Represents a not purchasable space. A not purchasable space is a special space with a specific action associated with it.
 * @param name
 *   the name of the space
 * @param spaceValue
 *   the value of the space used by the action
 * @param spaceType
 *   the space type
 * @param action
 *   the action associated with the space
 */
class NotPurchasableSpace(
    override val name: String,
    val spaceValue: Int,
    val spaceType: NotPurchasableSpaceType,
    val action: (player: Player) => (Player, Int)
) extends Space

/** Companion object to the NotPurchasableSpace class
 */
object NotPurchasableSpace:

  /** Creates a NotPurchasableSpace
   *
   * @param name
   * the name of the space
   * @param spaceValue
   * the value of the space used by the action
   * @param spaceType
   * the space type
   * @param action
   * the action associated with the space
   * @return
   * a new NotPurchasableSpace
   */
  def apply(
             name: String,
             spaceValue: Int,
             spaceType: NotPurchasableSpaceType,
             action: (player: Player) => (Player, Int)
           ): NotPurchasableSpace = new NotPurchasableSpace(name, spaceValue, spaceType, action)
