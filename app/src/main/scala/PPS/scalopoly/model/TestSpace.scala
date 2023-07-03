package PPS.scalopoly.model

class TestSpace(private val _name:  String) extends Space:
  override def name: String = _name
object TestSpace:
  def apply(name: String): TestSpace = new TestSpace(name)
