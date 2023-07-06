package PPS.scalopoly.model.space

/** A trait that represents a space of the board. A space is a position on the board that can be occupied by a player.
  * @see
  *   [[PPS.scalopoly.model.board.Board]]
  */
trait Space:
  /** The name of the space. */
  def name: String

  override def equals(obj: Any): Boolean = obj match
    case space: Space => space.name == this.name
    case _            => false
