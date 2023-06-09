package PPS.scalopoly.controller

import PPS.scalopoly.model.{Player, Token}
import javafx.scene.{control as jfxsc, image as jfxsi, layout as jfxsl}
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

  private def TEMPtoDELETE(): Unit =
    GameController.addPlayer(Player("Andrea", Token.FUNGO))
    GameController.addPlayer(Player("Zak", Token.NAVE))
    GameController.addPlayer(Player("Mic", Token.GATTO))
    GameController.addPlayer(Player("Luca", Token.DITALE))
    GameController.addPlayer(Player("Enri", Token.AUTOMOBILE))
    GameController.addPlayer(Player("Baldo", Token.CARRIOLA))

    GameController.startGame()


  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    TEMPtoDELETE()
    val imageUrl = getClass.getResource("/img/Gameboard.png")
    val image = new jfxsi.Image(imageUrl.toString)
    gameBoard.setImage(image)
    gameBoard.setPreserveRatio(false)
    setResolution()
    setBottomMenu()
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

  private def setBottomMenu(): Unit =
    GameController.players.length match
      case 2 =>
        player3.setVisible(false)
        player4.setVisible(false)
        player5.setVisible(false)
        player6.setVisible(false)
        setPlayer1()
        setPlayer2()
      case 3 =>
        player4.setVisible(false)
        player5.setVisible(false)
        player6.setVisible(false)
        setPlayer1()
        setPlayer2()
        setPlayer3()
      case 4 =>
        player5.setVisible(false)
        player6.setVisible(false)
        setPlayer1()
        setPlayer2()
        setPlayer3()
        setPlayer4()
      case 5 =>
        player6.setVisible(false)
        setPlayer1()
        setPlayer2()
        setPlayer3()
        setPlayer4()
        setPlayer5()
      case _ =>
        setPlayer1()
        setPlayer2()
        setPlayer3()
        setPlayer4()
        setPlayer5()
        setPlayer6()

    turnLbl.setText("Tocca a: " + GameController.currentPlayer.get.nickname)

    def setPlayer1(): Unit =
      nickname1.setText(GameController.players(0).nickname +
        " (" + GameController.players(0).token + ")")
      money1.setText("9999 €")

    def setPlayer2(): Unit =
      nickname2.setText(GameController.players(1).nickname +
        " (" + GameController.players(1).token + ")")
      money2.setText("9999 €")

    def setPlayer3(): Unit =
      nickname3.setText(GameController.players(2).nickname +
        " (" + GameController.players(2).token + ")")
      money3.setText("9999 €")

    def setPlayer4(): Unit =
      nickname4.setText(GameController.players(3).nickname +
        " (" + GameController.players(3).token + ")")
      money4.setText("9999 €")

    def setPlayer5(): Unit =
      nickname5.setText(GameController.players(4).nickname +
        " (" + GameController.players(4).token + ")")
      money5.setText("9999 €")

    def setPlayer6(): Unit =
      nickname6.setText(GameController.players(5).nickname +
        " (" + GameController.players(5).token + ")")
      money6.setText("9999 €")