package main.scala.model

import spray.json.JsObject

trait Model {
  def toJson(): JsObject
}
