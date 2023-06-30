package PPS.scalopoly.engine

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.model.*
import PPS.scalopoly.utils.{AlertUtils, GameUtils}
import PPS.scalopoly.engine.Game
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType

import java.util.Optional
import scala.util.Random

/** Object that represents the game engine.
  */
object GameEngine:

  val MIN_PLAYERS = 2
  private val MAX_PLAYERS = 6

  private val diceManager: DiceManager = DiceManager()

  /** Returns the list of players.
    * @return
    *   the list of players.
    */
  def players: List[Player] = Game.players

  /** Returns the current player.
    * @return
    *   the current player.
    */
  def currentPlayer: Player = players(Game.currentPlayer)

  /** Returns the list of available tokens.
    * @return
    *   the list of available tokens.
    */
  def availableTokens: List[Token] =
    Game.availableTokens

  /** Returns the winner of the game.
    * @return
    *   the winner of the game.
    */
  def winner: Option[Player] = Game.winner

  /** Adds a player to the game.
    * @param player
    *   the player to add.
    */
  def addPlayer(player: Player): Unit =
    Game.addPlayer(player)

  /** Removes a player from the game.
    * @param player
    *   the player to remove.
    */
  def removePlayer(player: Player): Unit =
    Game.removePlayer(player)

  /** Check if the game can start, at least 2 players are needed.
    * @return
    *   true if the game can start, false otherwise.
    */
  def canStartGame: Boolean =
    Game.players.length match
      case i if i < MIN_PLAYERS => false
      case _                    => true

  /** Check if a player can be added to the game, at most 6 players are allowed.
    * @return
    *   true if a player can be added, false otherwise.
    */
  def canAddPlayer: Boolean =
    Game.players.length match
      case i if i < MAX_PLAYERS => true
      case _                    => false

  /** Starts the game and shuffles the players.
    */
  def startGame(): Unit =
    Game.players = GameUtils.shufflePlayers(Game.players)

  /** Resets the game.
    */
  def newGame(): Unit =
    Game.reset()

  /** Exits the game.
    */
  def exitGame(): Unit =
    sys.exit(0)

  /** Ends the turn of the current player.
    */
  def endTurn(): Unit =
    Game.currentPlayer = (Game.currentPlayer + 1) % Game.players.length

  /** Moves the current player.
    * @return
    *   the result of the dice roll.
    */
  def moveCurrentPlayer(): (Int, Int) =
    val (dice1, dice2) = diceManager.roll()
    updateCurrentPlayerWith(currentPlayer.move(dice1 + dice2))
    (dice1, dice2)

  /** Removes the current player from the game, if there is only one player left
    * the game ends.
    */
  def currentPlayerQuit(): Unit =
    val playerToDelete = currentPlayer
    endTurn()
    val nextPlayer = currentPlayer
    Game.removePlayer(playerToDelete)
    Game.currentPlayer = Game.players.indexOf(nextPlayer)

  /** Returns the name of the space where the player is.
    *
    * @param player
    *   the player.
    * @return
    *   the name of the space where the player is.
    */
  def getSpaceNameFromPlayerPosition(player: Player): SpaceName =
    GameBoard.gameBoardList(player.actualPosition)

  /** Verifies if the current space is purchasable and if it is, asks the player
    * if he wants to buy it. If the space is owned by another player, the
    * current player pays the rent.
    */
  def checkPlayerActions(): Unit =
    checkSpaceStatus match
      case SpaceStatus.OWNED_BY_ANOTHER_PLAYER =>
        playerPaysRent()
      case SpaceStatus.PURCHASABLE =>
        askPlayerToBuySpace()
      case _ =>

  private def checkSpaceStatus: SpaceStatus =
    val currentSpaceName = getSpaceNameFromPlayerPosition(currentPlayer)
    GameUtils.getPurchasableSpaceFromSpaceName(currentSpaceName) match
      case Some(purchasableSpace) => checkPropertyStatus(purchasableSpace)
      case _                      => SpaceStatus.NOT_PURCHASABLE

  private def checkPropertyStatus(
      purchasableSpace: PurchasableSpace
  ): SpaceStatus = purchasableSpace match
    case purchasableSpace
        if GameUtils.propertyIsAlreadyOwned(purchasableSpace) =>
      purchasableSpace match
        case _ if currentPlayer.owns(purchasableSpace) =>
          SpaceStatus.OWNED_BY_CURRENT_PLAYER
        case _ => SpaceStatus.OWNED_BY_ANOTHER_PLAYER
    case _ =>
      SpaceStatus.PURCHASABLE

  private def playerPaysRent(): Unit =
    val purchasableSpace = GameUtils.getPurchasableSpaceFromSpaceName(
      getSpaceNameFromPlayerPosition(currentPlayer)
    )
    purchasableSpace match
      case Some(purchasableSpace) =>
        val owner = GameUtils.getOwnerFromPurchasableSpace(purchasableSpace)
        owner match
          case Some(owner) =>
            val rent = purchasableSpace.calculateRent()
            if currentPlayer.canPayOrBuy(rent) then
              AlertUtils.showRentPayment(
                currentPlayer,
                rent,
                owner,
                purchasableSpace
              )
              updatePlayerWith(
                players.indexOf(owner),
                owner.takeRent(rent)
              )
              updateCurrentPlayerWith(
                currentPlayer.pay(rent)
              )
            else
              AlertUtils.showPlayerEliminated(currentPlayer, owner)
              updatePlayerWith(
                players.indexOf(owner),
                owner.obtainHeritageFrom(currentPlayer)
              )
              currentPlayerQuit()
          case _ =>
      case _ =>

  private def askPlayerToBuySpace(): Unit =
    val purchasableSpace = GameUtils.getPurchasableSpaceFromSpaceName(
      getSpaceNameFromPlayerPosition(currentPlayer)
    )
    purchasableSpace match
      case Some(purchasableSpace) =>
        if currentPlayer.canPayOrBuy(purchasableSpace.sellingPrice) then
          val result = AlertUtils.showAskToBuyPurchasableSpace(
            currentPlayer,
            purchasableSpace
          )
          result.get match
            case ButtonType.OK =>
              updateCurrentPlayerWith(currentPlayer.buy(purchasableSpace))
            case _ =>
        else AlertUtils.showNotPurchasableSpace(currentPlayer, purchasableSpace)
      case _ =>

  private def updateCurrentPlayerWith(playerUpdated: Player): Unit =
    Game.players = Game.players.updated(
      Game.currentPlayer,
      playerUpdated
    )

  private def updatePlayerWith(index: Int, playerUpdated: Player): Unit =
    Game.players = Game.players.updated(
      index,
      playerUpdated
    )
