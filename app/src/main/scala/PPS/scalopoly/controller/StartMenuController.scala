package PPS.scalopoly.controller


import PPS.scalopoly.view.StartMenuView
import PPS.scalopoly.utils.resources.FxmlResources
import PPS.scalopoly.utils.FxmlUtils

object StartMenuController:

  private var startMenuView: StartMenuView = _

  def setView(view: StartMenuView): Unit =
    startMenuView = view

  def playGame() : Unit =
    FxmlUtils.changeScene(FxmlResources.CONFIGURATION_MENU.path)

  def exitGame() : Unit = sys.exit(0)
