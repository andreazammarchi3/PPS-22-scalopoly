package PPS.scalopoly.model

import PPS.scalopoly.utils.GameUtils

import scalafx.beans.property.StringProperty
import scalafx.beans.property.ObjectProperty

case class Player(nickname: String, token: Token, actualPosition: Int):

  private var _actualPosition: Int = 0

  val nickname_ = new StringProperty(this, "nickname", nickname)
  val token_ = new ObjectProperty[Token](this, "token", token)

  def move(steps: Int): Player =
    val newPosition = GameUtils.addSumToPosition(steps, actualPosition)
    Player(nickname, token, newPosition)

object Player:
  def apply(nickname: String, token: Token): Player = Player(nickname, token, 0)
