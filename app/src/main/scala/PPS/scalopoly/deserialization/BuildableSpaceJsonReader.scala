package PPS.scalopoly.deserialization

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.BuildableSpace
import PPS.scalopoly.utils.JsonUtils
import com.google.gson.stream.JsonReader

import java.io.Reader
import scala.collection.mutable.ListBuffer

/** Json reader for [[PPS.scalopoly.model.space.purchasable.BuildableSpace]]. It reads a json file and returns a list of
  * [[PPS.scalopoly.model.space.purchasable.BuildableSpace]].
  */
object BuildableSpaceJsonReader extends MyJsonReader[BuildableSpace]:
  override def read(reader: JsonReader): BuildableSpace =
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
        case "spaceGroup"   => spaceGroup = SpaceGroup.valueOf(reader.nextString())
        case "numHouse"     => numHouse = reader.nextInt()
        case "buildingCost" => buildingCost = reader.nextInt()
        case _              => reader.skipValue()
    reader.endObject()
    BuildableSpace(name, sellingPrice, rents, spaceGroup, numHouse, buildingCost)
