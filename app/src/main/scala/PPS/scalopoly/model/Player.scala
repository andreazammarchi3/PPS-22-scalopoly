package PPS.scalopoly.model

import PPS.scalopoly.model.space.purchasable.PurchasableSpace
import PPS.scalopoly.utils.GameUtils

/** Represents a player of the game.
  * @param nickname
  *   the nickname of the player
  * @param token
  *   the token of the player
  * @param actualPosition
  *   the actual position of the player
  * @param money
  *   the money of the player
  * @param ownedProperties
  *   the list of [[PPS.scalopoly.model.space.purchasable.PurchasableSpace]] owned by the player
  * @param isBot
  *   true if the player is a bot, false otherwise
  */
case class Player(
    nickname: String,
    token: Token,
    actualPosition: Int,
    money: Int,
    ownedProperties: List[PurchasableSpace],
    isBot: Boolean
):

  /** Moves the player of the given steps.
    * @param steps
    *   the steps to move
    * @return
    *   the player with the new position
    */
  def move(steps: Int): Player =
    Player(nickname, token, GameUtils.addSumToPosition(steps, actualPosition), money, ownedProperties, isBot)

  /** Pays the given money.
    *
    * @param money
    *   the money to pay
    * @return
    *   the player with the new money
    */
  def pay(money: Int): Player =
    Player(nickname, token, actualPosition, this.money - money, ownedProperties, isBot)

  /** Buys the given purchasable space.
    *
    * @param purchasableSpace
    *   the purchasable space to buy
    * @return
    *   the player with the new money and the new owned properties
    */
  def buy(purchasableSpace: PurchasableSpace): Player =
    Player(
      nickname,
      token,
      actualPosition,
      this.money - purchasableSpace.sellingPrice,
      ownedProperties :+ purchasableSpace,
      isBot
    )

  /** Obtains money from a rent or from passing from the Go space.
    *
    * @param value
    *   the value of the rent or the value of the start
    * @return
    *   the player with the new money
    */
  def cashIn(value: Int): Player =
    Player(nickname, token, actualPosition, money + value, ownedProperties, isBot)

  /** Obtains the heritage from another player.
    *
    * @param otherPlayer
    *   the other player
    * @return
    *   the player with the new money and the new owned properties
    */
  def obtainHeritageFrom(otherPlayer: Player): Player =
    Player(
      nickname,
      token,
      actualPosition,
      money + otherPlayer.money,
      ownedProperties ++ otherPlayer.ownedProperties,
      isBot
    )

  /** Checks if the player can pay or buy something.
    * @param value
    *   the value to check
    * @return
    *   true if the player can pay or buy something, false otherwise
    */
  def canAfford(value: Int): Boolean = money >= value

  /** Checks if the player owns the given purchasable space.
    * @param purchasableSpace
    *   the purchasable space to check
    * @return
    *   true if the player owns the given purchasable space, false otherwise
    */
  def owns(purchasableSpace: PurchasableSpace): Boolean =
    ownedProperties.contains(purchasableSpace)

/** Companion object of the class [[Player]].
  */
object Player:

  private val DEFAULT_STARTING_POSITION = 0
  private val DEFAULT_STARTING_MONEY = 2000

  /** Creates a new player with the given nickname and token.
    * @param nickname
    *   the nickname of the player
    * @param token
    *   the token of the player
    * @return
    *   the new player
    */
  def apply(nickname: String, token: Token): Player =
    Player(nickname, token, DEFAULT_STARTING_POSITION, DEFAULT_STARTING_MONEY, List.empty, false)

  /** Creates a new player with the given nickname, token and isBot.
    * @param nickname
    *   the nickname of the player
    * @param token
    *   the token of the player
    * @param isBot
    *   true if the player is a bot, false otherwise
    */
  def apply(nickname: String, token: Token, isBot: Boolean): Player =
    Player(nickname, token, DEFAULT_STARTING_POSITION, DEFAULT_STARTING_MONEY, List.empty, isBot)
