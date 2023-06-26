package PPS.scalopoly.view

import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.BorderPane

import java.net.URL
import java.util.ResourceBundle

class VictoryView extends Initializable:

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var pane: BorderPane = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var victoryLbl: Label = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var backToMenuBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var exitBtn: Button = _

  override def initialize(location: URL, resources: ResourceBundle): Unit = ???
  
  def backToMenuClick(): Unit = ???

  def exitGameBtnClick(): Unit = ???
