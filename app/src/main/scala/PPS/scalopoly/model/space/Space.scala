package PPS.scalopoly.model.space

trait Space:
  def name: String

  override def equals(obj: Any): Boolean = obj match
    case space: Space => space.name == this.name
    case _            => false
