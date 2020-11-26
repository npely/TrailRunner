package model.fileIOComponent.fileIO_XML_Impl

import java.io.{File, PrintWriter}

import com.google.inject.Guice
import com.google.inject.name.Names
import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json.{JsArray, JsNumber, JsObject, Json}
import src.main.TrailRunnerModule.TrailRunnerModule

import scala.io.Source
import scala.xml.{Elem, PrettyPrinter, XML}

class FileIO extends FileIOInterface{

  override def load: LevelInterface = {
    val injector = Guice.createInjector(new TrailRunnerModule)
    val xmlFile = scala.xml.XML.loadFile("level.xml")
    val name = (xmlFile \\ "Level" \ "Name").text
    var level: LevelInterface = null
    name match {
      case "Level1" => level = injector.instance[LevelInterface](Names.named("Level1"))
      case "Level2" => level = injector.instance[LevelInterface](Names.named("Level2"))
      case "Level3" => level = injector.instance[LevelInterface](Names.named("Level3"))
    }
    val size = (xmlFile \\ "Level" \ "Size").text.toInt

    var x = 0
    var y = 0
    for (i <- 0 to (size * size - 1)) {
      val fieldValue = (xmlFile \\ "Fields" \ "FieldValue")(i).text.toInt
      level.dungeon(x)(y).setValue(fieldValue)
      y += 1
      if (y % 10 == 0){
        x += 1
        y = 0
      }
    }

    val xPos = (xmlFile \\ "Level" \ "XPos").text.toInt
    val yPos = (xmlFile \\ "Level" \ "YPos").text.toInt
    level.player.xPos = xPos
    level.player.yPos = yPos
    level
  }

  override def save(level: LevelInterface): Unit = {
    val pw = new PrintWriter(new File("level.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(levelToXML(level))
    pw.write(xml)
    pw.close
  }

  def levelToXML(level: LevelInterface) = {
    <Level>
      <Name>{level.getName}</Name>
      <Size>{level.dungeon.length}</Size>
      <XPos>{level.player.xPos}</XPos>
      <YPos>{level.player.yPos}</YPos>
        {allFieldsToXml(level)}
    </Level>
  }

  def allFieldsToXml(level: LevelInterface): Elem = {
    var fieldString = "<Fields>"
    for (i <- 0 until level.rows; j <- 0 until level.columns) {
      fieldString = fieldString + "<FieldValue>"+ level.dungeon(i)(j).value +"</FieldValue>"
    }
    fieldString += "</Fields>"
    XML.loadString(fieldString)
  }

  override def levelToJson(level: LevelInterface) = {
    val levelObj = Json.obj(
      "name" -> level.getName,
      "size" -> JsNumber(level.dungeon.length),
      "xPos" -> JsNumber(level.player.xPos),
      "yPos" -> JsNumber(level.player.yPos),
    )

    var fields = new JsArray()
    for (i <- 0 to level.dungeon.length - 1) {
      for (j <- 0 to level.dungeon.length - 1) {
        fields = fields.append(Json.obj(
          "fieldvalue" -> level.dungeon(i)(j).value,
          "fieldtype" -> level.dungeon(i)(j).fieldType
        ))
      }
    }

    Json.obj(
      "level" -> levelObj,
      "fieldvalues" -> fields
    )
  }
}