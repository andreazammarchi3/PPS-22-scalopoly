package PPS.scalopoly.view

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.{Game, Player, Token}
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.ImgResources
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, Label}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{BorderPane, HBox, VBox}
import javafx.stage.Screen

import java.net.URL
import java.util

class GameView extends Initializable:

  @FXML
  private var bottomMenu: HBox = _

  @FXML
  private var playerListHBox: HBox = _

  @FXML
  private var bottomRightMenu: VBox = _

  @FXML
  private var turnLbl: Label = _

  @FXML
  private var throwDiceBtn: Button = _

  @FXML
  private var buildBtn: Button = _

  @FXML
  private var endTurnBtn: Button = _

  @FXML
  private var quitBtn: Button = _

  @FXML
  private var gameBoard: ImageView = _

  @FXML
  private var pane: BorderPane = _


  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    gameBoard.setImage(new Image(getClass.getResource(ImgResources.GAMEBOARD.path).toString))
    gameBoard.setPreserveRatio(false)
    setResolution()
    temp()
    updateTurnLbl()

  private def setResolution(): Unit =
    FxmlUtils.setResolution(pane, 0.9, 0.9)
    val (width, height) = FxmlUtils.getResolution
    gameBoard.setFitWidth(width - 2)
    gameBoard.setFitHeight(pane.getPrefHeight - bottomMenu.getPrefHeight)
    bottomMenu.setPrefWidth(width - 2)
    playerListHBox.setPrefWidth(width * 2/3)
    bottomRightMenu.setPrefWidth(width * 1/3)

  def quitBtnClick(): Unit =
    GameController.currentPlayerQuit()
    updateTurnLbl()

  def throwDiceBtnClick(): Unit =
    GameController.throwDice()
    println("Dadi: " + GameEngine.dice.dice1 + " e " + GameEngine.dice.dice2)
    endTurnBtn.setDisable(false)
    throwDiceBtn.setDisable(true)

  def endTurnBtnClick(): Unit =
    GameController.endTurn()
    endTurnBtn.setDisable(true)
    throwDiceBtn.setDisable(false)
    updateTurnLbl()

  private def temp(): Unit =
    val p1: Player = Player("P1", Token.DITALE)
    val p2: Player = Player("P2", Token.NAVE)
    val p3: Player = Player("P3", Token.GATTO)
    GameEngine.addPlayer(p1)
    GameEngine.addPlayer(p2)
    GameEngine.addPlayer(p3)
    GameEngine.startGame()

  private def updateTurnLbl(): Unit =
    turnLbl.setText("Tocca a: " + GameEngine.currentPlayer.get.nickname)
