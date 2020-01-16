package model.fileIOComponent.fileIO_XML_Impl

import java.io.PrintWriter

import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import java.io.File

import scala.xml.{Elem, PrettyPrinter, XML}


class FileIO extends FileIOInterface{

  override def load: LevelInterface = {
    val xmlFile = scala.xml.XML.loadFile("TrailRunner.xml")
    ???
  }

  override def save(level: LevelInterface): Unit = {
    val pw = new PrintWriter(new File("TrailRunner.xml"))
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
      <Fields></Fields>
    </Level>
  }

  def fieldToXmlString(level: LevelInterface): String = {
//    "<Field><name>" + name + "</name> " +
//
//    "<stationNumber>" + stationNumber + "</stationNumber>" + "<Field>"""
    ""
  }

  def allFieldsToXml(level: LevelInterface): Elem = {
    var fieldString = ""
    for (i <- 0 until level.rows; j <- 0 until level.columns) {
      fieldString = fieldString + fieldToXmlString(level)
    }
    fieldString = "<detectives>" + fieldString + "</detectives>"
    XML.loadString(fieldString)
  }
}