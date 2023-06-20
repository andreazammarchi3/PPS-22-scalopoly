package PPS.scalopoly.model

case class Player(nickname: String, token: Token, actualPosition: Int):

  def move(steps: Int): Player =
    val newPosition = actualPosition + steps
    Player(nickname, token, newPosition)

object Player:
  def apply(nickname: String, token: Token): Player = Player(nickname, token, 0)
