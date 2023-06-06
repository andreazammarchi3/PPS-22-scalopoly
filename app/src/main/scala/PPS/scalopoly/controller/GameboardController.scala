package PPS.scalopoly.controller

import javafx.scene.{control as jfxsc, layout as jfxsl, image as jfxsi}
import javafx.{event as jfxe, fxml as jfxf}
import scalafx.Includes.*
import scalafx.scene.control.Alert.*
import scalafx.scene.layout.{BorderPane, GridPane}

import java.net.URL
import java.util

class GameboardController extends jfxf.Initializable:

  //Player 1
  @jfxf.FXML
  private var player1: jfxsl.VBox = _

  @jfxf.FXML
  private var nickname1: jfxsc.Label = _

  @jfxf.FXML
  private var money1: jfxsc.Label = _

  @jfxf.FXML
  private var property1: jfxsc.Button = _


  //Player 2
  @jfxf.FXML
  private var player2: jfxsl.VBox = _

  @jfxf.FXML
  private var nickname2: jfxsc.Label = _

  @jfxf.FXML
  private var money2: jfxsc.Label = _

  @jfxf.FXML
  private var property2: jfxsc.Button = _


  //Player 3
  @jfxf.FXML
  private var player3: jfxsl.VBox = _

  @jfxf.FXML
  private var nickname3: jfxsc.Label = _

  @jfxf.FXML
  private var money3: jfxsc.Label = _

  @jfxf.FXML
  private var property3: jfxsc.Button = _


  //Player 4
  @jfxf.FXML
  private var player4: jfxsl.VBox = _

  @jfxf.FXML
  private var nickname4: jfxsc.Label = _

  @jfxf.FXML
  private var money4: jfxsc.Label = _

  @jfxf.FXML
  private var property4: jfxsc.Button = _


  //Player 5
  @jfxf.FXML
  private var player5: jfxsl.VBox = _

  @jfxf.FXML
  private var nickname5: jfxsc.Label = _

  @jfxf.FXML
  private var money5: jfxsc.Label = _

  @jfxf.FXML
  private var property5: jfxsc.Button = _


  //Player 6
  @jfxf.FXML
  private var player6: jfxsl.VBox = _

  @jfxf.FXML
  private var nickname6: jfxsc.Label = _

  @jfxf.FXML
  private var money6: jfxsc.Label = _

  @jfxf.FXML
  private var property6: jfxsc.Button = _


  @jfxf.FXML
  private var bottomMenu: jfxsl.HBox = _

  @jfxf.FXML
  private var bottomLeftMenu: jfxsl.HBox = _

  @jfxf.FXML
  private var bottomRightMenu: jfxsl.VBox = _

  @jfxf.FXML
  private var turnLbl: jfxsc.Label = _

  @jfxf.FXML
  private var throwDiceBtn: jfxsc.Button = _

  @jfxf.FXML
  private var buildBtn: jfxsc.Button = _

  @jfxf.FXML
  private var endTurnBtn: jfxsc.Button = _

  @jfxf.FXML
  private var exitBtn: jfxsc.Button = _

  @jfxf.FXML
  private var gameBoard: jfxsi.ImageView = _

  @jfxf.FXML
  private var pane: jfxsl.BorderPane = _
  private var borderPane: BorderPane = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    val imageUrl = getClass.getResource("/img/Gameboard.png")
    val image = new jfxsi.Image(imageUrl.toString)
    gameBoard.setImage(image)
    gameBoard.setPreserveRatio(false)
    setResolution()

    borderPane = new BorderPane(pane)

  private def setResolution(): Unit =
    val screenResolution = javafx.stage.Screen.getPrimary.getBounds
    val width = screenResolution.getWidth * 0.9
    val height = screenResolution.getHeight * 0.9

    pane.setPrefWidth(width)
    pane.setPrefHeight(height)

    gameBoard.setFitWidth(width - 2)
    gameBoard.setFitHeight(pane.getPrefHeight - bottomMenu.getPrefHeight)
    bottomMenu.setPrefWidth(width - 2) //1726
    bottomLeftMenu.setPrefWidth(width * 2/3)
    bottomRightMenu.setPrefWidth(width * 1/3)
