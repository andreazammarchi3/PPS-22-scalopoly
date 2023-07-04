package PPS.scalopoly.deserialization

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.BuildableSpace
import PPS.scalopoly.utils.JsonUtils
import com.google.gson.stream.JsonReader

import java.io.Reader
import scala.collection.mutable.ListBuffer

object BuildableSpaceJsonReader:
  def read(in: Reader): List[BuildableSpace] = readBuildableSpaces(
    new JsonReader(in)
  )

  private def readBuildableSpaces(reader: JsonReader): List[BuildableSpace] =
    val buildableSpaces = new ListBuffer[BuildableSpace]
    reader.beginArray()
    while (reader.hasNext)
      buildableSpaces += readBuildableSpace(reader)
    reader.endArray()
    buildableSpaces.toList

  private def readBuildableSpace(reader: JsonReader): BuildableSpace =
    var name = ""
    var sellingPrice = 0
    var rents = List.empty[Int]
    var spaceGroup = SpaceGroup.ARANCIONE
    var numHouse = 0
    var buildingCost = 0

    reader.beginObject()
    while (reader.hasNext)
      val currentName = reader.nextName()
      currentName match
        case "name"         => name = reader.nextString()
        case "sellingPrice" => sellingPrice = reader.nextInt()
        case "rents"        => rents = JsonUtils.readRents(reader)
        case "spaceGroup" =>
          spaceGroup = SpaceGroup.valueOf(reader.nextString())
        case "numHouse"     => numHouse = reader.nextInt()
        case "buildingCost" => buildingCost = reader.nextInt()
        case _              => reader.skipValue()
    reader.endObject()
    BuildableSpace(
      name,
      sellingPrice,
      rents,
      spaceGroup,
      numHouse,
      buildingCost
    )
