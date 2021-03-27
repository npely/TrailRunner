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

  override def configure() = {
    bindConstant().annotatedWith(Names.named("Zero")).to(0)
    bind[ControllerInterface].to[controller.controllerComponent.controllerBaseImpl.Controller]
    bind[FieldInterface].to[model.fieldComponent.fieldBaseImpl.Field]
    bind[LevelInterface].to[model.levelComponent.levelBaseImpl.Level]
    bind[FileIOInterface].to[model.fileIOComponent.fileIO_Json_Impl.FileIO]
  }

}
