package PPS.scalopoly.utils

import PPS.scalopoly.model.{DiceManager, GameBoard, Player, Token}
import PPS.scalopoly.utils.GameUtils
import PPS.scalopoly.engine.GameEngine
import org.junit.jupiter.api.Assertions.{
  assertEquals,
  assertFalse,
  assertThrows,
  assertTrue
}
import org.junit.jupiter.api.{BeforeEach, Test}

import scala.util.Random

class TestGameUtils:

  private val PURCHASABLE_SPACE = GameEngine.gameBoard.purchasableSpaces(0)
  private val player =
    new Player("player", Token.DITALE, 0, 0, List(PURCHASABLE_SPACE))

  @BeforeEach
  def setup: Unit =
    GameEngine.newGame()
    GameEngine.addPlayer(player)

  @Test
  def testAddSumToPosition(): Unit =
    val DEFAULT_STARTING_POSITION = 0
    val RANDOM_POSITION = Random.between(0, GameEngine.gameBoard.size / 2)
    val RANDOM_STEPS = Random.between(1, DiceManager.MAX_DICE_VALUE * 2)
    assertEquals(
      RANDOM_POSITION + RANDOM_STEPS,
      GameUtils.addSumToPosition(RANDOM_STEPS, RANDOM_POSITION)
    )
    assertEquals(
      DEFAULT_STARTING_POSITION,
      GameUtils.addSumToPosition(1, GameEngine.gameBoard.size - 1)
    )

  @Test
  def testGetCoordinateFromPosition(): Unit =
    val NEGATIVE_POSITION = -1
    val OVER_MAX_POSITION = GameEngine.gameBoard.size
    assertThrows(
      classOf[IllegalArgumentException],
      () => GameUtils.getCoordinateFromPosition(NEGATIVE_POSITION)
    )
    assertThrows(
      classOf[IllegalArgumentException],
      () => GameUtils.getCoordinateFromPosition(OVER_MAX_POSITION)
    )
    val COORD_FIRST_SIDE = ((9, 10), 1)
    val COORD_SECOND_SIDE = ((0, 9), 11)
    val COORD_THIRD_SIDE = ((1, 0), 21)
    val COORD_FOURTH_SIDE = ((10, 1), 31)
    assertEquals(
      COORD_FIRST_SIDE._1,
      GameUtils.getCoordinateFromPosition(COORD_FIRST_SIDE._2)
    )
    assertEquals(
      COORD_SECOND_SIDE._1,
      GameUtils.getCoordinateFromPosition(COORD_SECOND_SIDE._2)
    )
    assertEquals(
      COORD_THIRD_SIDE._1,
      GameUtils.getCoordinateFromPosition(COORD_THIRD_SIDE._2)
    )
    assertEquals(
      COORD_FOURTH_SIDE._1,
      GameUtils.getCoordinateFromPosition(COORD_FOURTH_SIDE._2)
    )

  @Test
  def getNthCellInGridWithStartingPos(): Unit =
    val DEFAULT_GRID_SIZE = (4, 3)
    val FIRST_CELL_OF_SECOND_ROW = (3, 0)
    val ILLEGAL_GRID_SIZE = (0, 1)
    val LAST_CELL = (3, 2)
    val STARTING_CELL = (0, 0)

    val DEFAULT_STARTING_VALUE = 1
    val ILLEGAL_STARTING_VALUE = 0
    val LAST_CELL_VALUE = 12
    val VALUE_OF_FIRST_CELL_OF_SECOND_ROW = 4

    assertThrows(
      classOf[IllegalArgumentException],
      () =>
        GameUtils.getNthCellInGridWithStartingPos(
          DEFAULT_STARTING_VALUE,
          ILLEGAL_GRID_SIZE,
          STARTING_CELL
        )
    )
    assertThrows(
      classOf[IllegalArgumentException],
      () =>
        GameUtils.getNthCellInGridWithStartingPos(
          DEFAULT_STARTING_VALUE,
          ILLEGAL_GRID_SIZE.swap,
          STARTING_CELL
        )
    )
    assertThrows(
      classOf[IllegalArgumentException],
      () =>
        GameUtils.getNthCellInGridWithStartingPos(
          ILLEGAL_STARTING_VALUE,
          DEFAULT_GRID_SIZE,
          STARTING_CELL
        )
    )
    assertThrows(
      classOf[IllegalArgumentException],
      () =>
        GameUtils.getNthCellInGridWithStartingPos(
          LAST_CELL_VALUE + 1,
          DEFAULT_GRID_SIZE,
          STARTING_CELL
        )
    )
    assertEquals(
      STARTING_CELL,
      GameUtils.getNthCellInGridWithStartingPos(
        DEFAULT_STARTING_VALUE,
        DEFAULT_GRID_SIZE,
        STARTING_CELL
      )
    )
    assertEquals(
      FIRST_CELL_OF_SECOND_ROW,
      GameUtils.getNthCellInGridWithStartingPos(
        VALUE_OF_FIRST_CELL_OF_SECOND_ROW,
        DEFAULT_GRID_SIZE,
        STARTING_CELL
      )
    )
    assertEquals(
      LAST_CELL,
      GameUtils.getNthCellInGridWithStartingPos(
        LAST_CELL_VALUE,
        DEFAULT_GRID_SIZE,
        STARTING_CELL
      )
    )

  @Test
  def testPropertyIsAlreadyOwned(): Unit =
    assertTrue(GameUtils.propertyIsAlreadyOwned(PURCHASABLE_SPACE))
    assertFalse(
      GameUtils.propertyIsAlreadyOwned(
        GameEngine.gameBoard.purchasableSpaces(1)
      )
    )

  @Test
  def testGetOwnerFromPurchasableSpace(): Unit =
    assertEquals(
      Some(player),
      GameUtils.getOwnerFromPurchasableSpace(PURCHASABLE_SPACE)
    )
    assertEquals(
      None,
      GameUtils.getOwnerFromPurchasableSpace(
        GameEngine.gameBoard.purchasableSpaces(1)
      )
    )

  @Test
  def testGetNotPurchasableSpaceFromPlayerPosition(): Unit =
    assertEquals(
      Some(GameEngine.gameBoard.gameBoardList(player.actualPosition)),
      GameUtils.getNotPurchasableSpaceFromPlayerPosition(player)
    )

  @Test
  def testGetBuildableSpaceFromName(): Unit =
    val BUILDABLE_SPACE = GameEngine.gameBoard.buildableSpaces(1)
    assertEquals(
      Some(BUILDABLE_SPACE),
      GameUtils.getBuildableSpaceFromName(BUILDABLE_SPACE.name)
    )
    assertEquals(
      None,
      GameUtils.getBuildableSpaceFromName("NOT_EXIST")
    )
