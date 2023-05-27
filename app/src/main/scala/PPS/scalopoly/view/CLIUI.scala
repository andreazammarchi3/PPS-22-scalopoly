package PPS.scalopoly.view

import PPS.scalopoly.model._

class CLIUI:
  def showGameBoard(game: Game): Unit =
    // stampo a video gameBoard
    println("Scalopoly Board:")
    printMonopolyBoard(game.gameBoard)
    println("\n")
    println("-------------------------------")

  private def drawCell(gameBoard: GameBoard, cellId: Int): Unit =
    val spaceName = cellId match
      case x if x > -1 => gameBoard.gameBoardMap.getOrElse(cellId, "").toString.padTo(23, ' ')
      case _ => " ".padTo(23, ' ')

    drawCellWithContainer(spaceName)

  private def drawCellWithContainer(cellContent: String): Unit =
    print(" ".padTo(23, ' '))
    print(f"| $cellContent")

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
        println("|")

  def showGameStart(game: Game): Unit =
    // stampo a video l'inizio del gioco
    println("Benvenuti a Scalopoly")
    var validChoise = false
    while (!validChoise)
      println("Premere 'G' se si vuole giocare, premere 'E' se si vuole terminare.")
      // Reads the line from the Console
      val result = scala.io.StdIn.readChar() match
        case 'g' | 'G' => {
          validChoise = true
          println("Che il gioco inizi!")
//          game.startGame()
        }
        case 'e' | 'E' => {
          println("Alla prossima!")
          sys.exit(0)
        }
        case _ => println("Inserire un carattere valido")
  def showGameAddPlayerOrStartPlay(game: Game): Unit =
    // Chiedo di aggiungere giocatori
    println("Premere 'A' per aggiungere giocatore o premere 'P' per avviare la partita")
    scala.io.StdIn.readChar() match
      case 'a' | 'A' =>
        showGameAddPlayers(game)

      case 'p' | 'P' =>
        showGameStartPlay(game)

  def showGameAddPlayers(game: Game): Unit =
    // Chiedo di aggiungere giocatori
    println("Aggiungere giocatore")
//    game.players match
//      case Nil  =>
        println("Inserire nome giocatore")
        scala.io.StdIn.readLine() match
          case null =>
            println("Nessun input. Il gioco verrà terminato.")
            sys.exit(0)

          case "" =>
            println("Nessun nome inserito")
            showGameAddPlayers(game)

          case name =>
            insertPlayerWithRandomToken(game, name)
            showGameAddPlayerOrStartPlay(game)



  def showGameStartPlay(game: Game): Unit =
    println("Inizio partita")
    game.startGame()

  private def insertPlayerWithRandomToken(game: Game, playerName: String): Unit =
    val token = Token.values(util.Random.nextInt(Token.values.length))
    println(f"Benvenuto $playerName, il tuo token sarà $token")
    game.addPlayer(Player(playerName, token)) //TODO: scelta del token

