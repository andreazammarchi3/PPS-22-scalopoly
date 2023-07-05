package PPS.scalopoly.model

import PPS.scalopoly.deserialization.{
  BuildableSpaceJsonReader,
  CompanySpaceJsonReader,
  NotPurchasableSpaceJsonReader,
  SpacesJsonReader,
  StationSpaceJsonReader
}
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import PPS.scalopoly.model.space.purchasable.{BuildableSpace, CompanySpace, PurchasableSpace, StationSpace}
import PPS.scalopoly.model.space.{Space, SpaceImpl}
import PPS.scalopoly.utils.JsonUtils
import PPS.scalopoly.utils.resources.JsonResources

/** Represents the game board.
  * @param spaces
  *   the list of spaces.
  * @param _buildableSpaces
  *   the list of buildable spaces.
  * @param _companySpaces
  *   the list of company spaces.
  * @param _stationSpaces
  *   the list of station spaces.
  * @param _notPurchasableSpace
  *   the list of not purchasable spaces.
  */
class GameBoard(
    spaces: List[Space],
    _buildableSpaces: List[BuildableSpace],
    _companySpaces: List[CompanySpace],
    _stationSpaces: List[StationSpace],
    _notPurchasableSpace: List[NotPurchasableSpace]
):

  /** Returns the list of [[CompanySpace]].
    * @return
    *   the list of [[CompanySpace]]
    */
  def companySpaces: List[CompanySpace] = _companySpaces

  /** Returns the list of [[StationSpace]].
    * @return
    *   the list of [[StationSpace]]
    */
  def stationSpaces: List[StationSpace] = _stationSpaces

  /** Returns the list of [[BuildableSpace]].
    * @return
    *   the list of [[BuildableSpace]]
    */
  def buildableSpaces: List[BuildableSpace] = _buildableSpaces

  /** Returns the list of [[NotPurchasableSpace]].
    * @return
    *   the list of [[NotPurchasableSpace]]
    */
  def notPurchasableSpace: List[NotPurchasableSpace] = _notPurchasableSpace

  /** Returns the list of [[PurchasableSpace]].
    * @return
    *   the list of [[PurchasableSpace]]
    */
  def purchasableSpaces: List[PurchasableSpace] =
    _companySpaces ++ _stationSpaces ++ _buildableSpaces

  /** Creates a new [[GameBoard]] with the updated [[BuildableSpace]].
    * @param buildableSpace
    *   the updated [[BuildableSpace]].
    * @return
    *   the new [[GameBoard]].
    */
  def updateBuildableSpace(buildableSpace: BuildableSpace): GameBoard =
    val space = spaces.find(_.name == buildableSpace.name)
    val build = buildableSpaces.find(_.name == buildableSpace.name)
    (space, build) match
      case (Some(space), Some(build)) =>
        val (spaceIndex, buildIndex) = (spaces.indexOf(space), buildableSpaces.indexOf(build))
        GameBoard(
          spaces.updated(spaceIndex, buildableSpace),
          buildableSpaces.updated(buildIndex, buildableSpace),
          _companySpaces,
          _stationSpaces,
          _notPurchasableSpace
        )
      case _ => this

  /** Returns the game board map.
    * @return
    *   the game board map.
    */
  def gameBoardList: List[Space] = spaces

  /** Returns the size of the game board.
    * @return
    *   the size of the game board.
    */
  def size: Int = spaces.length

/** The game board is a map of the game spaces represented by a list of [[SpaceName]].
  */
object GameBoard:
  /** Creates a new [[GameBoard]].
    * @return
    *   the new [[GameBoard]].
    */
  def apply(): GameBoard = new GameBoard(
    JsonUtils.readTypeSpaces[Space](JsonResources.SPACES, SpacesJsonReader),
    JsonUtils.readTypeSpaces[BuildableSpace](JsonResources.BUILDABLE_SPACES, BuildableSpaceJsonReader),
    JsonUtils.readTypeSpaces[CompanySpace](JsonResources.COMPANY_SPACES, CompanySpaceJsonReader),
    JsonUtils.readTypeSpaces[StationSpace](JsonResources.STATION_SPACES, StationSpaceJsonReader),
    JsonUtils.readTypeSpaces[NotPurchasableSpace](JsonResources.NOT_PURCHASABLE_SPACES, NotPurchasableSpaceJsonReader)
  )

  /** Creates a new [[GameBoard]].
    * @param spaces
    *   the list of spaces.
    * @param buildableSpaces
    *   the list of buildable spaces.
    * @param companySpaces
    *   the list of company spaces.
    * @param stationSpaces
    *   the list of station spaces.
    * @param notPurchasableSpace
    *   the list of not purchasable spaces.
    * @return
    *   the new [[GameBoard]].
    */
  def apply(
      spaces: List[Space],
      buildableSpaces: List[BuildableSpace],
      companySpaces: List[CompanySpace],
      stationSpaces: List[StationSpace],
      notPurchasableSpace: List[NotPurchasableSpace]
  ): GameBoard =
    new GameBoard(spaces, buildableSpaces, companySpaces, stationSpaces, notPurchasableSpace)
