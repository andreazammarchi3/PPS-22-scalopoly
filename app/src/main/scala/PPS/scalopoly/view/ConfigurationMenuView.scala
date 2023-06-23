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
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.scene.input.KeyCode.GameC
import scalafx.scene.control.ControlIncludes.jfxCellDataFeatures2sfx

import java.net.URL
import java.util

/** View for the configuration menu.
  */
class ConfigurationMenuView extends BaseView:

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
    super.initialize(url, rb)

  override protected def initUIElements(): Unit =
    FxmlUtils.setGameBoardImage(gameBoard)
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

  /** Checks if the player name is not empty and adds the player to the table
    * view.
    */
  def playGameBtnClick(): Unit =
    if (ConfigurationMenuController.canStartGame)
      ConfigurationMenuController.playGame()
    else
      FxmlUtils.showAlert(
        AlertType.WARNING,
        "Scalopoly",
        "Non è possibile avviare il gioco.",
        "Aggiungere almeno due giocatori."
      )

  /** Exits the game.
    */
  def exitGameBtnClick(): Unit =
    ConfigurationMenuController.exitGame()

  /** Add a player to the table view, if the player name is not empty.
    */
  @FXML
  def checkAndAddPlayerToTableView(): Unit =
    if (ConfigurationMenuController.canAddPlayer) addPlayerToTableView()

  /** Removes the selected player from the table view.
    */
  @FXML
  def removePlayerFromTableView(): Unit =
    val selectedPlayer = tableView.getSelectionModel.getSelectedItem
    tableView.getItems.remove(selectedPlayer)
    ConfigurationMenuController.removePlayer(selectedPlayer)
    updateAddPlayerCombobox()
    updateAddAndRemoveButton()

  private def initTableView(): Unit =
    playerNameColumn.setCellValueFactory(p =>
      StringProperty(p.getValue.nickname)
    )
    playerTokenColumn.setCellValueFactory(p => ObjectProperty(p.getValue.token))
    tableView.setItems(FXCollections.observableArrayList[Player]())

  private def addPlayerToTableView(): Unit =
    addPlayerNameTextField.getText match
      case playerName if playerName.isEmpty =>
        FxmlUtils.showAlert(
          AlertType.WARNING,
          "Scalopoly",
          "Non è possibile aggiungere il giocatore.",
          "Il nome del giocatore non può essere vuoto."
        )
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

  private def updateAddPlayerCombobox(): Unit =
    addPlayerTokenCombobox.getItems.setAll(
      FXCollections.observableArrayList(
        ConfigurationMenuController.availableToken(): _*
      )
    )
    addPlayerTokenCombobox.getSelectionModel.selectFirst()

  private def updateAddAndRemoveButton(): Unit =
    if ConfigurationMenuController.canAddPlayer then
      addPlayerBtn.setDisable(false)
    else addPlayerBtn.setDisable(true)
