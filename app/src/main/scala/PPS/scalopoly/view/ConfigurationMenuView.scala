package PPS.scalopoly.view

import PPS.scalopoly.model.{Player, Token}
import PPS.scalopoly.controller.ConfigurationMenuController
import PPS.scalopoly.utils.{AlertUtils, FxmlUtils}
import PPS.scalopoly.utils.resources.CssResources
import javafx.beans.binding.Bindings
import javafx.scene.control.{Button, ComboBox, TableView, TextField}
import javafx.stage.Stage
import javafx.fxml.{FXML, Initializable}
import javafx.scene.image.ImageView
import javafx.scene.layout.{BorderPane, VBox}
import javafx.collections.FXCollections
import javafx.scene.control.TableColumn
import scalafx.beans.property.{ObjectProperty, StringProperty}

import java.net.URL
import java.util.ResourceBundle

/** View for the configuration menu.
  */
class ConfigurationMenuView extends Initializable:

  private val N_MENUS = 2

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var startBtn: Button = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var exitBtn: Button = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var pane: BorderPane = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var gameBoard: ImageView = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var leftBorderPaneVBox: VBox = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var rightBorderPaneVBox: VBox = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var tableView: TableView[Player] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var playerNameColumn: TableColumn[Player, String] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var playerTokenColumn: TableColumn[Player, Token] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var addPlayerNameTextField: TextField = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var addPlayerTokenCombobox: ComboBox[Token] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var addPlayerBtn: Button = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var removePlayerBtn: Button = _

  override def initialize(location: URL, resources: ResourceBundle): Unit =
    FxmlUtils.initUIElements(
      pane,
      gameBoard,
      CssResources.GAME_STYLE,
      FxmlUtils.DEFAULT_WIDTH_PERC,
      FxmlUtils.DEFAULT_HEIGHT_PERC
    )

    val menuWidth = FxmlUtils.getResolution._1 - pane.getPrefHeight
    rightBorderPaneVBox.setPrefWidth(menuWidth / N_MENUS)
    leftBorderPaneVBox.setPrefWidth(menuWidth / N_MENUS)
    initTableView()
    updateAddPlayerCombobox()
    removePlayerBtn
      .disableProperty()
      .bind(
        Bindings.isEmpty(tableView.getSelectionModel.getSelectedItems)
      )

  /** Checks if the player name is not empty and adds the player to the table view.
    */
  def playGameBtnClick(): Unit =
    if (ConfigurationMenuController.canStartGame)
      ConfigurationMenuController.playGame()
    else
      AlertUtils.showNotEnoughPlayersWarning()

  /** Exits the game.
    */
  def exitGameBtnClick(): Unit =
    ConfigurationMenuController.exitGame()

  /** Add a player to the table view, if the player name is not empty.
    */
  def checkAndAddPlayerToTableView(): Unit =
    if (ConfigurationMenuController.canAddPlayer) addPlayerToTableView()

  /** Removes the selected player from the table view.
    */
  def removePlayerFromTableView(): Unit =
    val selectedPlayer = tableView.getSelectionModel.getSelectedItem
    tableView.getItems.remove(selectedPlayer)
    ConfigurationMenuController.removePlayer(selectedPlayer)
    updateAddPlayerCombobox()
    updateAddAndRemoveButton()

  private def initTableView(): Unit =
    playerNameColumn.setCellValueFactory(p => StringProperty(p.getValue.nickname))
    playerTokenColumn.setCellValueFactory(p => ObjectProperty(p.getValue.token))
    tableView.setItems(FXCollections.observableArrayList[Player]())

  private def addPlayerToTableView(): Unit =
    addPlayerNameTextField.getText match
      case playerName if playerName.isEmpty =>
        AlertUtils.showEmptyPlayerNameWarning()
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
    if ConfigurationMenuController.canAddPlayer then addPlayerBtn.setDisable(false)
    else addPlayerBtn.setDisable(true)
