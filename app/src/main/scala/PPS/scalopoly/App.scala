package PPS.scalopoly

import PPS.scalopoly.utils.resources.FxmlResources
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene

import java.io.IOException
import java.nio.file.Paths

object App extends JFXApp3:

  def start(): Unit =
    val resource = getClass.getResource(FxmlResources.START_MENU.path())
    if (resource == null)
      throw new IOException("Cannot load resource")
    
    val root: Parent = FXMLLoader.load(resource)

    stage = new PrimaryStage():
      title = "SCALOPOLY"
      scene = new Scene(root)
      resizable = false
