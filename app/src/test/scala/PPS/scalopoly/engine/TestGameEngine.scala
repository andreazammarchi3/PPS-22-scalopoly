package PPS.scalopoly.engine

import PPS.scalopoly.BaseTest
import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils
import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

@Test
class TestGameEngine extends BaseTest:

  @BeforeEach
  override def setup(): Unit =
    GameEngine.newGame()
    players.foreach(p => GameEngine.addPlayer(p))
    GameEngine.startGame()

  @Test
  def testStartGame(): Unit =
    assertEquals(players.length, GameEngine.players.length)
    players.foreach(p => assertTrue(GameEngine.players.contains(p)))

  @Test
  def testEndTurn(): Unit =
    val shuffledPlayer = GameEngine.players
    for i <- 1 until players.length
    do
      GameEngine.endTurn()
      assertTrue(GameEngine.currentPlayer.equals(shuffledPlayer(i)))

    GameEngine.endTurn()
    assertTrue(GameEngine.currentPlayer.equals(shuffledPlayer(0)))

  @Test
  def testMoveCurrentPlayer(): Unit =
    val DEFAULT_STARTING_POSITION = 0
    val startPosition = GameEngine.currentPlayer.actualPosition
    assertEquals(DEFAULT_STARTING_POSITION, startPosition)
    val dicePair = DiceManager().roll()
    val sum = dicePair._1 + dicePair._2
    GameEngine.moveCurrentPlayer(sum)
    assertTrue(dicePair._1.isValidInt && dicePair._2.isValidInt)
    assertEquals(
      startPosition + sum,
      GameEngine.currentPlayer.actualPosition
    )

  @Test
  def testCurrentPlayerQuit(): Unit =
    for i <- players.length - 1 to 1
    do
      val deletedPlayer = GameEngine.currentPlayer
      GameEngine.currentPlayerQuit()
      assertEquals(i, GameEngine.players.length)
      assertTrue(!GameEngine.players.contains(deletedPlayer))
    GameEngine.currentPlayerQuit()

  @Test
  def testAvailableTokens(): Unit =
    assertEquals(
      Token.values.length - players.length,
      GameEngine.availableTokens.length
    )
    players.foreach(p =>
      assertFalse(GameEngine.availableTokens.contains(p.token))
    )

  @Test
  def testCanStartGame(): Unit =
    assertTrue(GameEngine.canStartGame)
    players.foreach(p => GameEngine.removePlayer(p))
    assertFalse(GameEngine.canStartGame)
    GameEngine.addPlayer(player1)
    assertFalse(GameEngine.canStartGame)
    GameEngine.addPlayer(player2)
    assertTrue(GameEngine.canStartGame)

  @Test
  def testCanAddPlayer(): Unit =
    assertTrue(GameEngine.canAddPlayer)
    GameEngine.addPlayer(player4)
    assertTrue(GameEngine.canAddPlayer)
    GameEngine.addPlayer(player5)
    assertTrue(GameEngine.canAddPlayer)
    GameEngine.addPlayer(player6)
    assertFalse(GameEngine.canAddPlayer)

  @Test
  def testWinner(): Unit =
    assertEquals(None, GameEngine.winner)
    Game.winner = Some(player1)
    assertEquals(Some(player1), GameEngine.winner)

  @Test
  def testCheckSpaceStatus(): Unit =
    // VICOLO_CORTO must be PURCHASABLE
    GameEngine.moveCurrentPlayer(1)
    assertEquals(SpaceStatus.PURCHASABLE, GameEngine.checkSpaceStatus)
    GameUtils
      .getPurchasableSpaceFromPlayerPosition(GameEngine.currentPlayer)
      .foreach(p =>
        GameEngine.playerBuysPurchasableSpace(GameEngine.currentPlayer, p)
      )

    // PROBABILITA' must be NOT_PURCHASABLE
    GameEngine.moveCurrentPlayer(1)
    assertEquals(SpaceStatus.NOT_PURCHASABLE, GameEngine.checkSpaceStatus)

    // VICOLO_CORTO must be OWNED_BY_CURRENT_PLAYER
    GameEngine.moveCurrentPlayer(39)
    assertEquals(
      SpaceStatus.OWNED_BY_CURRENT_PLAYER,
      GameEngine.checkSpaceStatus
    )

    // VICOLO_CORTO must be OWNED_BY_ANOTHER_PLAYER
    GameEngine.endTurn()
    GameEngine.moveCurrentPlayer(1)
    assertEquals(
      SpaceStatus.OWNED_BY_ANOTHER_PLAYER,
      GameEngine.checkSpaceStatus
    )

  @Test
  def testPlayerPaysRent(): Unit =
    val vicoloCorto = GameEngine.gameBoard.purchasableSpaces(0)
    GameEngine.playerPaysRent(player1, vicoloCorto, player2)
    GameEngine.players
      .find(p => p.token == player1.token)
      .foreach(
        assertEquals(
          new Player(
            player1.nickname,
            player1.token,
            player1.actualPosition,
            player1.money - vicoloCorto.calculateRent,
            player1.ownedProperties
          ),
          _
        )
      )
    GameEngine.players
      .find(p => p.token == player2.token)
      .foreach(
        assertEquals(
          new Player(
            player2.nickname,
            player2.token,
            player2.actualPosition,
            player2.money + vicoloCorto.calculateRent,
            player2.ownedProperties
          ),
          _
        )
      )

  @Test
  def testPlayerObtainHeritage(): Unit =
    val vicoloCorto = GameEngine.gameBoard.purchasableSpaces(0)
    val MONEY_P1 = 2000 - vicoloCorto.sellingPrice
    GameEngine.playerBuysPurchasableSpace(
      player1,
      vicoloCorto
    )
    GameEngine.players
      .find(p => p.token == player1.token)
      .foreach(
        GameEngine.playerObtainHeritage(player2, _)
      )
    GameEngine.players
      .find(p => p.token == player2.token)
      .foreach(
        assertEquals(
          new Player(
            player2.nickname,
            player2.token,
            player2.actualPosition,
            player2.money + MONEY_P1,
            List(vicoloCorto)
          ),
          _
        )
      )

  @Test
  def testPlayerPassByGo(): Unit =
    val PASS_GO_MONEY = 200
    val STARTING_MONEY = 2000
    assertEquals(STARTING_MONEY, GameEngine.currentPlayer.money)
    GameEngine.playerPassByGo(GameEngine.currentPlayer)
    assertEquals(STARTING_MONEY + PASS_GO_MONEY, GameEngine.currentPlayer.money)

  @Test
  def testPlayerBuildsHouse(): Unit =
    val BUILDABLE_SPACE = GameEngine.gameBoard.buildableSpaces(1)
    GameEngine.playerBuildsHouse(player1, BUILDABLE_SPACE)
    GameEngine.players
      .find(p => p.token == player1.token)
      .foreach(p =>
        assertEquals(player1.money - BUILDABLE_SPACE.buildingCost, p.money)
        p.ownedProperties
          .find(_.name == BUILDABLE_SPACE.name)
          .foreach(property =>
            property match
              case b: PPS.scalopoly.model.space.purchasable.BuildableSpace =>
                assertEquals(
                  BUILDABLE_SPACE.numHouse + 1,
                  b.numHouse
                )
          )
      )

  @Test
  def testPlayerOnNotPurchasableSpace(): Unit =
    val NOT_PURCHASABLE_SPACE = GameEngine.gameBoard.notPurchasableSpace
      .find(
        _.name == "Tassa di Lusso"
      )
      .get
    GameEngine.playerOnNotPurchasableSpace(player1, NOT_PURCHASABLE_SPACE)
    GameEngine.players
      .find(p => p.token == player1.token)
      .foreach(p =>
        assertEquals(
          NOT_PURCHASABLE_SPACE.action(player1).money,
          p.money
        )
      )
