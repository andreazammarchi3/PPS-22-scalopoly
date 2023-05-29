package PPS.scalopoly

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.model.Game
import PPS.scalopoly.view.GameViewCLI

object App:
  def main(args: Array[String]): Unit =
    val model = new Game
    val view = new GameViewCLI
    val controller = new GameController(model, view)

    controller.initialize()

    controller.run()
