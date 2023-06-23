package PPS.scalopoly.utils

import PPS.scalopoly.utils.resources.{FxmlResources, ImgResources}
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{AnchorPane, BorderPane, Pane}
import javafx.stage.Screen
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.stage.Stage

import java.io.IOException

/** Utility object that provides methods to load FXML resources and change the
  * current scene.
  */
object FxmlUtils:
  private var _stage: Stage = _
  private var width: Double = _
  private var height: Double = _

  /** Returns the current stage.
    * @return
    *   the current stage.
    */
  def stage: Stage = _stage

  /** Sets the current stage.
    * @param value
    *   the stage to set.
    */
  def stage_=(value: Stage): Unit =
    _stage = value

  /** Changes the current scene.
    * @param fxmlPath
    *   the path of the FXML resource to load.
    */
  def changeScene(fxmlPath: String): Unit =
    val scene = loadFXMLResource(fxmlPath)
    stage.setScene(scene)

  /** Create the primary stage to be used in the application.
    */
  def createPrimaryStage(): Unit =
    stage = new PrimaryStage:
      title.value = "SCALOPOLY"
      scene = FxmlUtils.loadFXMLResource(FxmlResources.START_MENU.path)
      resizable = false

  /** Sets the resolution of the game board to be the same as the resolution of
    * the pane.
    * @param pane
    *   the pane containing the game board.
    * @param gameBoard
    *   the game board.
    */
  def setGameBoardSize(pane: BorderPane, gameBoard: ImageView): Unit =
    val gameBoardSize = pane.getPrefHeight
    gameBoard.setFitWidth(gameBoardSize)
    gameBoard.setFitHeight(gameBoardSize)

  /** Sets the image of the game board.
    * @param gameBoard
    *   the game board.
    */
  def setGameBoardImage(gameBoard: ImageView): Unit =
    gameBoard.setImage(
      new Image(
        getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString
      )
    )
    gameBoard.setPreserveRatio(false)

  /** Sets the resolution of the pane to be scaled according to the screen
    * resolution.
    * @param pane
    *   the pane to scale.
    * @param widthPerc
    *   the percentage of the screen width to use.
    * @param heightPerc
    *   the percentage of the screen height to use.
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

  /** Gets the resolution of the screen.
    * @return
    *   the resolution of the screen.
    */
  def getResolution: (Double, Double) =
    (width, height)

  /** Shows an alert.
    * @param alertType
    *   the type of the alert.
    * @param title
    *   the title of the alert.
    * @param headerText
    *   the header text of the alert.
    * @param contentText
    *   the content text of the alert.
    */
  def showAlert(
      alertType: AlertType,
      title: String,
      headerText: String,
      contentText: String
  ): Unit =
    val alert = new Alert(alertType)
    alert.setTitle(title)
    alert.setHeaderText(headerText)
    alert.setContentText(contentText)
    alert.showAndWait()

  private def loadFXMLResource(fxmlPath: String): Scene =
    val fxmlFile = getClass.getResource(fxmlPath)
    if (fxmlFile == null)
      throw new IOException("Cannot load resource: " + fxmlPath)
    val root: Parent = FXMLLoader.load(fxmlFile)
    new Scene(root)
