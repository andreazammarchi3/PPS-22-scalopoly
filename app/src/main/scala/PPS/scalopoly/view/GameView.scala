package PPS.scalopoly.view

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.{Game, Player, Token}
import PPS.scalopoly.utils.{FxmlUtils, GameUtils}
import PPS.scalopoly.utils.resources.ImgResources
import javafx.fxml.{FXML, Initializable}
import javafx.geometry.{Pos, Rectangle2D}
import javafx.scene.control.{Button, Label}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{Border, BorderPane, ColumnConstraints, GridPane, HBox, RowConstraints, VBox}
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

  private var cellsGrids: Map[(Int, Int), GridPane] = Map.empty
  private var tokensImgView: Map[Token, ImageView] = Map.empty

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    GameController.setView(this)
    gameBoard.setImage(new Image(getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString))
    gameBoard.setPreserveRatio(false)
    setResolution()
    temp()

    GameEngine.players.foreach(p =>
      createPlayerBox(p)
      val tokenImg = new ImageView(new Image(getClass.getResource(p.token.img.path).toString))
      tokensImgView += (p.token, tokenImg)
      tokenImg.setPreserveRatio(false)
      tokenImg.setFitWidth(gameBoard.getFitWidth / 11 / 4)
      tokenImg.setFitHeight(gameBoard.getFitHeight / 11 / 4)
      updateTokenPosition(p))

  private def setResolution(): Unit =
    FxmlUtils.setResolution(pane, 0.9, 0.9)
    val (width, height) = FxmlUtils.getResolution
    val gameBoardSize = pane.getPrefHeight
    gameBoard.setFitWidth(gameBoardSize)
    gameBoard.setFitHeight(gameBoardSize)
    initCellGrids()

    val menuWidth = width - gameBoardSize
    actionsMenu.setPrefWidth(menuWidth / 2)
    playerListBox.setPrefWidth(menuWidth / 2)

  private def initCellGrids(): Unit =
    for i <- 0 to 10
      j <- 0 to 10
      if ((i == 0 || i == 10) && (j >= 0 && j <= 10)) || ((j == 0 || j == 10) && (i >= 0 && i <= 10))
    do
      val tmpGrid = new GridPane()
      spawnColumns(tmpGrid, 4)
      spawnRows(tmpGrid, 3)
      if i == 0 then
        tmpGrid.setRotate(90)
      else if i == 10  && j < 10 then
        tmpGrid.setRotate(-90)
      if j == 0 then
        tmpGrid.setRotate(180)

      mainGrid.add(tmpGrid, i, j)
      cellsGrids += ((i, j), tmpGrid)

      def spawnColumns(grid: GridPane, numCol: Int): Unit =
        val col = new ColumnConstraints()
        col.setPercentWidth(50)
        for _ <- 0 until numCol do
          grid.getColumnConstraints.add(col)

      def spawnRows(grid: GridPane, numRow: Int): Unit =
        val row = new RowConstraints()
        row.setPercentHeight(50)
        for _ <- 0 until numRow do
          grid.getRowConstraints.add(row)

  private def temp(): Unit =
    val p1: Player = Player("P1", Token.DITALE)
    val p2: Player = Player("P2", Token.NAVE)
    val p3: Player = Player("P3", Token.GATTO)
    val p4: Player = Player("P4", Token.CANE)
    val p5: Player = Player("P5", Token.STIVALE)
    val p6: Player = Player("P6", Token.AUTOMOBILE)
    GameEngine.addPlayer(p1)
    GameEngine.addPlayer(p2)
    GameEngine.addPlayer(p3)
    GameEngine.addPlayer(p4)
    GameEngine.addPlayer(p5)
    GameEngine.addPlayer(p6)
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

  private def getFirstFreeCellForToken(gridPane: GridPane): (Int, Int) =
    GameUtils.getCoordinateFromOneNumber(gridPane.getChildren.size() + 1, 4)

  def quitBtnClick(): Unit =
    playersHBox(GameEngine.currentPlayer.get).setDisable(true)
    tokensImgView(GameEngine.currentPlayer.get.token).setDisable(true)
    tokensImgView(GameEngine.currentPlayer.get.token).setStyle("-fx-opacity: 0.5")
    GameController.currentPlayerQuit()
    endTurnBtn.setDisable(true)
    throwDiceBtn.setDisable(false)

  def throwDiceBtnClick(): Unit =
    GameController.throwDice()
    println("Dadi: " + GameEngine.dice.dice1 + " e " + GameEngine.dice.dice2)
    endTurnBtn.setDisable(false)
    throwDiceBtn.setDisable(true)

  def endTurnBtnClick(): Unit =
    GameController.endTurn()
    endTurnBtn.setDisable(true)
    throwDiceBtn.setDisable(false)

  def updateTokenPosition(player: Player): Unit =
    val coordinate = GameUtils.getCoordinateFromPosition(player.actualPosition)
    val cellGrid = cellsGrids(coordinate)
    val (col, row) = getFirstFreeCellForToken(cellGrid)
    cellGrid.add(tokensImgView(player.token), col, row)
