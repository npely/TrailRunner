package src.main.TrailRunnerModule

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import controller.controllerComponent.ControllerInterface
import model.fieldComponent.FieldInterface
import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import model.levelComponent.levelBaseImpl.{Level, Level1, Level2, Level3}
import model.playerComponent.PlayerInterface
import net.codingwell.scalaguice.ScalaModule
import model.fileIOComponent.fileIoJsonImpl.FileIO

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

    bind[FileIOInterface].to[model.fileIOComponent.fileIoJsonImpl.FileIO]
  }

}
