package PPS.scalopoly.model

import PPS.scalopoly.utils.GameUtils

import scalafx.beans.property.StringProperty
import scalafx.beans.property.ObjectProperty

/**
 * Represents a player of the game.
 * @param nickname the nickname of the player
 * @param token the token of the player
 * @param actualPosition the actual position of the player
 */
case class Player(nickname: String, token: Token, actualPosition: Int):

  private var _actualPosition: Int = 0

  val nickname_ = new StringProperty(this, "nickname", nickname)
  val token_ = new ObjectProperty[Token](this, "token", token)

  /**
   * Moves the player of the given steps.
   * @param steps the steps to move
   * @return the player with the new position
   */
  def move(steps: Int): Player =
    val newPosition = GameUtils.addSumToPosition(steps, actualPosition)
    Player(nickname, token, newPosition)

/**
 * Companion object of the class Player.
 */
object Player:
  /**
   * Creates a new player with the given nickname and token.
   * @param nickname the nickname of the player
   * @param token the token of the player
   * @return the new player
   */
  def apply(nickname: String, token: Token): Player = Player(nickname, token, 0)
