package PPS.scalopoly

import javafx.{fxml as jfxf, scene as jfxs}
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene

import java.io.IOException
import java.nio.file.Paths

/**
 * Example of using FXMLLoader from ScalaFX.
 *
 * @author Jarek Sacha
 */
object App extends JFXApp3:

  def start(): Unit =

    val resource = getClass.getResource("/fxml/StartMenuFXML.fxml")
    if (resource == null)
      throw new IOException("Cannot load resource")
    
    val root: jfxs.Parent = jfxf.FXMLLoader.load(resource)

    stage = new PrimaryStage():
      title = "SCALOPOLY"
      scene = new Scene(root)
      resizable = false
