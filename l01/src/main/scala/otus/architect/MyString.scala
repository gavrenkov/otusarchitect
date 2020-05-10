package otus.architect

class MyString () {
  var value: String = ""
  var size: Long = 0

  def this(str: String) {
    this()
    value = str
    size = str.length
  }

  def this(c: Char) {
    this()
    value = c.toString
    size = 1
  }

  def getValue: String = value
  def getSize: Long = size
  def empty(): Unit = {
    value = ""
    size = 0
  }
}
