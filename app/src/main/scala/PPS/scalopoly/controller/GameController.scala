package PPS.scalopoly.controller

import PPS.scalopoly.engine.{BotEngine, EndgameLogicEngine, GameEngine, GameReader, PlayerActionsEngine}
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
      GameReader.currentPlayer.actualPosition
    )
    GameEngine.moveCurrentPlayer(dicePair._1 + dicePair._2)
    if checkPassByGo(GameReader.currentPlayer.actualPosition) then
      PlayerActionsEngine playerPassByGo GameReader.currentPlayer
    if GameReader.botIsPlaying && view != null then view.diceThrown(dicePair._1, dicePair._2)
    dicePair

  /** End the turn of the current player.
    */
  def endTurn(): Unit =
    GameEngine.endTurn()
    if GameReader.botIsPlaying && view != null then view.updateTurnLabel()

  /** Check which actions the current player can perform.
    */
  def checkPlayerActions(): Unit =
    val player = GameReader.currentPlayer
    val purchasableSpace = GameUtils getPurchasableSpaceFromPlayerPosition player
    GameEngine.checkSpaceStatus match
      case SpaceStatus.OWNED_BY_ANOTHER_PLAYER =>
        purchasableSpace.foreach(p => GameUtils.getOwnerFromPurchasableSpace(p).foreach(o => handleRent(player, p, o)))
      case SpaceStatus.PURCHASABLE => purchasableSpace.foreach(handlePurchase(player, _))
      case SpaceStatus.NOT_PURCHASABLE =>
        val notPurchasableSpace = GameUtils getNotPurchasableSpaceFromPlayerPosition player
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
    if (GameReader.currentPlayer owns buildableSpace) && buildableSpace.canBuildHouse
    then
      if GameReader.currentPlayer canAfford buildableSpace.buildingCost then
        if GameUtils checkIfPlayerOwnsAllPropertiesOfSameGroup buildableSpace.spaceGroup then
          PlayerActionsEngine.playerBuildsHouse(GameReader.currentPlayer, buildableSpace)
          true
        else
          if !GameReader.botIsPlaying then
            AlertUtils.showPlayerDonNotOwnAllPropertiesOfSameGroup(GameReader.currentPlayer, buildableSpace.spaceGroup)
          false
      else
        if !GameReader.botIsPlaying then AlertUtils.showPlayerCannotBuyHouses(GameReader.currentPlayer, buildableSpace)
        false
    else false

  private def handleRent(player: Player, purchasableSpace: PurchasableSpace, owner: Player): Unit =
    val rent = purchasableSpace.calculateRent
    if player canAfford rent then
      if !GameReader.botIsPlaying then AlertUtils.showRentPayment(player, rent, owner, purchasableSpace)
      PlayerActionsEngine.playerPaysRent(player, purchasableSpace, owner)
    else
      AlertUtils.showPlayerEliminatedByRent(player, owner)
      PlayerActionsEngine.playerObtainHeritage(owner, player)
      currentPlayerQuit()

  private def handlePurchase(player: Player, purchasableSpace: PurchasableSpace): Unit =
    if player canAfford purchasableSpace.sellingPrice then
      if playerWantToBuySpace(player, purchasableSpace) then
        PlayerActionsEngine.playerBuysPurchasableSpace(player, purchasableSpace)
    else if !GameReader.botIsPlaying then AlertUtils.showNotPurchasableSpace(player, purchasableSpace)

  private def handleNotPurchasableAction(player: Player, notPurchasableSpace: NotPurchasableSpace): Unit =
    notPurchasableSpace.spaceType match
      case NotPurchasableSpaceType.BLANK =>
      case NotPurchasableSpaceType.CHANCE =>
        val actionResult = PlayerActionsEngine.playerOnNotPurchasableSpace(player, notPurchasableSpace)
        if GameReader.currentPlayer.money < 0 then
          AlertUtils.showPlayerEliminatedByTax(player, notPurchasableSpace.spaceValue)
          currentPlayerQuit()
        else if !GameReader.botIsPlaying then
          AlertUtils.showNotPurchasableSpaceAction(
            player,
            notPurchasableSpace,
            actionResult
          )
      case _ =>
        if player canAfford notPurchasableSpace.spaceValue then
          val actionResult = PlayerActionsEngine.playerOnNotPurchasableSpace(player, notPurchasableSpace)
          if !GameReader.botIsPlaying then
            AlertUtils.showNotPurchasableSpaceAction(
              player,
              notPurchasableSpace,
              actionResult
            )
        else
          AlertUtils.showPlayerEliminatedByTax(player, notPurchasableSpace.spaceValue)
          currentPlayerQuit()

  private def playerWantToBuySpace(player: Player, purchasableSpace: PurchasableSpace): Boolean =
    if !GameReader.botIsPlaying then
      val result = AlertUtils.showAskToBuyPurchasableSpace(player, purchasableSpace)
      result.get match
        case ButtonType.OK => true
        case _             => false
    else BotEngine decideToBuySpace purchasableSpace

  private def showVictory(): Unit =
    GameReader.winner.foreach(w =>
      AlertUtils.showVictory(w)
      GameEngine.newGame()
      FxmlUtils.changeScene(FxmlResources.START_MENU.path)
    )
