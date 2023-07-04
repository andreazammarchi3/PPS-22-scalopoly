package PPS.scalopoly.deserialization

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.notPurchasable.{ChanceSpace, CommunityChestSpace, IncomeTaxSpace, LuxuryTaxSpace, NotPurchasableSpace}
import PPS.scalopoly.utils.JsonUtils
import com.google.gson.stream.JsonReader

import java.io.Reader
import scala.collection.mutable.ListBuffer

object NotPurchasableSpaceJsonReader:
  def read(in: Reader): List[NotPurchasableSpace] = readNotBuildableSpaces(
    new JsonReader(in)
  )

  private def readNotBuildableSpaces(reader: JsonReader): List[NotPurchasableSpace] =
    val notBuildableSpaces = new ListBuffer[NotPurchasableSpace]
    reader.beginArray()
    while (reader.hasNext)
      val space = readNotBuildableSpace(reader)
      if (space.nonEmpty) notBuildableSpaces += space.get
    reader.endArray()
    notBuildableSpaces.toList

  private def readNotBuildableSpace(reader: JsonReader): Option[NotPurchasableSpace] =
    var name = ""
    var spaceType = ""
    var spaceValue = 0

    reader.beginObject()
    while (reader.hasNext)
      val currentName = reader.nextName()
      currentName match
        case "name"         => name = reader.nextString()
        case "spaceType" => spaceType = reader.nextString()
        case "spaceValue" => spaceValue = reader.nextInt()
        case _              => reader.skipValue()
    reader.endObject()

    spaceType match
      case "Chance" => Some(ChanceSpace(name, spaceValue))
      case "CommunityChest" => Some(CommunityChestSpace(name, spaceValue))
      case "IncomeTax" => Some(IncomeTaxSpace(name, spaceValue))
      case "LuxuryTax" => Some(LuxuryTaxSpace(name, spaceValue))
      case _ => None

