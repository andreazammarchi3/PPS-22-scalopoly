package PPS.scalopoly.view

import PPS.scalopoly.model.{Game, SpaceName}

class CLIUI {
  def showGameBoard(game: Game): Unit = {
    // stampo a video game.gameBoard
    var board = game.gameBoard
    println("Monopoly Board:")
    printMonopolyBoard(board)
    println("\n")
    println("-------------------------------")
  }

  private def printMonopolyBoard(board: Map[Int, SpaceName]): Unit = {
    val rows: List[List[Int]] = List(
      List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
      List(39, -1, -1, -1, -1, -1, -1, -1, -1, -1, 11),
      List(38, -1, -1, -1, -1, -1, -1, -1, -1, -1, 12),
      List(37, -1, -1, -1, -1, -1, -1, -1, -1, -1, 13),
      List(36, -1, -1, -1, -1, -1, -1, -1, -1, -1, 14),
      List(35, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15),
      List(34, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16),
      List(33, -1, -1, -1, -1, -1, -1, -1, -1, -1, 17),
      List(32, -1, -1, -1, -1, -1, -1, -1, -1, -1, 18),
      List(31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 19),
      List(30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20)
    )

    for (row <- rows) {
      for (cell <- row) {
        val spaceName = if (cell == -1) " ".padTo(23, ' ') else board.getOrElse(cell, "").toString.padTo(23, ' ')
        print(f"$spaceName")
      }
      println()
    }
  }
  def showGameStart(): Unit = {
    // stampo a video Linizio del game

  }
}
