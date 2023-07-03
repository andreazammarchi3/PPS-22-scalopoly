package PPS.scalopoly.controller

import PPS.scalopoly.model.{Player, Token}
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

/** Controller for the configuration menu view.
  */
object ConfigurationMenuController:

  /** Add a player to the game.
    * @param player
    *   the player to add.
    */
  def addPlayer(player: Player): Unit =
    GameEngine.addPlayer(player)

  /** Remove a player from the game.
    * @param player
    *   the player to remove.
    */
  def removePlayer(player: Player): Unit =
    GameEngine.removePlayer(player)

  /** Get the available tokens.
    * @return
    *   the available tokens.
    */
  def availableToken(): List[Token] =
    GameEngine.availableTokens

  /** Get the available players.
    * @return
    *   the available players.
    */
  def availablePlayer(): List[Player] =
    GameEngine.players

  /** Check if a player can be added to the game.
    * @return
    *   true if a player can be added, false otherwise.
    */
  def canAddPlayer: Boolean =
    GameEngine.canAddPlayer

  /** Check if the game can start.
    * @return
    *   true if the game can start, false otherwise.
    */
  def canStartGame: Boolean =
    GameEngine.canStartGame

  /** Start the game.
    */
  def playGame(): Unit =
    GameEngine.startGame()
    FxmlUtils.changeScene(FxmlResources.GAME_VIEW.path)

  /** Exit the game.
    */
  def exitGame(): Unit = GameEngine.exitGame()
