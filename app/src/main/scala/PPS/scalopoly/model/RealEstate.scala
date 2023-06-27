package PPS.scalopoly.model

/** Represents a real estate of the game.
 *
 * @param spaceName
 *   the space name of the real estate
 */
case class RealEstate(spaceName: SpaceName):

  val RENT_SELLING_PRICE_PERC = 10
  private var _owner: Option[Player] = None

  /** Returns the current player owner of the real estate.
   *
   * @return
   * the owner if it's set.
   */
  def owner: Option[Player] = _owner

  /** Let the player buy the real estate if it's possible
   *
   * @return
   * true if the purchase is successful, false otherwise
   */
  def isBoughtBy(player: Player): Boolean =
    if canBuy then
      _owner = Some(player)
      true
    else
      false

  /** Check if the real estate can be bought
   *
   * @return
   * true if the real estate can be bought, false otherwise
   */
  def canBuy: Boolean =
    _owner match
      case None => true
      case _ => false

  def calculateRent(): Int =
    spaceName.sellingPrice / RENT_SELLING_PRICE_PERC

