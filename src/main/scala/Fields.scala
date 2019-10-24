case class Field(column : Int, row: Int, value : Int) {
  def isBroken:Boolean = value == 0
}

val field1 = Field(1, 1, 1)
val field2 = Field(1, 1, 2)
val field3 = Field(0, 1, 3)

field3.isBroken

