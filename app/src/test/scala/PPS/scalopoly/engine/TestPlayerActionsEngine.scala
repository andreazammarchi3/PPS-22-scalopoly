package PPS.scalopoly.engine

import PPS.scalopoly.BaseTest
import PPS.scalopoly.model.Player
import org.junit.jupiter.api.Assertions.{assertEquals, assertFalse, assertTrue}
import org.junit.jupiter.api.{BeforeEach, Test}

class TestPlayerActionsEngine extends BaseTest:

  @BeforeEach
  override def setup(): Unit =
    GameEngine.newGame()
    players.foreach(p => GameEngine.addPlayer(p))
    GameEngine.startGame()

  @Test
  def testPlayerPaysRent(): Unit =
    val vicoloCorto = GameEngine.gameBoard.purchasableSpaces(0)
    PlayerActionsEngine.playerPaysRent(player1, vicoloCorto, player2)
    GameEngine.players
      .find(p => p.token == player1.token)
      .foreach(
        assertEquals(
          new Player(
            player1.nickname,
            player1.token,
            player1.actualPosition,
            player1.money - vicoloCorto.calculateRent,
            player1.ownedProperties,
            player1.isBot
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
            player2.ownedProperties,
            player2.isBot
          ),
          _
        )
      )

  @Test
  def testPlayerObtainHeritage(): Unit =
    val vicoloCorto = GameEngine.gameBoard.purchasableSpaces(0)
    val MONEY_P1 = 2000 - vicoloCorto.sellingPrice
    PlayerActionsEngine.playerBuysPurchasableSpace(
      player1,
      vicoloCorto
    )
    GameEngine.players
      .find(p => p.token == player1.token)
      .foreach(
        PlayerActionsEngine.playerObtainHeritage(player2, _)
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
            List(vicoloCorto),
            player2.isBot
          ),
          _
        )
      )

  @Test
  def testPlayerPassByGo(): Unit =
    val PASS_GO_MONEY = 200
    val STARTING_MONEY = 2000
    assertEquals(STARTING_MONEY, GameEngine.currentPlayer.money)
    PlayerActionsEngine.playerPassByGo(GameEngine.currentPlayer)
    assertEquals(STARTING_MONEY + PASS_GO_MONEY, GameEngine.currentPlayer.money)

  @Test
  def testPlayerBuildsHouse(): Unit =
    val BUILDABLE_SPACE = GameEngine.gameBoard.buildableSpaces(1)
    PlayerActionsEngine.playerBuildsHouse(player1, BUILDABLE_SPACE)
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
    GameEngine.gameBoard.notPurchasableSpace
      .find(
        _.name == "Tassa di Lusso"
      )
      .foreach(notPurchasableSpace =>
        PlayerActionsEngine.playerOnNotPurchasableSpace(player1, notPurchasableSpace)
        GameEngine.players
          .find(p => p.token == player1.token)
          .foreach(p =>
            assertEquals(
              notPurchasableSpace.action(player1).money,
              p.money
            )
          )
      )
