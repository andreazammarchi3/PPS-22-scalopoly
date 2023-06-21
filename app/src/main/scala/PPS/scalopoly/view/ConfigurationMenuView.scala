package PPS.scalopoly.view

import PPS.scalopoly.model.{Player, Token}
import PPS.scalopoly.controller.ConfigurationMenuController
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.{CssResources, ImgResources}
import javafx.scene.control.{Button, ComboBox, TableView, TextField}
import javafx.stage.{Screen, Stage, Window}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.{Parent, Scene}
import javafx.scene.layout.{AnchorPane, BorderPane, VBox}
import javafx.collections.{FXCollections, ObservableList}
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.TableColumn
import scalafx.scene.input.KeyCode.GameC
import scalafx.scene.control.ControlIncludes.jfxCellDataFeatures2sfx

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
  private var leftBorderPaneVBox: VBox = _
  @FXML
  private var rightBorderPaneVBox: VBox = _
  @FXML
  private var tableView: TableView[Player] = _
  @FXML
  private var playerNameColumn: TableColumn[Player, String] = _
  @FXML
  private var playerTokenColumn: TableColumn[Player, Token] = _
  @FXML
  private var addPlayerNameTextField: TextField = _
  @FXML
  private var addPlayerTokenCombobox: ComboBox[Token] = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    ConfigurationMenuController.setView(this)
    initUIElements()

  private def initUIElements(): Unit =
    gameBoard.setImage(new Image(getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString))
    gameBoard.setPreserveRatio(false)
    pane.getStylesheets.add(getClass.getResource(CssResources.GAME_STYLE.path).toExternalForm)
    FxmlUtils.setPaneResolution(pane, 0.9, 0.9)
    FxmlUtils.setGameBoardSize(pane, gameBoard)
    val gameBoardSize = pane.getPrefHeight
    val (width, height) = FxmlUtils.getResolution
    val menuWidth = width - gameBoardSize
    rightBorderPaneVBox.setPrefWidth(menuWidth / 2)
    leftBorderPaneVBox.setPrefWidth(menuWidth / 2)
    initTableView()
    updateAddPlayerCombobox()

  private def initTableView(): Unit =
    playerNameColumn.setCellValueFactory(_.value.nickname_)
    playerTokenColumn.setCellValueFactory(_.value.token_)
    tableView.setItems(FXCollections.observableArrayList[Player]())


  def playGameBtnClick(): Unit =
    ConfigurationMenuController.playGame()

  def exitGameBtnClick(): Unit =
    ConfigurationMenuController.exitGame()

  @FXML
  def addPlayerToTableView(): Unit =
    val newPlayer = Player(addPlayerNameTextField.getText, addPlayerTokenCombobox.getValue)
    tableView.getItems.add(newPlayer)
    ConfigurationMenuController.addPayer(newPlayer)
    updateAddPlayerCombobox()

  def updateAddPlayerCombobox(): Unit =
    addPlayerTokenCombobox.getItems.setAll(FXCollections.observableArrayList(ConfigurationMenuController.availableToken(): _*))
    addPlayerTokenCombobox.getSelectionModel.selectFirst()