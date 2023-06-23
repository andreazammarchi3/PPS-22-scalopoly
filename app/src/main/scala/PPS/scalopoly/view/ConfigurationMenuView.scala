package PPS.scalopoly.view

import PPS.scalopoly.model.{Player, Token}
import PPS.scalopoly.controller.ConfigurationMenuController
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.{CssResources, ImgResources}
import javafx.beans.binding.Bindings
import javafx.scene.control.{Button, ComboBox, TableView, TextField}
import javafx.stage.{Screen, Stage, Window}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.{Parent, Scene}
import javafx.scene.layout.{AnchorPane, BorderPane, VBox}
import javafx.collections.{FXCollections, ObservableList}
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.control.TableColumn
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import scalafx.scene.input.KeyCode.GameC
import scalafx.scene.control.ControlIncludes.jfxCellDataFeatures2sfx

import java.net.URL
import java.util

/**
 * View for the configuration menu.
 */
class ConfigurationMenuView extends Initializable:

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var startBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var exitBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var pane: BorderPane = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var gameBoard: ImageView = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var leftBorderPaneVBox: VBox = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var rightBorderPaneVBox: VBox = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var tableView: TableView[Player] = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var playerNameColumn: TableColumn[Player, String] = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var playerTokenColumn: TableColumn[Player, Token] = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var addPlayerNameTextField: TextField = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var addPlayerTokenCombobox: ComboBox[Token] = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var addPlayerBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var removePlayerBtn: Button = _
  
  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    ConfigurationMenuController.setView(this)
    initUIElements()

  private def initUIElements(): Unit =
    gameBoard.setImage(
      new Image(
        getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString
      )
    )
    gameBoard.setPreserveRatio(false)
    pane.getStylesheets.add(
      getClass.getResource(CssResources.GAME_STYLE.path).toExternalForm
    )
    FxmlUtils.setPaneResolution(pane, 0.9, 0.9)
    FxmlUtils.setGameBoardSize(pane, gameBoard)
    val gameBoardSize = pane.getPrefHeight
    val (width, height) = FxmlUtils.getResolution
    val menuWidth = width - gameBoardSize
    rightBorderPaneVBox.setPrefWidth(menuWidth / 2)
    leftBorderPaneVBox.setPrefWidth(menuWidth / 2)
    initTableView()
    updateAddPlayerCombobox()
    removePlayerBtn
      .disableProperty()
      .bind(
        Bindings.isEmpty(tableView.getSelectionModel.getSelectedItems)
      )

  private def initTableView(): Unit =
    playerNameColumn.setCellValueFactory(_.value.nickname_)
//    playerNameColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2))
    playerTokenColumn.setCellValueFactory(_.value.token_)
//    playerTokenColumn.prefWidthProperty().bind(tableView.widthProperty().divide(2))
    tableView.setItems(FXCollections.observableArrayList[Player]())

  /**
   * Checks if the player name is not empty and adds the player to the table view.
   */
  def playGameBtnClick(): Unit =
    if (ConfigurationMenuController.canStartGame)
      ConfigurationMenuController.playGame()
    else showCantStartAlert()
  
  private def showCantStartAlert(): Unit =
    val alert = new Alert(AlertType.WARNING)
    alert.setTitle("Scalopoly")
    alert.setHeaderText("Non è possibile avviare il gioco.")
    alert.setContentText("Aggiungere almeno due giocatori.")
    alert.showAndWait()

  private def showEmptyPlayerNameAlert(): Unit =
    val alert = new Alert(AlertType.WARNING)
    alert.setTitle("Scalopoly")
    alert.setHeaderText("Non è possibile aggiungere il giocatore.")
    alert.setContentText("Il nome del giocatore non può essere vuoto.")
    alert.showAndWait()

  /**
   * Exits the game.
   */
  def exitGameBtnClick(): Unit =
    ConfigurationMenuController.exitGame()

  /**
   * Add a player to the table view, if the player name is not empty.
   */
  @FXML
  def checkAndAddPlayerToTableView(): Unit =
    if (ConfigurationMenuController.canAddPlayer) addPlayerToTableView()

  private def addPlayerToTableView(): Unit =
    addPlayerNameTextField.getText match
      case playerName if playerName.isEmpty => showEmptyPlayerNameAlert()
      case _ =>
        val newPlayer =
          Player(
            addPlayerNameTextField.getText,
            addPlayerTokenCombobox.getValue
          )
        tableView.getItems.add(newPlayer)
        ConfigurationMenuController.addPlayer(newPlayer)
        updateAddPlayerCombobox()
        updateAddAndRemoveButton()
        addPlayerNameTextField.clear()

  /**
   * Removes the selected player from the table view.
   */
  @FXML
  def removePlayerFromTableView(): Unit =
    val selectedPlayer = tableView.getSelectionModel.getSelectedItem
    tableView.getItems.remove(selectedPlayer)
    ConfigurationMenuController.removePlayer(selectedPlayer)
    updateAddPlayerCombobox()
    updateAddAndRemoveButton()

  private def updateAddPlayerCombobox(): Unit =
    addPlayerTokenCombobox.getItems.setAll(
      FXCollections.observableArrayList(
        ConfigurationMenuController.availableToken(): _*
      )
    )
    addPlayerTokenCombobox.getSelectionModel.selectFirst()

  private def updateAddAndRemoveButton(): Unit =
    if (!ConfigurationMenuController.canAddPlayer)
      addPlayerBtn.setDisable(true)
    else
      addPlayerBtn.setDisable(false)
