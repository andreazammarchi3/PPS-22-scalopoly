package PPS.scalopoly.utils

import PPS.scalopoly.utils.resources.{CssResources, FxmlResources, ImgResources}
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.{Alert, ButtonType}
import javafx.scene.control.Alert.AlertType
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.{BorderPane, Pane}
import javafx.stage.Screen
import scalafx.Includes.*
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene
import scalafx.stage.Stage

import java.io.IOException
import java.util.Optional

/** Utility object that provides methods to load FXML resources and change the current scene.
  */
object FxmlUtils:

  val DEFAULT_WIDTH_PERC: Double = 0.9
  val GAME_WIDTH_PERC: Double = 0.8
  val DEFAULT_HEIGHT_PERC: Double = 0.9

  @SuppressWarnings(
    Array("org.wartremover.warts.Null")
  )
  private var _stage: Stage = _

  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var width: Double = _

  @SuppressWarnings(
    Array("org.wartremover.warts.Null")
  )
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
    stage setScene scene

  /** Create the primary stage to be used in the application.
    */
  def createPrimaryStage(): Unit =
    stage = new PrimaryStage:
      title.value = "SCALOPOLY"
      scene = FxmlUtils.loadFXMLResource(FxmlResources.START_MENU.path)
      resizable = false

  /** Initialize the UI elements of the game.
    * @param pane
    *   the pane to initialize
    * @param gameBoard
    *   the game board imageView
    * @param cssResources
    *   the css style to apply
    * @param width_perc
    *   the width percentage
    * @param height_perc
    *   the height percentage
    */
  def initUIElements(
      pane: BorderPane,
      gameBoard: ImageView,
      cssResources: CssResources,
      width_perc: Double,
      height_perc: Double
  ): Unit =
    setPaneResolution(pane, width_perc, height_perc)
    setGameBoardImage(gameBoard)
    setGameBoardSize(pane, gameBoard)
    setPaneStyle(pane, cssResources)

  /** Gets the resolution of the screen.
    * @return
    *   the resolution of the screen.
    */
  def getResolution: (Double, Double) =
    (width, height)

  /** Shows an alert.
    *
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
  ): Optional[ButtonType] =
    val alert = new Alert(alertType)
    alert setTitle title
    alert setHeaderText headerText
    alert setContentText contentText
    alert.showAndWait()

  private def loadFXMLResource(fxmlPath: String): Scene =
    val fxmlFile = getClass.getResource(fxmlPath)
    if fxmlFile == null then throw new IOException("Cannot load resource: " + fxmlPath)
    val root: Parent = FXMLLoader load fxmlFile
    new Scene(root)

  private def setGameBoardSize(pane: BorderPane, gameBoard: ImageView): Unit =
    val gameBoardSize = pane.getPrefHeight
    gameBoard setFitWidth gameBoardSize
    gameBoard setFitHeight gameBoardSize

  private def setGameBoardImage(gameBoard: ImageView): Unit =
    gameBoard.setImage(new Image(getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString))
    gameBoard.setPreserveRatio(false)

  private def setPaneResolution(pane: BorderPane, widthPerc: Double, heightPerc: Double): Unit =
    val screenResolution = Screen.getPrimary.getBounds
    width = screenResolution.getWidth * widthPerc
    height = screenResolution.getHeight * heightPerc
    pane setPrefWidth width
    pane setPrefHeight height

  private def setPaneStyle(pane: BorderPane, cssResources: CssResources): Unit =
    pane.getStylesheets.add(getClass.getResource(cssResources.path).toExternalForm)
