package PPS.scalopoly.view

import PPS.scalopoly.model.Token
import PPS.scalopoly.controller.ConfigurationMenuController
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.{CssResources, ImgResources}
import javafx.scene.control.{Button, ComboBox, TextField}
import javafx.stage.{Screen, Stage, Window}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.{Parent, Scene}
import javafx.scene.layout.{AnchorPane, BorderPane}
import javafx.collections.{FXCollections, ObservableList}

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

  @FXML
  private var addPlayer1: Button = _
  @FXML
  private var removePlayer1: Button = _
  @FXML
  private var addPlayer2: Button = _
  @FXML
  private var removePlayer2: Button = _
  @FXML
  private var addPlayer3: Button = _
  @FXML
  private var removePlayer3: Button = _
  @FXML
  private var addPlayer4: Button = _
  @FXML
  private var removePlayer4: Button = _
  @FXML
  private var addPlayer5: Button = _
  @FXML
  private var removePlayer5: Button = _
  @FXML
  private var addPlayer6: Button = _
  @FXML
  private var removePlayer6: Button = _
  @FXML
  private var addPlayer7: Button = _
  @FXML
  private var removePlayer7: Button = _
  @FXML
  private var addPlayer8: Button = _
  @FXML
  private var removePlayer8: Button = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    ConfigurationMenuController.setView(this)

    initUIElements()

  private def initUIElements(): Unit =
    gameBoard.setImage(new Image(getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString))
    gameBoard.setPreserveRatio(false)
    pane.getStylesheets.add(getClass.getResource(CssResources.GAME_STYLE.path).toExternalForm)
    FxmlUtils.setPaneResolution(pane, 0.9, 0.9)
    FxmlUtils.setGameBoardSize(pane, gameBoard)

    initComboboxes(false)

  private def initComboboxes(onlyEmptyComboboxes : Boolean): Unit =
    val tokenCollection: ObservableList[Token] = FXCollections.observableArrayList(ConfigurationMenuController.getAvailableToken(): _*)
    initCombobox(playerTokenComboBox1, tokenCollection, onlyEmptyComboboxes)
    initCombobox(playerTokenComboBox2, tokenCollection, onlyEmptyComboboxes)
    initCombobox(playerTokenComboBox3, tokenCollection, onlyEmptyComboboxes)
    initCombobox(playerTokenComboBox4, tokenCollection, onlyEmptyComboboxes)
    initCombobox(playerTokenComboBox5, tokenCollection, onlyEmptyComboboxes)
    initCombobox(playerTokenComboBox6, tokenCollection, onlyEmptyComboboxes)
    initCombobox(playerTokenComboBox7, tokenCollection, onlyEmptyComboboxes)
    initCombobox(playerTokenComboBox8, tokenCollection, onlyEmptyComboboxes)

  private def initCombobox(playerTokenCombobox: ComboBox[Token], tokenCollection: ObservableList[Token], onlyEmptyComboboxes : Boolean): Unit =
    if (!onlyEmptyComboboxes || !playerTokenCombobox.getSelectionModel.isEmpty) playerTokenCombobox.setItems(tokenCollection)

  private def updateNotSelectedCombobox(): Unit =
    val tokenCollection = FXCollections.observableArrayList(ConfigurationMenuController.getAvailableToken(): _*)
    initComboboxes(true)

  def playGameBtnClick(): Unit =
    if (!playerNameTextField1.getText().isEmpty) ConfigurationMenuController.addPayer(playerNameTextField1.getText(), playerTokenComboBox1.getSelectionModel.getSelectedItem)
    if (!playerNameTextField2.getText().isEmpty) ConfigurationMenuController.addPayer(playerNameTextField2.getText(), playerTokenComboBox2.getSelectionModel.getSelectedItem)
    if (!playerNameTextField3.getText().isEmpty) ConfigurationMenuController.addPayer(playerNameTextField3.getText(), playerTokenComboBox3.getSelectionModel.getSelectedItem)
    if (!playerNameTextField4.getText().isEmpty) ConfigurationMenuController.addPayer(playerNameTextField4.getText(), playerTokenComboBox4.getSelectionModel.getSelectedItem)
    if (!playerNameTextField5.getText().isEmpty) ConfigurationMenuController.addPayer(playerNameTextField5.getText(), playerTokenComboBox5.getSelectionModel.getSelectedItem)
    if (!playerNameTextField6.getText().isEmpty) ConfigurationMenuController.addPayer(playerNameTextField6.getText(), playerTokenComboBox6.getSelectionModel.getSelectedItem)
    if (!playerNameTextField7.getText().isEmpty) ConfigurationMenuController.addPayer(playerNameTextField7.getText(), playerTokenComboBox7.getSelectionModel.getSelectedItem)
    if (!playerNameTextField8.getText().isEmpty) ConfigurationMenuController.addPayer(playerNameTextField8.getText(), playerTokenComboBox8.getSelectionModel.getSelectedItem)
    ConfigurationMenuController.playGame()

  def exitGameBtnClick(): Unit =
    ConfigurationMenuController.exitGame()
