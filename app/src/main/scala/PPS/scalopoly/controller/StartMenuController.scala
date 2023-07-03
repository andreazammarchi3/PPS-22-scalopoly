package PPS.scalopoly.controller

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

/** Controller for the [[PPS.scalopoly.view.StartMenuView]]
  */
object StartMenuController:

  /** Enter the configuration menu.
    */
  def playGame(): Unit =
    FxmlUtils.changeScene(FxmlResources.CONFIGURATION_MENU.path)

  /** Exit the game.
    */
  def exitGame(): Unit = GameEngine.exitGame()
