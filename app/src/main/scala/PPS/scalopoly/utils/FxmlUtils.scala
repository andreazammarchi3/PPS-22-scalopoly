package PPS.scalopoly.utils

import PPS.scalopoly.utils.resources.FxmlResources
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.image.ImageView
import javafx.scene.layout.{AnchorPane, BorderPane, Pane}
import javafx.stage.Screen
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.stage.Stage

import java.io.IOException

/**
 * Utility object that provides methods to load FXML resources and change the current scene.
 */
object FxmlUtils:
  private var _stage: Stage = _
  private var width: Double = _
  private var height: Double = _

  /**
   * Returns the current stage.
   * @return the current stage.
   */
  def stage: Stage = _stage

  /**
   * Sets the current stage.
   * @param value the stage to set.
   */
  def stage_=(value: Stage): Unit =
    _stage = value

  private def loadFXMLResource(fxmlPath: String): Scene =
    val fxmlFile = getClass.getResource(fxmlPath)
    if (fxmlFile == null)
      throw new IOException("Cannot load resource: " + fxmlPath)
    val root: Parent = FXMLLoader.load(fxmlFile)
    new Scene(root)

  /**
   * Changes the current scene.
   * @param fxmlPath the path of the FXML resource to load.
   */
  def changeScene(fxmlPath: String): Unit =
    val scene = loadFXMLResource(fxmlPath)
    stage.setScene(scene)

  /**
   * Create the primary stage to be used in the application.
   */
  def createPrimaryStage(): Unit =
    stage = new PrimaryStage:
      title.value = "SCALOPOLY"
      scene = FxmlUtils.loadFXMLResource(FxmlResources.START_MENU.path)
      resizable = false

  /**
   * Sets the resolution of the game board to be the same as the resolution of the pane.
   * @param pane the pane containing the game board.
   * @param gameBoard the game board.
   */
  def setGameBoardSize(pane: BorderPane, gameBoard: ImageView): Unit =
    val (width, height) = getResolution
    val gameBoardSize = pane.getPrefHeight
    gameBoard.setFitWidth(gameBoardSize)
    gameBoard.setFitHeight(gameBoardSize)

  /**
   * Sets the resolution of the pane to be scaled according to the screen resolution.
   * @param pane the pane to scale.
   * @param widthPerc the percentage of the screen width to use.
   * @param heightPerc the percentage of the screen height to use.
   */
  def setPaneResolution(
      pane: BorderPane,
      widthPerc: Double,
      heightPerc: Double
  ): Unit =
    val screenResolution = Screen.getPrimary.getBounds
    width = screenResolution.getWidth * widthPerc
    height = screenResolution.getHeight * heightPerc
    pane.setPrefWidth(width)
    pane.setPrefHeight(height)

  /**
   * Gets the resolution of the screen.
   * @return the resolution of the screen.
   */
  def getResolution: (Double, Double) =
    (width, height)
