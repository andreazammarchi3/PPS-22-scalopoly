package PPS.scalopoly.controller

import javafx.scene.{control as jfxsc, layout as jfxsl}
import javafx.{event as jfxe, fxml as jfxf}
import scalafx.Includes.*
import scalafx.scene.control.Alert.*
import scalafx.scene.layout.{BorderPane, GridPane}

import java.net.URL
import java.util

class StartMenuController extends jfxf.Initializable:

  @jfxf.FXML
  private var startBtn: jfxsc.Button = _
  @jfxf.FXML
  private var exitBtn: jfxsc.Button = _

  @jfxf.FXML
  private var pane: jfxsl.BorderPane = _
  private var borderPane: BorderPane = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    borderPane = new BorderPane(pane)

  //Qua deve chiudere la finestrea attuale e visualizzare quella in cui si aggiungono i giocatori
  def playGame() : Unit = new jfxsc.Alert(AlertType.Information, "Creazione giocatori...").showAndWait()

  def exitGame() : Unit = sys.exit(0)
