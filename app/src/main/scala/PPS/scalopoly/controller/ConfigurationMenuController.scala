package PPS.scalopoly.controller

import PPS.scalopoly.model.Token
import PPS.scalopoly.model.Player
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.view.ConfigurationMenuView
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

import java.net.URL
import java.util

object ConfigurationMenuController:

  private var configurationMenuView: ConfigurationMenuView = _

  def setView(view: ConfigurationMenuView): Unit =
    configurationMenuView = view

  def addPayer(player: Player): Unit =
    GameEngine.addPlayer(player)

  def removePayer(player: Player): Unit =
    GameEngine.removePlayer(player)

  def availableToken(): List[Token] =
    GameEngine.availableTokens()

  def availablePlayer(): List[Player] =
    GameEngine.players

  def playGame(): Unit =
    GameEngine.startGame()
    FxmlUtils.changeScene(FxmlResources.GAME_VIEW.path)

  def exitGame(): Unit = sys.exit(0)