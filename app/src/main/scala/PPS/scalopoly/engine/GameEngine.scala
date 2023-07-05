package PPS.scalopoly.engine

import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils
import PPS.scalopoly.engine.Game
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import PPS.scalopoly.model.space.purchasable.{BuildableSpace, PurchasableSpace}

/** Game engine that manages the [[Game]] and offers methods to interact with
  * it.
  */
object GameEngine:

  val MIN_PLAYERS = 2
  private val MAX_PLAYERS = 6
  private val PASS_GO_MONEY = 500

  def gameBoard: GameBoard = Game.gameBoard

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
    * @param steps
    *   the number of steps to move.
    */
  def moveCurrentPlayer(steps: Int): Unit =
    updatePlayerWith(Game.currentPlayer, currentPlayer.move(steps))

  /** Removes the current player from the game, if there is only one player left
    * the game ends.
    */
  def currentPlayerQuit(): Unit =
    val playerToDelete = currentPlayer
    endTurn()
    val nextPlayer = currentPlayer
    Game.removePlayer(playerToDelete)
    Game.currentPlayer = Game.players.indexOf(nextPlayer)

  /** Check the status of the space where the current player is.
    * @return
    *   the status of the space where the current player is.
    */
  def checkSpaceStatus: SpaceStatus =
    val purchasableSpace =
      GameUtils.getPurchasableSpaceFromPlayerPosition(currentPlayer)
    purchasableSpace match
      case Some(purchasableSpace) => checkPropertyStatus(purchasableSpace)
      case _                      => SpaceStatus.NOT_PURCHASABLE

  /** Player buys a purchasable space.
    *
    * @param player
    *   the player who buys the purchasable space.
    * @param purchasableSpace
    *   the purchasable space to buy.
    */
  def playerBuysPurchasableSpace(
      player: Player,
      purchasableSpace: PurchasableSpace
  ): Unit =
    updatePlayerWith(
      players.indexOf(player),
      player.buy(purchasableSpace)
    )

  /** Player pays rent to the owner of a purchasable space.
    *
    * @param player
    *   the player who pays the rent.
    * @param purchasableSpace
    *   the purchasable space to pay the rent.
    * @param owner
    *   the owner of the purchasable space.
    */
  def playerPaysRent(
      player: Player,
      purchasableSpace: PurchasableSpace,
      owner: Player
  ): Unit =
    val rent = purchasableSpace.calculateRent
    updatePlayerWith(
      players.indexOf(owner),
      owner.cashIn(rent)
    )
    updatePlayerWith(
      players.indexOf(player),
      player.pay(rent)
    )

  /** Player obtains a heritage from another player.
    *
    * @param receiver
    *   the player who gives the heritage.
    * @param giver
    *   the player who obtains the heritage.
    */
  def playerObtainHeritage(receiver: Player, giver: Player): Unit =
    updatePlayerWith(
      players.indexOf(receiver),
      receiver.obtainHeritageFrom(giver)
    )

  /** Player obtain the money from passing by Go.
    *
    * @param player
    *   the player who obtains the money.
    */
  def playerPassByGo(player: Player): Unit =
    updatePlayerWith(
      players.indexOf(player),
      player.cashIn(PASS_GO_MONEY)
    )

  def playerBuildsHouse(player: Player, buildableSpace: BuildableSpace): Unit =
    updateBuildableSpacesWith(buildableSpace.buildHouse)
    updatePlayerWith(
      players.indexOf(player),
      player.pay(buildableSpace.buildingCost)
    )

  /** Player is on a not purchasable space.
    *
    * @param player
    *   the player ont the not purchasable space.
    * @param notPurchasableSpace
    *   the not purchasable space.
    */
  def playerOnNotPurchasableSpace(
      player: Player,
      notPurchasableSpace: NotPurchasableSpace
  ): Unit =
    updatePlayerWith(
      players.indexOf(player),
      notPurchasableSpace.action(player)
    )
  private def checkPropertyStatus(
      purchasableSpace: PurchasableSpace
  ): SpaceStatus = purchasableSpace match
    case purchasableSpace
        if GameUtils.propertyIsAlreadyOwned(purchasableSpace) =>
      purchasableSpace match
        case _ if currentPlayer.owns(purchasableSpace) =>
          SpaceStatus.OWNED_BY_CURRENT_PLAYER
        case _ => SpaceStatus.OWNED_BY_ANOTHER_PLAYER
    case _ => SpaceStatus.PURCHASABLE

  private def updatePlayerWith(index: Int, playerUpdated: Player): Unit =
    Game.players = Game.players.updated(
      index,
      playerUpdated
    )

  private def updateBuildableSpacesWith(updatedSpace: BuildableSpace): Unit =
    Game.gameBoard = gameBoard.updateBuildableSpace(updatedSpace)
