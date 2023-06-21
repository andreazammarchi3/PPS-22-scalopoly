package PPS.scalopoly.model

import PPS.scalopoly.utils.GameUtils

case class Player(nickname: String, token: Token, actualPosition: Int):

  def move(steps: Int): Player =
    val newPosition = GameUtils.addSumToPosition(steps, actualPosition)
    Player(nickname, token, newPosition)

object Player:
  def apply(nickname: String, token: Token): Player = Player(nickname, token, 0)
