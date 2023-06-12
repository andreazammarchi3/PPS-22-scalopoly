package PPS.scalopoly.utils

import PPS.scalopoly.utils.resources.FxmlResources
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.layout.{AnchorPane, Pane}
import javafx.stage.Screen
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.stage.Stage

import java.io.IOException

object FxmlUtils:
  private var _stage: Stage = _
  private var width: Double = _
  private var height: Double = _

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

  def setResolution(pane: Pane, widthPerc: Double, heightPerc: Double): Unit =
    val screenResolution = Screen.getPrimary.getBounds
    width = screenResolution.getWidth * widthPerc
    height = screenResolution.getHeight * heightPerc
    pane.setPrefWidth(width)
    pane.setPrefHeight(height)
    
  def getResolution: (Double, Double) =
    (width, height)
