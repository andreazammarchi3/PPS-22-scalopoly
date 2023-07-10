package PPS.scalopoly.model.space

/** Represents a space in the board.
  * @param _name
  *   the name of the space
  */
class SpaceImpl(_name: String) extends Space:
  /** Returns the name of the space.
    * @return
    *   the name of the space
    */
  def name: String = _name

/** Companion object of the SpaceImpl class
  */
object SpaceImpl:
  /** Creates a space with the given name.
    * @param name
    *   the name of the space
    * @return
    *   the space created
    */
  def apply(name: String): SpaceImpl = new SpaceImpl(name)
