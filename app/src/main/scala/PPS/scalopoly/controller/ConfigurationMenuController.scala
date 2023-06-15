package PPS.scalopoly.controller

import javafx.fxml.{FXML, FXMLLoader, Initializable}

import java.net.URL
import java.util

class ConfigurationMenuController extends Initializable:

  @FXML
  private var startBtn: Button = _
  @FXML
  private var exitBtn: Button = _
    override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    var a = 1


  def playGame(): Unit =
    FxmlUtils.changeScene(FxmlResources.GAME_VIEW.path)

  def exitGame(): Unit = sys.exit(0)