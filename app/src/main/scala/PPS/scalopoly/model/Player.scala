package PPS.scalopoly.model

case class Player(nickname: String, token: Token):

  private var _actualPosition: Int = 0

  def actualPosition: Int = _actualPosition

  def actualPosition_=(value: Int): Unit =
    _actualPosition = value
