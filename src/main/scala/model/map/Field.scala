case class Field(column : Int, row: Int, value : Int) {
  def isBroken:Boolean = value == 0
  def isSet:Boolean = column != 0 && row != 0
}


