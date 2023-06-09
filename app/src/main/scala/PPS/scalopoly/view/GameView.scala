package PPS.scalopoly.view

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.engine.{EndgameLogicEngine, GameReader}
import PPS.scalopoly.model.space.purchasable.BuildableSpace
import PPS.scalopoly.model.{GameBoard, Player, Token}
import PPS.scalopoly.utils
import PPS.scalopoly.utils.{FxmlUtils, GameUtils}
import PPS.scalopoly.utils.resources.{CssResources, ImgResources}
import javafx.beans.binding.{Bindings, BooleanBinding}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, Label, ListView, TableColumn, TableView}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{BorderPane, ColumnConstraints, GridPane, RowConstraints, VBox}
import scalafx.scene.shape.Path
import scalafx.beans.property.StringProperty

import scala.collection.mutable.Map as MMap
import java.net.URL
import java.util.ResourceBundle

class GameView extends Initializable:

  private val N_COLS_IN_CELL = 4
  private val N_ROWS_IN_CELL = 3

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var playersTable: TableView[Player] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var playerNameColumn: TableColumn[Player, String] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var playerTokenColumn: TableColumn[Player, String] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var playerMoneyColumn: TableColumn[Player, String] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var botColumn: TableColumn[Player, String] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var actionsMenu: VBox = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var throwDiceBtn: Button = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var buildBtn: Button = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var endTurnBtn: Button = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var quitBtn: Button = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var gameBoard: ImageView = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var pane: BorderPane = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var mainGrid: GridPane = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var diceImageView1: ImageView = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var diceImageView2: ImageView = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var propertiesList: ListView[String] = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var turnLabel: Label = _

  private val cellsGrids: MMap[(Int, Int), GridPane] = MMap.empty

  private val tokensImgView: MMap[Token, ImageView] = MMap.empty

  override def initialize(url: URL, rb: ResourceBundle): Unit =
    FxmlUtils.initUIElements(
      pane,
      gameBoard,
      CssResources.GAME_STYLE,
      FxmlUtils.GAME_WIDTH_PERC,
      FxmlUtils.DEFAULT_HEIGHT_PERC
    )

    initCellGrids()
    val menuWidth = FxmlUtils.getResolution._1 - pane.getPrefHeight
    gameBoard.setFitWidth(pane.getPrefHeight)
    mainGrid.setPrefWidth(pane.getPrefHeight)
    actionsMenu.setPrefWidth(menuWidth)

    setDiceImg(diceImageView1)
    setDiceImg(diceImageView2)

    GameReader.players.foreach(p =>
      val tokenImg = new ImageView(new Image(getClass.getResource(p.token.img.path).toString))
      tokensImgView.addOne(p.token, tokenImg)
      setImageViewDimensions(tokenImg)
      updateTokenPosition(p)
    )

    playersTable.setPrefWidth(menuWidth)
    playersTable.setOnMouseClicked(_ => updatePropertiesList())
    playerNameColumn.setCellValueFactory(p => StringProperty(p.getValue.nickname))
    playerTokenColumn.setCellValueFactory(p => StringProperty(p.getValue.token.toString))
    playerMoneyColumn.setCellValueFactory(p => StringProperty(p.getValue.money.toString))
    botColumn.setCellValueFactory(p => StringProperty(if p.getValue.isBot then "Bot" else ""))
    updatePlayersTable()

    updateTurnLabel()

    buildBtn
      .disableProperty()
      .bind(Bindings.isEmpty(propertiesList.getSelectionModel.getSelectedItems))

    GameController.view = this

  /** Remove current player from the game
    */
  def quitBtnClick(): Unit =
    tokensImgView(GameReader.currentPlayer.token).setDisable(true)
    GameController.currentPlayerQuit()
    if GameReader.players.nonEmpty && !EndgameLogicEngine.checkOnlyBotsRemaining then
      setBtnsForEndTurn(false)
      updatePlayersTable()
      updateTurnLabel()

  def diceThrown(dice1: Int, dice2: Int): Unit =
    updateTokenPosition(GameReader.currentPlayer)
    updateDiceImg(dice1, dice2)
    updatePlayersTable() // necessary to update the money in case the player has passed from the Go cell
    GameController.checkPlayerActions()
    updatePlayersTable()
    updatePropertiesList()
    updateTurnLabel()
    tokensImgView.foreach(t =>
      GameReader.players.find(p => p.token == t._1) match
        case None => t._2.setDisable(true)
        case _    =>
    )
    if dice1 != dice2 && !GameReader.currentPlayer.isBot then setBtnsForEndTurn(true)

  /** Throw dice
    */
  def throwDiceBtnClick(): Unit =
    val (dice1, dice2) = GameController.throwDice()
    diceThrown(dice1, dice2)

  /** End turn
    */
  def endTurnBtnClick(): Unit =
    GameController.endTurn()
    setBtnsForEndTurn(false)
    updateTurnLabel()

  /** Build a house on the selected property
    */
  def buildBtnClick(): Unit =
    GameUtils
      .getBuildableSpaceFromName(propertiesList.getSelectionModel.getSelectedItem)
      .foreach(buildableSpace =>
        if GameController.playerBuildsHouse(buildableSpace) then
          updatePlayersTable()
          updateHouseImg(buildableSpace)
      )

  /** Update the turn label with the current player
    */
  def updateTurnLabel(): Unit =
    turnLabel.setText("Turno di " + GameReader.currentPlayer.nickname + "(" + GameReader.currentPlayer.token + ")")

  private def initCellGrids(): Unit =
    val RIGHT_ANGLE = 90
    val CONSTRAINT_PERC = 50
    for
      i <- 0 to GameUtils.CELLS_IN_SIDE
      j <- 0 to GameUtils.CELLS_IN_SIDE
      if ((i == 0 || i == GameUtils.CELLS_IN_SIDE) && (j >= 0 && j <= GameUtils.CELLS_IN_SIDE))
        || ((j == 0 || j == GameUtils.CELLS_IN_SIDE) && (i >= 0 && i <= GameUtils.CELLS_IN_SIDE))
    do
      val tmpGrid = new GridPane()
      spawnColumns(tmpGrid, N_COLS_IN_CELL)
      spawnRows(tmpGrid, N_ROWS_IN_CELL)
      if i == 0 then tmpGrid.setRotate(RIGHT_ANGLE)
      else if i == GameUtils.CELLS_IN_SIDE && j < GameUtils.CELLS_IN_SIDE then tmpGrid.setRotate(-RIGHT_ANGLE)
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

  private def getFirstFreeCellStartingFrom(gridPane: GridPane, nthCell: Int, startingCell: (Int, Int)): (Int, Int) =
    GameUtils.getNthCellInGridWithStartingPos(nthCell + 1, (N_COLS_IN_CELL, N_ROWS_IN_CELL), startingCell)

  private def setBtnsForEndTurn(can: Boolean): Unit =
    endTurnBtn.setDisable(!can)
    throwDiceBtn.setDisable(can)

  private def setDiceImg(diceImgView: ImageView): Unit =
    diceImgView.setFitWidth(pane.getPrefHeight / (GameReader.gameBoard.size / GameUtils.GAMEBOARD_SIDES + 1))

  private def updateDiceImg(dice1: Int, dice2: Int): Unit =
    updateSingleDiceImg(dice1, diceImageView1)
    updateSingleDiceImg(dice2, diceImageView2)

  private def updateSingleDiceImg(dice: Int, diceImageView: ImageView): Unit =
    val dicePath: String = ImgResources.valueOf("DICE_" + dice.toString).path
    diceImageView.setImage(new Image(getClass.getResource(dicePath).toString))

  private def updateHouseImg(buildableSpace: BuildableSpace): Unit =
    val cellGrid = cellsGrids(
      GameUtils
        .getCoordinateFromPosition(GameReader.gameBoard.gameBoardList.indexOf(buildableSpace))
    )
    buildableSpace.numHouse match
      case numHouse if numHouse < BuildableSpace.MAX_HOUSES - 1 =>
        val (col, row) = getFirstFreeCellStartingFrom(cellGrid, numHouse, (0, 0))
        val houseImg = new ImageView(new Image(getClass.getResource(ImgResources.IMG_HOUSE.path).toString))
        setImageViewDimensions(houseImg)
        cellGrid.add(houseImg, col, row)
      case numHouse if numHouse == BuildableSpace.MAX_HOUSES - 1 =>
        val houseImgs = cellGrid.getChildren.filtered(i => !tokensImgView.values.toList.contains(i))
        cellGrid.getChildren.removeAll(houseImgs)
        val (col, row) = getFirstFreeCellStartingFrom(cellGrid, 0, (0, 0))
        val hotelImg = new ImageView(new Image(getClass.getResource(ImgResources.IMG_HOTEL.path).toString))
        setImageViewDimensions(hotelImg)
        cellGrid.add(hotelImg, col, row)
      case _ =>

  private def setImageViewDimensions(imgView: ImageView): Unit =
    imgView.setPreserveRatio(false)
    imgView.setFitWidth(gameBoard.getFitWidth / (GameUtils.CELLS_IN_SIDE + 1) / N_COLS_IN_CELL)
    imgView.setFitHeight(gameBoard.getFitHeight / (GameUtils.CELLS_IN_SIDE + 1) / N_COLS_IN_CELL)

  private def updateTokenPosition(player: Player): Unit =
    val cellGrid = cellsGrids(GameUtils.getCoordinateFromPosition(player.actualPosition))
    val numHouse = GameUtils.getPurchasableSpaceFromPlayerPosition(player) match
      case Some(p) =>
        GameUtils.getBuildableSpaceFromName(p.name) match
          case Some(b) if b.numHouse < BuildableSpace.MAX_HOUSES => b.numHouse
          case Some(_)                                           => 1
          case _                                                 => 0
      case _ => 0
    val (col, row) = getFirstFreeCellStartingFrom(cellGrid, cellGrid.getChildren.size() - numHouse, (0, 1))
    cellGrid.add(tokensImgView(player.token), col, row)

  private def updatePlayersTable(): Unit =
    playersTable.getItems.clear()
    GameReader.players.foreach(p => playersTable.getItems.add(p))

  private def updatePropertiesList(): Unit = playersTable.getSelectionModel.getSelectedItem match
    case p if p != null =>
      propertiesList.getItems.clear()
      p.ownedProperties.foreach(p => propertiesList.getItems.add(p.name))
    case _ =>
