package main

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import controller.controllerComponent.ControllerInterface
import model.fieldComponent.FieldInterface
import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import model.levelComponent.levelBaseImpl._
import net.codingwell.scalaguice.ScalaModule

class TrailRunnerModule extends AbstractModule with ScalaModule {

  val zero = 0

  override def configure() = {
    bindConstant().annotatedWith(Names.named("Zero")).to(zero)
    bind[ControllerInterface].to[controller.controllerComponent.controllerBaseImpl.Controller]
    bind[FieldInterface].to[model.fieldComponent.fieldBaseImpl.Field]
    //bind[LevelInterface].to[Level]
    bind[LevelInterface].annotatedWithName("Level1").toInstance(new Level1)
    bind[LevelInterface].annotatedWithName("Level2").toInstance(new Level2)
    bind[LevelInterface].annotatedWithName("Level3").toInstance(new Level3)
    bind[LevelInterface].annotatedWithName("Level4").toInstance(new Level4)
    bind[LevelInterface].annotatedWithName("Level5").toInstance(new Level5)
    bind[LevelInterface].annotatedWithName("CustomLevel").toInstance(new CustomLevel)

    //Use XML:
    //bind[FileIOInterface].to[model.fileIOComponent.fileIO_XML_Impl.FileIO]
    //Use Json:
    bind[FileIOInterface].to[model.fileIOComponent.fileIO_Json_Impl.FileIO]
  }

}
