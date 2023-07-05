package PPS.scalopoly.model

import PPS.scalopoly.deserialization.{
  BuildableSpaceJsonReader,
  CompanySpaceJsonReader,
  NotPurchasableSpaceJsonReader,
  SpacesJsonReader,
  StationSpaceJsonReader
}
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import PPS.scalopoly.model.space.purchasable.{
  BuildableSpace,
  CompanySpace,
  PurchasableSpace,
  StationSpace
}
import PPS.scalopoly.model.space.{Space, SpaceImpl}
import PPS.scalopoly.utils.JsonUtils
import PPS.scalopoly.utils.resources.JsonResources

class GameBoard(
    spaces: List[Space],
    _buildableSpaces: List[BuildableSpace],
    _companySpaces: List[CompanySpace],
    _stationSpaces: List[StationSpace],
    _notPurchasableSpace: List[NotPurchasableSpace]
):

  def companySpaces: List[CompanySpace] = _companySpaces

  def stationSpaces: List[StationSpace] = _stationSpaces

  def buildableSpaces: List[BuildableSpace] = _buildableSpaces

  def notPurchasableSpace: List[NotPurchasableSpace] = _notPurchasableSpace

  def purchasableSpaces: List[PurchasableSpace] =
    _companySpaces ++ _stationSpaces ++ _buildableSpaces

  def updateBuildableSpace(buildableSpace: BuildableSpace): GameBoard =
    val space = spaces.find(_.name == buildableSpace.name)
    val build = buildableSpaces.find(_.name == buildableSpace.name)
    (space, build) match
      case (Some(space), Some(build)) =>
        val (spaceIndex, buildIndex) =
          (spaces.indexOf(space), buildableSpaces.indexOf(build))
        GameBoard(
          spaces.updated(spaceIndex, buildableSpace),
          buildableSpaces.updated(buildIndex, buildableSpace),
          _companySpaces,
          _stationSpaces,
          _notPurchasableSpace
        )
      case _ => this

  /** Returns the game board map.
    *
    * @return
    *   the game board map.
    */
  def gameBoardList: List[Space] = spaces

  /** Returns the size of the game board.
    *
    * @return
    *   the size of the game board.
    */
  def size: Int = spaces.length

/** The game board is a map of the game spaces represented by a list of
  * [[SpaceName]].
  */
object GameBoard:
  def apply(): GameBoard = new GameBoard(
    JsonUtils.readTypeSpaces[Space](JsonResources.SPACES, SpacesJsonReader),
    JsonUtils.readTypeSpaces[BuildableSpace](
      JsonResources.BUILDABLE_SPACES,
      BuildableSpaceJsonReader
    ),
    JsonUtils.readTypeSpaces[CompanySpace](
      JsonResources.COMPANY_SPACES,
      CompanySpaceJsonReader
    ),
    JsonUtils.readTypeSpaces[StationSpace](
      JsonResources.STATION_SPACES,
      StationSpaceJsonReader
    ),
    JsonUtils.readTypeSpaces[NotPurchasableSpace](
      JsonResources.NOT_PURCHASABLE_SPACES,
      NotPurchasableSpaceJsonReader
    )
  )
  def apply(
      spaces: List[Space],
      buildableSpaces: List[BuildableSpace],
      companySpaces: List[CompanySpace],
      stationSpaces: List[StationSpace],
      notPurchasableSpace: List[NotPurchasableSpace]
  ): GameBoard =
    new GameBoard(
      spaces,
      buildableSpaces,
      companySpaces,
      stationSpaces,
      notPurchasableSpace
    )
