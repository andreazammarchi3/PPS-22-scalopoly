package PPS.scalopoly.deserialization

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.notPurchasable.{
  NotPurchasableSpace,
  NotPurchasableSpaceBuilder,
  NotPurchasableSpaceType
}
import PPS.scalopoly.utils.JsonUtils
import com.google.gson.stream.JsonReader

import java.io.Reader
import scala.collection.mutable.ListBuffer

/** Json reader for [[PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace]] objects. It reads a json file and
  * returns a list of [[PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace]].
  */
object NotPurchasableSpaceJsonReader extends MyJsonReader[NotPurchasableSpace]:
  override def read(reader: JsonReader): NotPurchasableSpace =
    var name = ""
    var spaceType = ""
    var spaceValue = 0

    reader.beginObject()
    while (reader.hasNext)
      val currentName = reader.nextName()
      currentName match
        case "name"       => name = reader.nextString()
        case "spaceType"  => spaceType = reader.nextString()
        case "spaceValue" => spaceValue = reader.nextInt()
        case _            => reader.skipValue()
    reader.endObject()

    spaceType match
      case "Chance" => NotPurchasableSpaceBuilder(name, NotPurchasableSpaceType.CHANCE, spaceValue).build()
      case "CommunityChest" =>
        NotPurchasableSpaceBuilder(name, NotPurchasableSpaceType.COMMUNITY_CHEST, spaceValue).build()
      case "IncomeTax" => NotPurchasableSpaceBuilder(name, NotPurchasableSpaceType.INCOME_TAX, spaceValue).build()
      case "LuxuryTax" => NotPurchasableSpaceBuilder(name, NotPurchasableSpaceType.LUXURY_TAX, spaceValue).build()
      case _           => NotPurchasableSpaceBuilder(name, NotPurchasableSpaceType.BLANK, spaceValue).build()
