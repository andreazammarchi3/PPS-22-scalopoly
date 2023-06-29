package PPS.scalopoly.view

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.engine.GameEngine.currentPlayer
import PPS.scalopoly.model.{
  DiceManager,
  GameBoard,
  Player,
  PurchasableSpace,
  Token
}
import PPS.scalopoly.utils.{FxmlUtils, GameUtils}
import PPS.scalopoly.utils.resources.{CssResources, ImgResources}
import javafx.beans.value.ChangeListener
import javafx.fxml.{FXML, Initializable}
import javafx.geometry.{Pos, Rectangle2D}
import javafx.scene.control.{Button, Label, ListView}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{
  Background,
  BackgroundFill,
  Border,
  BorderPane,
  ColumnConstraints,
  GridPane,
  HBox,
  RowConstraints,
  VBox
}
import javafx.scene.paint.Color
import javafx.stage.Screen
import scalafx.scene.shape.Path

import scala.collection.mutable.Map as MMap
import java.net.URL
import java.util

class GameView extends Initializable:

  private val N_MENUS = 2
  private val N_COLS_IN_CELL = 4
  private val N_ROWS_IN_CELL = 3

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var playerListBox: VBox = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var actionsMenu: VBox = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var throwDiceBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var buildBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var endTurnBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var quitBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var gameBoard: ImageView = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var pane: BorderPane = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var mainGrid: GridPane = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var diceImageView1: ImageView = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var diceImageView2: ImageView = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var logLabel: Label = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var propertiesList: ListView[String] = _

  private val playersHBox: MMap[Token, HBox] = MMap.empty

  private val cellsGrids: MMap[(Int, Int), GridPane] = MMap.empty

  private val tokensImgView: MMap[Token, ImageView] = MMap.empty

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    FxmlUtils.initUIElements(
      pane,
      gameBoard,
      CssResources.GAME_STYLE,
      FxmlUtils.DEFAULT_WIDTH_PERC,
      FxmlUtils.DEFAULT_HEIGHT_PERC
    )

    initCellGrids()
    val menuWidth = FxmlUtils.getResolution._1 - pane.getPrefHeight
    actionsMenu.setPrefWidth(menuWidth / N_MENUS)
    playerListBox.setPrefWidth(menuWidth / N_MENUS)

    setDiceImg(diceImageView1)
    setDiceImg(diceImageView2)

    GameEngine.players.foreach(p =>
      createPlayerBox(p)
      val tokenImg = new ImageView(
        new Image(getClass.getResource(p.token.img.path).toString)
      )
      tokensImgView.addOne(p.token, tokenImg)
      tokenImg.setPreserveRatio(false)
      tokenImg.setFitWidth(
        gameBoard.getFitWidth / (GameUtils.CELLS_IN_SIDE + 1) / N_COLS_IN_CELL
      )
      tokenImg.setFitHeight(
        gameBoard.getFitHeight / (GameUtils.CELLS_IN_SIDE + 1) / N_COLS_IN_CELL
      )
      updateTokenPosition(p)
    )

    updateStyleForCurrentPLayer()

  /** Remove current player from the game
    */
  def quitBtnClick(): Unit =
    playersHBox(GameEngine.currentPlayer.token).setDisable(true)
    tokensImgView(GameEngine.currentPlayer.token).setDisable(true)
    log(GameEngine.currentPlayer.token.toString + " ha abbandonato la partita")
    GameController.currentPlayerQuit()
    if (GameEngine.players.nonEmpty)
      setBtnsForEndTurn(false)
      updateStyleForCurrentPLayer()

  /** Throw dice
    */
  def throwDiceBtnClick(): Unit =
    val (dice1, dice2) = GameController.throwDice()
    updateTokenPosition(GameEngine.currentPlayer)
    updateDiceImg(dice1, dice2)
    log(
      GameEngine.currentPlayer.token.toString + " ha tirato " + dice1 + " e " + dice2
    )
    GameController.checkPlayerActions()

    if dice1 != dice2 then setBtnsForEndTurn(true)

  /** End turn
    */
  def endTurnBtnClick(): Unit =
    GameController.endTurn()
    setBtnsForEndTurn(false)
    updateStyleForCurrentPLayer()
    log(GameEngine.currentPlayer.token.toString + " ha terminato il turno")

  def log(msg: String): Unit =
    logLabel.setText(msg)

  private def initCellGrids(): Unit =
    val RIGHT_ANGLE = 90
    val CONSTRAINT_PERC = 50
    for
      i <- 0 to GameUtils.CELLS_IN_SIDE
      j <- 0 to GameUtils.CELLS_IN_SIDE
      if ((i == 0 || i == GameUtils.CELLS_IN_SIDE) && (j >= 0 && j <= GameUtils.CELLS_IN_SIDE)) || ((j == 0 || j == GameUtils.CELLS_IN_SIDE) && (i >= 0 && i <= GameUtils.CELLS_IN_SIDE))
    do
      val tmpGrid = new GridPane()
      spawnColumns(tmpGrid, N_COLS_IN_CELL)
      spawnRows(tmpGrid, N_ROWS_IN_CELL)
      if i == 0 then tmpGrid.setRotate(RIGHT_ANGLE)
      else if i == GameUtils.CELLS_IN_SIDE && j < GameUtils.CELLS_IN_SIDE then
        tmpGrid.setRotate(-RIGHT_ANGLE)
      if j == 0 then tmpGrid.setRotate(RIGHT_ANGLE * 2)

      mainGrid.add(tmpGrid, i, j)
      cellsGrids.addOne((i, j), tmpGrid)

      def spawnColumns(grid: GridPane, numCol: Int): Unit =
        val col = new ColumnConstraints()
        col.setPercentWidth(CONSTRAINT_PERC)
        for _ <- 0 until numCol do grid.getColumnConstraints.add(col)

      def spawnRows(grid: GridPane, numRow: Int): Unit =
        val row = new RowConstraints()
        row.setPercentHeight(CONSTRAINT_PERC)
        for _ <- 0 until numRow do grid.getRowConstraints.add(row)

  private def createPlayerBox(player: Player): Unit =
    val DEFAULT_SPACING = 10
    val playerHBox: HBox = new HBox()
    playerListBox.getChildren.add(playerHBox)

    val playerLbl: Label = new Label(
      player.nickname + " - " + player.token.toString.toLowerCase()
    )
    playerHBox.getChildren.add(playerLbl)

    val playerMoneyLbl: Label = new Label(player.money.toString)
    playerHBox.getChildren.add(playerMoneyLbl)

    val playerPropertiesBtn: Button = new Button("Proprietà")
    playerPropertiesBtn.setOnAction(_ =>
      highlightProperties(GameEngine.getOwnedPropertiesFromPlayer(player.token))
    )
    playerPropertiesBtn.getStyleClass.add("scalopoly-btn")
    playerHBox.getChildren.add(playerPropertiesBtn)

    playerHBox.setSpacing(DEFAULT_SPACING)
    playerHBox.setAlignment(Pos.CENTER)

    playersHBox += (player.token -> playerHBox)

  private def highlightProperties(properties: List[PurchasableSpace]): Unit =
    propertiesList.getItems.clear()
    properties.foreach(p => propertiesList.getItems.add(p.spaceName.name))

  private def getFirstFreeCellForToken(gridPane: GridPane): (Int, Int) =
    GameUtils.getNthCellInGrid(
      gridPane.getChildren.size() + 1,
      (N_COLS_IN_CELL, N_ROWS_IN_CELL),
      (0, 0)
    )

  private def setBtnsForEndTurn(can: Boolean): Unit =
    endTurnBtn.setDisable(!can)
    throwDiceBtn.setDisable(can)

  private def updateSingleDiceImg(dice: Int, diceImageView: ImageView): Unit =
    val dicePath: String = ImgResources.valueOf("DICE_" + dice.toString).path
    diceImageView.setImage(new Image(getClass.getResource(dicePath).toString))

  private def updateStyleForCurrentPLayer(): Unit =
    playersHBox.values.foreach(h => h.getStyleClass.clear())
    playersHBox(GameEngine.currentPlayer.token).getStyleClass
      .add("green-background")

  private def updateTokenPosition(player: Player): Unit =
    val cellGrid = cellsGrids(
      GameUtils.getCoordinateFromPosition(player.actualPosition)
    )
    val (col, row) = getFirstFreeCellForToken(cellGrid)
    cellGrid.add(tokensImgView(player.token), col, row + 1)

  private def setDiceImg(diceImgView: ImageView): Unit =
    diceImgView.setFitWidth(
      pane.getPrefHeight / (GameBoard.size / GameUtils.GAMEBOARD_SIDES + 1)
    )

  private def updateDiceImg(dice1: Int, dice2: Int): Unit =
    updateSingleDiceImg(dice1, diceImageView1)
    updateSingleDiceImg(dice2, diceImageView2)
