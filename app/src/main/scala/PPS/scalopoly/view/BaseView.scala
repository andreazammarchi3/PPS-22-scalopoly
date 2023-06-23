package PPS.scalopoly.view

import javafx.fxml.Initializable

import java.net.URL
import java.util.ResourceBundle

/** Trait that represents a view of the game.
  */
trait BaseView extends Initializable:
  override def initialize(location: URL, resources: ResourceBundle): Unit =
    initUIElements()

  /** Initializes the UI elements of the view.
    */
  protected def initUIElements(): Unit
