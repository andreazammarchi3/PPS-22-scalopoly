package PPS.scalopoly.controller

import PPS.scalopoly.model.{Player, Token}
import PPS.scalopoly.engine.{GameEngine, GameReader}
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

/** Controller for the [[PPS.scalopoly.view.ConfigurationMenuView]].
  */
object ConfigurationMenuController:

  /** Add a player to the game.
    * @param player
    *   the player to add.
    */
  def addPlayer(player: Player): Unit =
    GameEngine addPlayer player

  /** Remove a player from the game.
    * @param player
    *   the player to remove.
    */
  def removePlayer(player: Player): Unit =
    GameEngine removePlayer player

  /** Get the available tokens.
    * @return
    *   the available tokens.
    */
  def availableToken(): List[Token] =
    GameReader.availableTokens

  /** Check if a player can be added to the game.
    * @return
    *   true if a player can be added, false otherwise.
    */
  def canAddPlayer: Boolean =
    GameReader.canAddPlayer

  /** Check if the game can start.
    * @return
    *   true if the game can start, false otherwise.
    */
  def canStartGame: Boolean =
    GameReader.canStartGame

  /** Start the game.
    */
  def playGame(): Unit =
    GameEngine.startGame()
    FxmlUtils.changeScene(FxmlResources.GAME_VIEW.path)
    if GameReader.currentPlayer.isBot then GameEngine.botPlays()

  /** Exit the game.
    */
  def exitGame(): Unit =
    GameEngine.exitGame()
