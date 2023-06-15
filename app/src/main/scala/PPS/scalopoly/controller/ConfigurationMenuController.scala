package PPS.scalopoly.controller

import PPS.scalopoly.view.ConfigurationMenuView
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

import java.net.URL
import java.util

object ConfigurationMenuController:

  private var configurationMenuView: ConfigurationMenuView = _

  def setView(view: ConfigurationMenuView): Unit =
    configurationMenuView = view

  def playGame(): Unit =
    FxmlUtils.changeScene(FxmlResources.GAME_VIEW.path)

  def exitGame(): Unit = sys.exit(0)