package PPS.scalopoly.view

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.{Game, Player, Token}
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.ImgResources
import javafx.fxml.{FXML, Initializable}
import javafx.geometry.{Pos, Rectangle2D}
import javafx.scene.control.{Button, Label}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{Border, BorderPane, GridPane, HBox, VBox}
import javafx.stage.Screen

import java.net.URL
import java.util

class GameView extends Initializable:

  @FXML
  private var playerListBox: VBox = _

  @FXML
  private var actionsMenu: VBox = _

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

  @FXML
  private var mainGrid: GridPane = _

  private var playersHBox: Map[Player, HBox] = Map.empty

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    gameBoard.setImage(new Image(getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString))
    gameBoard.setPreserveRatio(false)
    setResolution()
    temp()

    GameEngine.players.foreach(p => createPlayerBox(p))

  private def setResolution(): Unit =
    FxmlUtils.setResolution(pane, 0.9, 0.9)
    val (width, height) = FxmlUtils.getResolution
    val gameBoardSize = pane.getPrefHeight
    gameBoard.setFitWidth(gameBoardSize)
    gameBoard.setFitHeight(gameBoardSize)
    initTokens(gameBoardSize)

    val menuWidth = width - gameBoardSize
    actionsMenu.setPrefWidth(menuWidth / 2)
    playerListBox.setPrefWidth(menuWidth / 2)

  private def initTokens(gameBoardSize: Double): Unit =
    for i <- 0 to 10
      j <- 0 to 10
      if ((i == 0 || i == 10) && (j >= 0 && j <= 10)) || ((j == 0 || j == 10) && (i >= 0 && i <= 10))
    do
      val tmpGrid = new GridPane()
      tmpGrid.addColumn(0)
      tmpGrid.addColumn(1)
      tmpGrid.addColumn(2)
      tmpGrid.addColumn(3)
      tmpGrid.addRow(0)
      tmpGrid.addRow(1)
      tmpGrid.addRow(2)
      tmpGrid.add(new Button("s"), 0, 0)
      tmpGrid.add(new Button("t"), 1, 1)
      tmpGrid.add(new Button("a"), 2, 2)
      tmpGrid.add(new Button("a"), 3, 2)
      tmpGrid.setGridLinesVisible(true)
      tmpGrid.setStyle("-fx-grid-lines-visible: true; -fx-border-color: black;")
      mainGrid.add(tmpGrid, i, j)

  def quitBtnClick(): Unit =
    playersHBox(GameEngine.currentPlayer.get).setDisable(true)
    GameController.currentPlayerQuit()

  def throwDiceBtnClick(): Unit =
    GameController.throwDice()
    println("Dadi: " + GameEngine.dice.dice1 + " e " + GameEngine.dice.dice2)
    endTurnBtn.setDisable(false)
    throwDiceBtn.setDisable(true)

  def endTurnBtnClick(): Unit =
    GameController.endTurn()
    endTurnBtn.setDisable(true)
    throwDiceBtn.setDisable(false)

  private def temp(): Unit =
    val p1: Player = Player("P1", Token.DITALE)
    val p2: Player = Player("P2", Token.NAVE)
    val p3: Player = Player("P3", Token.GATTO)
    GameEngine.addPlayer(p1)
    GameEngine.addPlayer(p2)
    GameEngine.addPlayer(p3)
    GameEngine.startGame()

  private def createPlayerBox(player: Player): Unit =
    val playerHBox: HBox = new HBox()
    playerListBox.getChildren.add(playerHBox)

    val playerLbl: Label = new Label(player.nickname)
    playerHBox.getChildren.add(playerLbl)

    /*
    val playerMoneyLbl: Label = new Label("0$")
    playerHBox.getChildren.add(playerMoneyLbl)

    val playerPropertiesBtn: Button = new Button("Proprietà")
    playerHBox.getChildren.add(playerPropertiesBtn)
    */

    playerHBox.setSpacing(5)
    playerHBox.setAlignment(Pos.CENTER)

    playersHBox += (player -> playerHBox)

