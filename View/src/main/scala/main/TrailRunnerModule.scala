package main

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import controllerComponent.ControllerInterface
import model.fieldComponent.FieldInterface
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import net.codingwell.scalaguice.ScalaModule

class TrailRunnerModule extends AbstractModule with ScalaModule {

  override def configure() = {
    bindConstant().annotatedWith(Names.named("Zero")).to(0)
    bindConstant().annotatedWith(Names.named("Ten")).to(10)
    bindConstant().annotatedWith(Names.named("Ground")).to("")
    bindConstant().annotatedWith(Names.named("False")).to(false)
    bindConstant().annotatedWith(Names.named("Level")).to("Level")
    bind[PlayerInterface].to[model.playerComponent.playerBaseImpl.Player]
    bind[ControllerInterface].to[controllerComponent.controllerBaseImpl.Controller]
    bind[FieldInterface].to[model.fieldComponent.fieldBaseImpl.Field]
    bind[LevelInterface].to[model.levelComponent.levelBaseImpl.Level]
  }

}
