package PPS.scalopoly.view

import PPS.scalopoly.model.Token
import PPS.scalopoly.controller.ConfigurationMenuController
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.{CssResources, ImgResources}
import javafx.scene.control.{Button, ComboBox, TextField}
import javafx.stage.{Screen, Stage, Window}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.{Parent, Scene}
import javafx.scene.layout.{AnchorPane, BorderPane}

import java.net.URL
import java.util

class ConfigurationMenuView extends Initializable:

  @FXML
  private var startBtn: Button = _
  @FXML
  private var exitBtn: Button = _
  @FXML
  private var pane: BorderPane = _
  @FXML
  private var gameBoard: ImageView = _
  @FXML
  private var playerNameTextField1: TextField = _
  @FXML
  private var playerNameTextField2: TextField = _
  @FXML
  private var playerNameTextField3: TextField = _
  @FXML
  private var playerNameTextField4: TextField = _
  @FXML
  private var playerNameTextField5: TextField = _
  @FXML
  private var playerNameTextField6: TextField = _
  @FXML
  private var playerNameTextField7: TextField = _
  @FXML
  private var playerNameTextField8: TextField = _
  @FXML
  private var playerTokenComboBox1: ComboBox[Token] = _
  @FXML
  private var playerTokenComboBox2: ComboBox[Token] = _
  @FXML
  private var playerTokenComboBox3: ComboBox[Token] = _
  @FXML
  private var playerTokenComboBox4: ComboBox[Token] = _
  @FXML
  private var playerTokenComboBox5: ComboBox[Token] = _
  @FXML
  private var playerTokenComboBox6: ComboBox[Token] = _
  @FXML
  private var playerTokenComboBox7: ComboBox[Token] = _
  @FXML
  private var playerTokenComboBox8: ComboBox[Token] = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    ConfigurationMenuController.setView(this)
    gameBoard.setImage(new Image(getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString))
    gameBoard.setPreserveRatio(false)
    pane.getStylesheets.add(getClass.getResource(CssResources.GAME_STYLE.path).toExternalForm)
    initUIElements()


//    playerTokenComboBox1.getItems().addAll(Token.values)
  private def initUIElements(): Unit =
    FxmlUtils.setPaneResolution(pane, 0.9, 0.9)
    FxmlUtils.setGameBoardSize(pane, gameBoard)
  def playGameBtnClick(): Unit =
//    ConfigurationMenuController.addPayer(playerNameTextField1.getText(), Token.CARRIOLA)
//    ConfigurationMenuController.addPayer(playerNameTextField2.getText, Token.DITALE)
//    ConfigurationMenuController.addPayer(playerNameTextField3.getText, Token.NAVE)
//    ConfigurationMenuController.addPayer(playerNameTextField4.getText, Token.GATTO)
//    ConfigurationMenuController.addPayer(playerNameTextField5.getText, Token.CANE)
//    ConfigurationMenuController.addPayer(playerNameTextField6.getText, Token.STIVALE)
//    ConfigurationMenuController.addPayer(playerNameTextField7.getText, Token.AUTOMOBILE)
//    ConfigurationMenuController.addPayer(playerNameTextField8.getText, Token.CILINDRO)
    ConfigurationMenuController.playGame()

  def exitGameBtnClick(): Unit =
    ConfigurationMenuController.exitGame()