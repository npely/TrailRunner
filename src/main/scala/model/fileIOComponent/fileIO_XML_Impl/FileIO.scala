package model.fileIOComponent.fileIO_XML_Impl

import java.io.PrintWriter

import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import java.io.File

import scala.xml.PrettyPrinter


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
}