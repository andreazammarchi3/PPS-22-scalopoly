package PPS.scalopoly.engine

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.model.*
import PPS.scalopoly.utils.{FxmlUtils, GameUtils}
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
    Game.players = Game.players.updated(
      Game.currentPlayer,
      currentPlayer.move(dice1 + dice2)
    )
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
    * @param player
    *   the player.
    * @return
    *   the name of the space where the player is.
    */
  def getSpaceNameFromPlayerPosition(player: Player): SpaceName =
    GameBoard.gameBoardList(player.actualPosition)

  def checkPlayerActions(): Unit =
    checkSpaceStatus match
      case SpaceStatus.OWNED_BY_ANOTHER_PLAYER =>
        playerPaysRent()
      case SpaceStatus.PURCHASABLE =>
        val result = FxmlUtils.showAlert(
          AlertType.CONFIRMATION,
          "Attenzione",
          "Acquista proprieta'",
          "Vuoi acquistare la proprieta' " + getSpaceNameFromPlayerPosition(
            currentPlayer
          ).name + " libera?"
        )
        result.get match
          case ButtonType.OK =>
            val purchasableSpace = getPurchasableSpaceFromCurrentPlayer
            if currentPlayer.canPayOrBuy(purchasableSpace.sellingPrice) then
              val newPlayer = currentPlayer.buy(purchasableSpace)
              Game.players = Game.players.updated(
                Game.players.indexOf(currentPlayer),
                newPlayer
              )
          case _ =>
      case _ =>

  private def checkSpaceStatus: SpaceStatus =
    val currentSpaceName = getSpaceNameFromPlayerPosition(currentPlayer)
    currentSpaceName match
      case spaceName if !spaceName.isPurchasable => SpaceStatus.NOT_PURCHASABLE
      case spaceName =>
        GameUtils.getPurchasableSpaceFromSpaceName(spaceName) match
          case purchasableSpace
              if !GameUtils.checkIfPropertyIsAlreadyOwned(purchasableSpace) =>
            SpaceStatus.PURCHASABLE
          case purchasableSpace =>
            purchasableSpace match
              case ownedSpace
                  if currentPlayer.ownedProperties.contains(purchasableSpace) =>
                SpaceStatus.OWNED_BY_CURRENT_PLAYER
              case ownedSpace => SpaceStatus.OWNED_BY_ANOTHER_PLAYER

  private def getPurchasableSpaceFromCurrentPlayer: PurchasableSpace =
    GameUtils.getPurchasableSpaceFromSpaceName(
      getSpaceNameFromPlayerPosition(currentPlayer)
    )

  private def playerPaysRent(): Unit =
    val rent = getPurchasableSpaceFromCurrentPlayer.calculateRent()
    // TODO: trace in UI
    if currentPlayer.canPayOrBuy(rent) then
      Game.players = Game.players.updated(
        Game.players.indexOf(currentPlayer),
        currentPlayer.pay(rent)
      )
      println(
        "Il giocatore " + currentPlayer.nickname + " paga " + rent + " di affitto al giocatore " + getOwnerFromPurchasableSpace(
          getPurchasableSpaceFromCurrentPlayer
        ).nickname
      )
    else Game.removePlayer(currentPlayer)

  def getOwnedPropertiesFromPlayer(token: Token): List[PurchasableSpace] =
    Game.players.find(_.token == token).get.ownedProperties

  private def getOwnerFromPurchasableSpace(
      purchasableSpace: PurchasableSpace
  ): Player =
    players
      .find(_.ownedProperties.contains(purchasableSpace))
      .getOrElse(throw new Exception("Proprieta' senza proprietario"))
