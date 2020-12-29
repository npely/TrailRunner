package model.levelComponent.levelBaseImpl

import javax.inject.Inject
import model.fieldComponent.FieldInterface
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.PlayerFactory

class CustomLevel @Inject() extends Level {
  override var name: String = "CustomLevel"
  override var playerName: String = _
  override var startX: Int = _
  override var startY: Int = _
  override var winX: Int = _
  override var winY: Int = _
  override var doorX: Int = _
  override var doorY: Int = _

  override val player: PlayerInterface = PlayerFactory.createPlayer1()
  override val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

  fillNullValues()
}
