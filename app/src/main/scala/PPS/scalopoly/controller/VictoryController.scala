package PPS.scalopoly.controller

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

/** Controller for the victory view.
 */
object VictoryController:

  /** Go back to the start menu.
   */
  def backToMenu():Unit =
    GameEngine.newGame()
    FxmlUtils.changeScene(FxmlResources.START_MENU.path)

  /** Exit the game.
   */
  def exitGame(): Unit = GameEngine.exitGame()
