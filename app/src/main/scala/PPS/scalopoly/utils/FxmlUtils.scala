package PPS.scalopoly.utils

import PPS.scalopoly.utils.resources.FxmlResources
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.layout.{AnchorPane, Pane}
import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.stage.Stage

import java.io.IOException

object FxmlUtils:
  private var _stage: Stage = _

  def stage: Stage = _stage

  def stage_=(value: Stage): Unit =
    _stage = value


  private def loadFXMLResource(fxmlPath: String): Scene =
    val fxmlFile = getClass.getResource(fxmlPath)
    if (fxmlFile == null)
      throw new IOException("Cannot load resource: " + fxmlPath)
    val root: Parent = FXMLLoader.load(fxmlFile)
    new Scene(root)

  def changeScene(fxmlPath: String): Unit =
    val scene = loadFXMLResource(fxmlPath)
    stage.setScene(scene)

  def createPrimaryStage(): Unit =
    stage = new PrimaryStage:
      title.value = "SCALOPOLY"
      scene = FxmlUtils.loadFXMLResource(FxmlResources.START_MENU.path)
      resizable = false
