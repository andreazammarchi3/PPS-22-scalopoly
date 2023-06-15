package PPS.scalopoly.controller

import PPS.scalopoly.model.Token
import PPS.scalopoly.model.Player
import PPS.scalopoly.view.ConfigurationMenuView
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

import java.net.URL
import java.util

object ConfigurationMenuController:

  private var configurationMenuView: ConfigurationMenuView = _

  def setView(view: ConfigurationMenuView): Unit =
    configurationMenuView = view

  def addPayer(name: String, token: Token): Unit =
    val p0: Player = Player(name, token)
  def playGame(): Unit =
    FxmlUtils.changeScene(FxmlResources.GAME_VIEW.path)

  def exitGame(): Unit = sys.exit(0)