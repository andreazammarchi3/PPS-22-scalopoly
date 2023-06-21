package PPS.scalopoly.model

import scalafx.beans.property.StringProperty
import scalafx.beans.property.ObjectProperty

case class Player(nickname: String, token: Token):

  private var _actualPosition: Int = 0

  val nickname_ = new StringProperty(this, "nickname", nickname)
  val token_ = new ObjectProperty[Token](this, "token", token)
  def actualPosition: Int = _actualPosition

  def actualPosition_=(value: Int): Unit =
    _actualPosition = value
