package PPS.scalopoly.controller

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.{Player, Token}
import PPS.scalopoly.utils.resources.ImgResources
import javafx.scene.layout.{BorderPane, HBox, VBox}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, Label}
import javafx.scene.image.{Image, ImageView}
import javafx.stage.Screen

import java.net.URL
import java.util

class GameViewController extends Initializable:

  @FXML
  private val bottomMenu: HBox = new HBox()

  @FXML
  private val playerListHBox: HBox = new HBox()

  @FXML
  private val bottomRightMenu: VBox = new VBox()

  @FXML
  private val turnLbl: Label = new Label()

  @FXML
  private val throwDiceBtn: Button = new Button()

  @FXML
  private val buildBtn: Button = new Button()

  @FXML
  private val endTurnBtn: Button = new Button()

  @FXML
  private val exitBtn: Button = new Button()

  @FXML
  private val gameBoard: ImageView = new ImageView()

  @FXML
  private val pane: BorderPane = new BorderPane()


  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    //GameEngine.startGame()
    val image = new Image(getClass.getResource(ImgResources.GAMEBOARD.path).toString)
    gameBoard.setImage(image)
    gameBoard.setPreserveRatio(false)
    setResolution()

  private def setResolution(): Unit =
    val screenResolution = Screen.getPrimary.getBounds
    val width = screenResolution.getWidth * 0.9
    val height = screenResolution.getHeight * 0.9

    pane.setPrefWidth(width)
    pane.setPrefHeight(height)

    gameBoard.setFitWidth(width - 2)
    gameBoard.setFitHeight(pane.getPrefHeight - bottomMenu.getPrefHeight)
    bottomMenu.setPrefWidth(width - 2)
    playerListHBox.setPrefWidth(width * 2/3)
    bottomRightMenu.setPrefWidth(width * 1/3)
