package PPS.scalopoly.engine

import PPS.scalopoly.engine.GameReader.players
import PPS.scalopoly.model.Player
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import PPS.scalopoly.model.space.purchasable.{BuildableSpace, PurchasableSpace}

object PlayerActionsEngine:

  private val PASS_GO_MONEY = 200

  /** Player buys a purchasable space.
    *
    * @param player
    *   the player who buys the purchasable space.
    * @param purchasableSpace
    *   the purchasable space to buy.
    */
  def playerBuysPurchasableSpace(player: Player, purchasableSpace: PurchasableSpace): Unit =
    EngineUtils.updatePlayerWith(players.indexOf(player), player.buy(purchasableSpace))

  /** Player pays rent to the owner of a purchasable space.
    *
    * @param player
    *   the player who pays the rent.
    * @param purchasableSpace
    *   the purchasable space to pay the rent.
    * @param owner
    *   the owner of the purchasable space.
    */
  def playerPaysRent(player: Player, purchasableSpace: PurchasableSpace, owner: Player): Unit =
    val rent = purchasableSpace.calculateRent
    EngineUtils.updatePlayerWith(players.indexOf(owner), owner.cashIn(rent))
    EngineUtils.updatePlayerWith(players.indexOf(player), player.pay(rent))

  /** Player obtains a heritage from another player.
    *
    * @param receiver
    *   the player who gives the heritage.
    * @param giver
    *   the player who obtains the heritage.
    */
  def playerObtainHeritage(receiver: Player, giver: Player): Unit =
    EngineUtils.updatePlayerWith(players.indexOf(receiver), receiver.obtainHeritageFrom(giver))

  /** Player obtain the money from passing by Go.
    *
    * @param player
    *   the player who obtains the money.
    */
  def playerPassByGo(player: Player): Unit =
    EngineUtils.updatePlayerWith(players.indexOf(player), player.cashIn(PASS_GO_MONEY))

  /** Player builds a house on a buildable space.
    *
    * @param player
    *   the player who builds the house.
    * @param buildableSpace
    *   the buildable space where the house is built.
    */
  def playerBuildsHouse(player: Player, buildableSpace: BuildableSpace): Unit =
    EngineUtils.updateBuildableSpacesWith(buildableSpace.buildHouse)
    EngineUtils.updatePlayerWith(players.indexOf(player), player.pay(buildableSpace.buildingCost))

  /** Player is on a not purchasable space.
    *
    * @param player
    *   the player ont the not purchasable space.
    * @param notPurchasableSpace
    *   the not purchasable space.
    * @return
    *   new value of the player's money.
    */
  def playerOnNotPurchasableSpace(player: Player, notPurchasableSpace: NotPurchasableSpace): Int =
    val (resultPlayer: Player, moneyOperationResult: Int) = notPurchasableSpace.action(player)
    EngineUtils.updatePlayerWith(players.indexOf(player), resultPlayer)
    moneyOperationResult
