package PPS.scalopoly.deserialization

import PPS.scalopoly.engine.prolog.PrologEngine
import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.purchasable.CompanySpace
import PPS.scalopoly.utils.JsonUtils
import com.google.gson.stream.JsonReader

import java.io.Reader
import scala.collection.mutable.ListBuffer

/** JsonReader for [[PPS.scalopoly.model.space.purchasable.CompanySpace]] objects. It reads a json file and returns a
  * list of [[PPS.scalopoly.model.space.purchasable.CompanySpace]].
  */
object CompanySpaceJsonReader extends MyJsonReader[CompanySpace]:
  override def read(reader: JsonReader): CompanySpace =
    var name = ""
    var sellingPrice = 0
    var baseRent = 0
    var spaceGroup = SpaceGroup.ARANCIONE

    reader.beginObject()
    while (reader.hasNext)
      val currentName = reader.nextName()
      currentName match
        case "name"         => name = reader.nextString()
        case "sellingPrice" => sellingPrice = reader.nextInt()
        case "baseRent"     => baseRent = reader.nextInt()
        case "spaceGroup" =>
          spaceGroup = SpaceGroup.valueOf(reader.nextString())
        case _ => reader.skipValue()
    reader.endObject()
    CompanySpace(name, sellingPrice, PrologEngine.calculateRents(baseRent, 0, 0), spaceGroup)
