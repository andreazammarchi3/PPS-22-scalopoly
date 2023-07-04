package PPS.scalopoly.controller

import PPS.scalopoly.engine.{EndgameLogicEngine, GameEngine}
import PPS.scalopoly.model.space.purchasable.PurchasableSpace
import PPS.scalopoly.model.{DiceManager, Player, SpaceStatus}
import PPS.scalopoly.utils.{AlertUtils, FxmlUtils, GameUtils}
import PPS.scalopoly.utils.resources.FxmlResources
import javafx.scene.control.ButtonType

/** Controller for the [[PPS.scalopoly.view.GameView]].
  */
object GameController:

  private val didPlayerPassByGo: Int => Int => Boolean = oldPosition =>
    newPosition => newPosition < oldPosition

  /** Remove current player from the game.
    */
  def currentPlayerQuit(): Unit =
    GameEngine.currentPlayerQuit()
    if EndgameLogicEngine.checkVictory() then showVictory()

  /** Throw the dice and move the current player.
    *
    * @return
    *   the result of the dice roll.
    */
  def throwDice(): (Int, Int) =
    val dicePair = DiceManager().roll()
    val checkPassByGo = didPlayerPassByGo(
      GameEngine.currentPlayer.actualPosition
    )
    GameEngine.moveCurrentPlayer(dicePair._1 + dicePair._2)
    if checkPassByGo(GameEngine.currentPlayer.actualPosition) then
      GameEngine.playerPassByGo(GameEngine.currentPlayer)
    dicePair

  /** End the turn of the current player.
    */
  def endTurn(): Unit =
    GameEngine.endTurn()

  /** Check which actions the current player can perform.
    */
  def checkPlayerActions(): Unit =
    val player = GameEngine.currentPlayer
    val purchasableSpace =
      GameUtils.getPurchasableSpaceFromPlayerPosition(player)
    GameEngine.checkSpaceStatus match
      case SpaceStatus.OWNED_BY_ANOTHER_PLAYER =>
        purchasableSpace.foreach(p =>
          GameUtils
            .getOwnerFromPurchasableSpace(p)
            .foreach(o => handleRent(player, p, o))
        )
      case SpaceStatus.PURCHASABLE =>
        purchasableSpace.foreach(handlePurchase(player, _))
      case _ =>
    if EndgameLogicEngine.checkVictory() then showVictory()

  def playerBuildsHouse(space: String): Unit =
    GameUtils.getBuildableSpaceFromName(space).foreach(b =>
      if GameEngine.currentPlayer.canPayOrBuy(b.buildingCost) && GameEngine.currentPlayer.owns(b) && GameUtils.checkIfPlayerOwnsAllPropertiesOfSameGroup(b.spaceGroup) then
        GameEngine.playerBuildsHouse(GameEngine.currentPlayer, b)
    )

  private def handleRent(
      player: Player,
      purchasableSpace: PurchasableSpace,
      owner: Player
  ): Unit =
    val rent = purchasableSpace.calculateRent
    if player.canPayOrBuy(rent) then
      AlertUtils.showRentPayment(player, rent, owner, purchasableSpace)
      GameEngine.playerPaysRent(player, purchasableSpace, owner)
    else
      AlertUtils.showPlayerEliminated(player, owner)
      GameEngine.playerObtainHeritage(owner, player)
      currentPlayerQuit()

  private def handlePurchase(
      player: Player,
      purchasableSpace: PurchasableSpace
  ): Unit =
    if player.canPayOrBuy(purchasableSpace.sellingPrice) then
      if playerWantToBuySpace(player, purchasableSpace) then
        GameEngine.playerBuysPurchasableSpace(player, purchasableSpace)
    else AlertUtils.showNotPurchasableSpace(player, purchasableSpace)

  private def playerWantToBuySpace(
      player: Player,
      purchasableSpace: PurchasableSpace
  ): Boolean =
    val result = AlertUtils.showAskToBuyPurchasableSpace(
      player,
      purchasableSpace
    )
    result.get match
      case ButtonType.OK => true
      case _             => false

  private def showVictory(): Unit =
    GameEngine.winner.foreach(w =>
      AlertUtils.showVictory(w)
      GameEngine.newGame()
      FxmlUtils.changeScene(FxmlResources.START_MENU.path)
    )
