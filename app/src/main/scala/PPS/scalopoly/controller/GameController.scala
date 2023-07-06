package PPS.scalopoly.controller

import PPS.scalopoly.engine.{BotEngine, EndgameLogicEngine, GameEngine, PlayerActionsEngine}
import PPS.scalopoly.model.space.notPurchasable.{NotPurchasableSpace, NotPurchasableSpaceType}
import PPS.scalopoly.model.space.purchasable.{BuildableSpace, PurchasableSpace}
import PPS.scalopoly.model.{DiceManager, Player, SpaceStatus}
import PPS.scalopoly.utils.{AlertUtils, FxmlUtils, GameUtils}
import PPS.scalopoly.utils.resources.FxmlResources
import PPS.scalopoly.view.GameView
import javafx.scene.control.ButtonType

/** Controller for the [[PPS.scalopoly.view.GameView]].
  */
object GameController:

  private val didPlayerPassByGo: Int => Int => Boolean = oldPosition => newPosition => newPosition < oldPosition
  private var _view: GameView = _

  def view: GameView = _view

  def view_=(value: GameView): Unit =
    _view = value

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
      PlayerActionsEngine.playerPassByGo(GameEngine.currentPlayer)
    if GameEngine.botIsPlaying && view != null then view.diceThrown(dicePair._1, dicePair._2)
    dicePair

  /** End the turn of the current player.
    */
  def endTurn(): Unit =
    GameEngine.endTurn()
    if GameEngine.botIsPlaying && view != null then view.updateTurnLabel()

  /** Check which actions the current player can perform.
    */
  def checkPlayerActions(): Unit =
    val player = GameEngine.currentPlayer
    val purchasableSpace = GameUtils.getPurchasableSpaceFromPlayerPosition(player)
    GameEngine.checkSpaceStatus match
      case SpaceStatus.OWNED_BY_ANOTHER_PLAYER =>
        purchasableSpace.foreach(p => GameUtils.getOwnerFromPurchasableSpace(p).foreach(o => handleRent(player, p, o)))
      case SpaceStatus.PURCHASABLE => purchasableSpace.foreach(handlePurchase(player, _))
      case SpaceStatus.NOT_PURCHASABLE =>
        val notPurchasableSpace = GameUtils.getNotPurchasableSpaceFromPlayerPosition(player)
        notPurchasableSpace.foreach(handleNotPurchasableAction(player, _))
      case _ =>
    if EndgameLogicEngine.checkVictory() then showVictory()

  /** Check if the current player can build a house on the given
    * [[PPS.scalopoly.model.space.purchasable.BuildableSpace]].
    *
    * @param buildableSpace
    *   the [[PPS.scalopoly.model.space.purchasable.BuildableSpace]] on which the player wants to build a house.
    * @return
    *   true if the player can build a house on the given [[PPS.scalopoly.model.space.purchasable.BuildableSpace]],
    *   false otherwise.
    */
  def playerBuildsHouse(buildableSpace: BuildableSpace): Boolean =
    if GameEngine.currentPlayer.owns(buildableSpace) && buildableSpace.canBuildHouse
    then
      if GameEngine.currentPlayer.canAfford(buildableSpace.buildingCost) then
        if GameUtils.checkIfPlayerOwnsAllPropertiesOfSameGroup(buildableSpace.spaceGroup) then
          PlayerActionsEngine.playerBuildsHouse(GameEngine.currentPlayer, buildableSpace)
          true
        else
          if !GameEngine.botIsPlaying then
            AlertUtils.showPlayerDonNotOwnAllPropertiesOfSameGroup(GameEngine.currentPlayer, buildableSpace.spaceGroup)
          false
      else
        if !GameEngine.botIsPlaying then AlertUtils.showPlayerCannotBuyHouses(GameEngine.currentPlayer, buildableSpace)
        false
    else false

  private def handleRent(player: Player, purchasableSpace: PurchasableSpace, owner: Player): Unit =
    val rent = purchasableSpace.calculateRent
    if player.canAfford(rent) then
      if !GameEngine.botIsPlaying then AlertUtils.showRentPayment(player, rent, owner, purchasableSpace)
      PlayerActionsEngine.playerPaysRent(player, purchasableSpace, owner)
    else
      AlertUtils.showPlayerEliminated(player, owner)
      PlayerActionsEngine.playerObtainHeritage(owner, player)
      currentPlayerQuit()

  private def handlePurchase(player: Player, purchasableSpace: PurchasableSpace): Unit =
    if player.canAfford(purchasableSpace.sellingPrice) then
      if playerWantToBuySpace(player, purchasableSpace) then
        PlayerActionsEngine.playerBuysPurchasableSpace(player, purchasableSpace)
    else if !GameEngine.botIsPlaying then AlertUtils.showNotPurchasableSpace(player, purchasableSpace)

  private def handleNotPurchasableAction(player: Player, notPurchasableSpace: NotPurchasableSpace): Unit =
    notPurchasableSpace.spaceType match
      case NotPurchasableSpaceType.BLANK =>
      case _ =>
        if !GameEngine.botIsPlaying then
          AlertUtils.showNotPurchasableSpaceAction(
            player,
            notPurchasableSpace,
            PlayerActionsEngine.playerOnNotPurchasableSpace(player, notPurchasableSpace)
          )
        else PlayerActionsEngine.playerOnNotPurchasableSpace(player, notPurchasableSpace)

  private def playerWantToBuySpace(player: Player, purchasableSpace: PurchasableSpace): Boolean =
    if !GameEngine.botIsPlaying then
      val result = AlertUtils.showAskToBuyPurchasableSpace(player, purchasableSpace)
      result.get match
        case ButtonType.OK => true
        case _             => false
    else BotEngine.decideToBuySpace(purchasableSpace)

  private def showVictory(): Unit =
    GameEngine.winner.foreach(w =>
      AlertUtils.showVictory(w)
      GameEngine.newGame()
      FxmlUtils.changeScene(FxmlResources.START_MENU.path)
    )
