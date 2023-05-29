package PPS.scalopoly.view

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.model.*

import scala.annotation.tailrec
import scala.util.Try

class GameViewCLI:
  private var gameController: GameController = _

  def setController(controller: GameController): Unit =
    gameController = controller

  def getController: GameController = gameController

  def showGameBoard(game: Game): Unit =
    // stampo a video gameBoard
    println("Tabellone Scalopoly:")
    printMonopolyBoard(gameController.gameBoard)
    println("\n")
    printMonopolyPlayerStatus(game)
    println("-------------------------------")

  private def drawCell(gameBoard: GameBoard, cellId: Int): Unit =
    val spaceName = cellId match
      case x if x > -1 =>
        gameBoard.gameBoardMap.getOrElse(cellId, "").toString.padTo(23, ' ')
      case _ => " ".padTo(23, ' ')

    drawCellWithContainer(spaceName)

  private def drawCellWithContainer(cellContent: String): Unit =
    if (cellContent == null || cellContent.isEmpty) print("")
    else print(f"| $cellContent")

  private def printMonopolyPlayerStatus(game: Game): Unit =
    game.players match
      case l if l.nonEmpty =>
        game.currentPlayer match
          case Some(player: Player) =>
            println(f"È il turno del giocatore ${player.nickname}")
          case _ => println("")

        println("Giocatori:")
        game.players.foreach(x =>
          println(
            f"${x.nickname}, ${x.token}. Posizione ${gameController.getSpaceNameFromPlayerPosition(x)}"
          )
        )
      case _ =>
        println("Nessun giocatore presente.")

  private def printMonopolyBoard(gameBoard: GameBoard): Unit =
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
    for (row <- rows)
      // Stampa il contenuto delle celle
      for (cell <- row)
        drawCell(gameBoard, cell)
      println("")

  def showGameStart(game: Game): Unit =
    // stampo a video l'inizio del gioco
    println("Benvenuti a Scalopoly")
    var validChoise = false
    while (!validChoise)
      println(
        "Premere 'G' se si vuole giocare, premere 'E' se si vuole terminare."
      )
      // Reads the line from the Console
      scala.io.StdIn.readChar() match
        case 'g' | 'G' =>
          validChoise = true
          println("Che il gioco inizi!")
          showGameAddPlayers(game)
        case 'e' | 'E' =>
          exitGame()
        case _ => println("Inserire un carattere valido")

  def showGameAddPlayerOrStartPlay(game: Game): Unit =
    // Chiedo di aggiungere giocatori
    println(
      "Premere 'A' per aggiungere giocatore o premere 'P' per avviare la partita"
    )
    scala.io.StdIn.readChar() match
      case 'a' | 'A' =>
        showGameAddPlayers(game)
      case 'p' | 'P' =>
        showGameStartPlay(game)
      case 'e' | 'E' =>
        exitGame()
      case _ => println("Inserire un carattere valido")

  def showGameAddPlayers(game: Game): Unit =
    println("Benvenuto nuovo giocatore, inserisci il tuo nickname:")
    scala.io.StdIn.readLine() match
      case null | "" =>
        println("Nessun nickname inserito.")
        showGameAddPlayers(game)
      case name =>
        val token = askForAToken(game, name)
        insertPlayer(game, name, token)
        showGameAddPlayerOrStartPlay(game)

  private def showGameStartPlay(game: Game): Unit =
    println("Inizio partita")
    gameController.startGame()
    showAskCurrentUserToRollDiceOrQuit(game)

  private def insertPlayer(game: Game, playerName: String, token: Token): Unit =
    println(f"$playerName, il tuo token sarà $token")
    game.addPlayer(Player(playerName, token))

  @tailrec
  private def askForAToken(game: Game, playerName: String): Token =
    println(
      f"Benvenuto $playerName, scegli il tuo token tra quelli disponibili:"
    )
    game.availableTokens.foreach(x =>
      println(f"Premere ${game.availableTokens.indexOf(x)} per ${x.toString}")
    )
    tryToInt(scala.io.StdIn.readLine()) match
      case Some(position: Int) => game.availableTokens(position)
      case _ =>
        showInputNotValid()
        askForAToken(game, playerName)

  private def showInputNotValid(): Unit =
    println("Input non valido.")

  def showAskCurrentUserToRollDiceOrQuit(game: Game): Unit =
    printMonopolyPlayerStatus(game)
    println(
      f"${game.currentPlayer.get.nickname}, premi 1 per lanciare i dadi, 2 per abbandonare la partita"
    )
    tryToInt(scala.io.StdIn.readLine()) match
      case Some(position: Int) =>
        position match
          case 1 =>
            println(
              f"${game.currentPlayer.get.nickname}, ha lanciato i dadi e procede."
            )
            gameController.moveCurrentPlayer()
            println(
              f"Dai dadi hai ottenuto ${gameController.dice.dice1} e ${gameController.dice.dice2}, per un totale di ${gameController.dice.sum()}"
            )
            printMonopolyPlayerStatus(game)
            if (gameController.dice.checkSame())
              showAskCurrentUserToRollDiceOrQuit(game)
            else
              showAskCurrentPlayerEndTurnOrOrQuit(game)
          case 2 =>
            playerQuit(game)
          case _ =>
            showInputNotValid()
            showAskCurrentUserToRollDiceOrQuit(game)
      case _ =>
        showInputNotValid()
        showAskCurrentUserToRollDiceOrQuit(game)

  def showAskCurrentPlayerEndTurnOrOrQuit(game: Game): Unit =
    println(
      f"${game.currentPlayer.get.nickname}, premi 1 per terminare il turno, 2 per abbandonare la partita"
    )
    tryToInt(scala.io.StdIn.readLine()) match
      case Some(position: Int) =>
        position match
          case 1 =>
            println(f"${game.currentPlayer.get.nickname} termina il turno.")
            gameController.endTurn()
            showAskCurrentUserToRollDiceOrQuit(game)
          case 2 =>
            playerQuit(game)
          case _ =>
            showInputNotValid()
            showAskCurrentUserToRollDiceOrQuit(game)
      case _ =>
        showInputNotValid()
        showAskCurrentUserToRollDiceOrQuit(game)

  private def playerQuit(game: Game): Unit =
    println(f"${game.currentPlayer.get.nickname} abbandona la partita.")
    gameController.currentPlayerQuit()
    showAskCurrentUserToRollDiceOrQuit(game)

  private def tryToInt(s: String) = Try(s.toInt).toOption

  private def exitGame(): Unit =
    println("Partita terminata. A presto!")
    gameController.exitGame()
