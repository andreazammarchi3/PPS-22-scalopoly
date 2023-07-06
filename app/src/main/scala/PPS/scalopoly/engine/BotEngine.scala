package PPS.scalopoly.engine

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.model.space.purchasable.PurchasableSpace

import scala.annotation.tailrec

/** Object that represents the bot engine. It is responsible for the bot's actions.
  */
object BotEngine:
  /** Method that starts the bot's turn.
    */
  def play(): Unit =
    println(s"${GameEngine.currentPlayer.nickname} plays")
    checkOptions()
    GameController.endTurn()

  /** Method that decides whether to buy a purchasable space or not.
    * @param purchasableSpace
    *   the purchasable space to buy
    * @return
    *   true if the bot decides to buy the space, false otherwise
    */
  def decideToBuySpace(purchasableSpace: PurchasableSpace): Boolean =
    println(s"${GameEngine.currentPlayer.nickname} decides to buy ${purchasableSpace.name}")
    true

  @tailrec
  private def checkOptions(): Unit =
    println(s"${GameEngine.currentPlayer.nickname} checks options")
    if canThrowDice(GameEngine.currentPlayer.actualPosition) then
      val (dice1, dice2) = GameController.throwDice()
      println(s"${GameEngine.currentPlayer.nickname} throws dice: $dice1, $dice2")
      if dice1 == dice2 then checkOptions()
    else GameController.endTurn()

  private def canThrowDice(startingPosition: Int): Boolean =
    GameEngine.currentPlayer.actualPosition == startingPosition
