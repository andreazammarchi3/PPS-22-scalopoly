package PPS.scalopoly.view

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.{DiceManager, Game, GameBoard, Player, Token}
import PPS.scalopoly.utils.FxmlUtils.getResolution
import PPS.scalopoly.utils.{FxmlUtils, GameUtils}
import PPS.scalopoly.utils.resources.{CssResources, ImgResources}
import javafx.fxml.{FXML, Initializable}
import javafx.geometry.{Pos, Rectangle2D}
import javafx.scene.control.{Button, Label}
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

  private val playersHBox: MMap[Token, HBox] = MMap.empty

  private val cellsGrids: MMap[(Int, Int), GridPane] = MMap.empty

  private val tokensImgView: MMap[Token, ImageView] = MMap.empty

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    GameController.setView(this)
    initUIElements()

    GameEngine.players.foreach(p =>
      createPlayerBox(p)
      val tokenImg = new ImageView(
        new Image(getClass.getResource(p.token.img.path).toString)
      )
      tokensImgView.addOne(p.token, tokenImg)
      tokenImg.setPreserveRatio(false)
      tokenImg.setFitWidth(gameBoard.getFitWidth / 11 / 4)
      tokenImg.setFitHeight(gameBoard.getFitHeight / 11 / 4)
      updateTokenPosition(p)
    )

    pane.getStylesheets.add(
      getClass.getResource(CssResources.GAME_STYLE.path).toExternalForm
    )
    updateStyleForCurrentPLayer()

  private def initUIElements(): Unit =
    gameBoard.setImage(
      new Image(
        getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString
      )
    )
    gameBoard.setPreserveRatio(false)
    FxmlUtils.setPaneResolution(pane, 0.9, 0.9)
    FxmlUtils.setGameBoardSize(pane, gameBoard)
    initCellGrids()
    val gameBoardSize = pane.getPrefHeight
    val (width, height) = getResolution
    val menuWidth = width - gameBoardSize
    actionsMenu.setPrefWidth(menuWidth / 2)
    playerListBox.setPrefWidth(menuWidth / 2)

    diceImageView1.setFitWidth(gameBoardSize / 11)
    diceImageView2.setFitWidth(gameBoardSize / 11)

  private def initCellGrids(): Unit =
    for
      i <- 0 to 10
      j <- 0 to 10
      if ((i == 0 || i == 10) && (j >= 0 && j <= 10)) || ((j == 0 || j == 10) && (i >= 0 && i <= 10))
    do
      val tmpGrid = new GridPane()
      spawnColumns(tmpGrid, 4)
      spawnRows(tmpGrid, 3)
      if i == 0 then tmpGrid.setRotate(90)
      else if i == 10 && j < 10 then tmpGrid.setRotate(-90)
      if j == 0 then tmpGrid.setRotate(180)

      mainGrid.add(tmpGrid, i, j)
      cellsGrids.addOne((i, j), tmpGrid)

      def spawnColumns(grid: GridPane, numCol: Int): Unit =
        val col = new ColumnConstraints()
        col.setPercentWidth(50)
        for _ <- 0 until numCol do grid.getColumnConstraints.add(col)

      def spawnRows(grid: GridPane, numRow: Int): Unit =
        val row = new RowConstraints()
        row.setPercentHeight(50)
        for _ <- 0 until numRow do grid.getRowConstraints.add(row)

  private def createPlayerBox(player: Player): Unit =
    val playerHBox: HBox = new HBox()
    playerListBox.getChildren.add(playerHBox)

    val playerLbl: Label = new Label(
      player.nickname + " - " + player.token.toString.toLowerCase()
    )
    playerHBox.getChildren.add(playerLbl)

    /*
    val playerMoneyLbl: Label = new Label("99999$")
    playerHBox.getChildren.add(playerMoneyLbl)

    val playerPropertiesBtn: Button = new Button("Proprietà")
    playerPropertiesBtn.getStyleClass.add("scalopoly-btn")
    playerHBox.getChildren.add(playerPropertiesBtn)
     */

    playerHBox.setSpacing(10)
    playerHBox.setAlignment(Pos.CENTER)

    playersHBox += (player.token -> playerHBox)

  private def getFirstFreeCellForToken(gridPane: GridPane): (Int, Int) =
    GameUtils.getNthCellInGrid(gridPane.getChildren.size() + 1, (4, 3), (0, 0))

  /**
   * Update the style of the current player
   */
  def updateStyleForCurrentPLayer(): Unit =
    playersHBox.values.foreach(h => h.getStyleClass.clear())
    playersHBox(GameEngine.currentPlayer.token).getStyleClass
      .add("green-background")

  /**
   * Remove current player from the game
   */
  def quitBtnClick(): Unit =
    playersHBox(GameEngine.currentPlayer.token).setDisable(true)
    tokensImgView(GameEngine.currentPlayer.token).setDisable(true)
    GameController.currentPlayerQuit()
    endTurnBtn.setDisable(true)
    throwDiceBtn.setDisable(false)

  /**
   * Throw dice
   */
  def throwDiceBtnClick(): Unit =
    val (dice1, dice2) = GameController.throwDice()
    if dice1 != dice2 then
      endTurnBtn.setDisable(false)
      throwDiceBtn.setDisable(true)

  /**
   * End turn
   */
  def endTurnBtnClick(): Unit =
    GameController.endTurn()
    endTurnBtn.setDisable(true)
    throwDiceBtn.setDisable(false)

  /**
   * Update the position of the token in the game board
   * @param player the player who's token position has to be updated
   */
  def updateTokenPosition(player: Player): Unit =
    val coordinate = GameUtils.getCoordinateFromPosition(player.actualPosition)
    val cellGrid = cellsGrids(coordinate)
    val (col, row) = getFirstFreeCellForToken(cellGrid)
    cellGrid.add(tokensImgView(player.token), col, row + 1)

  /**
   * Update the dice image to show the result of the dice throw
   * @param dice1 the first dice result
   * @param dice2 the second dice result
   */
  def updateDiceImg(dice1: Int, dice2: Int): Unit =
    val dice1Path: String = ImgResources.valueOf("DICE_" + dice1.toString).path
    diceImageView1.setImage(new Image(getClass.getResource(dice1Path).toString))
    val dice2Path: String = ImgResources.valueOf("DICE_" + dice2.toString).path
    diceImageView2.setImage(new Image(getClass.getResource(dice2Path).toString))
