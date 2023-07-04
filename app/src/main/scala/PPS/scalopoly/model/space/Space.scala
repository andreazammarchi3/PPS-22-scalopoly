package PPS.scalopoly.model.space

trait Space:
  def name: String

sealed class SpaceImpl(_name: String) extends Space:
  def name: String = _name

object SpaceImpl:
  def apply(name: String): SpaceImpl = new SpaceImpl(name)
