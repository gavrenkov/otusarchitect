package otus.architect

class MyStringArray() extends MyString() {
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

  def toCharArray: Array[Char] = value.toCharArray
  def toCharArray(from: Int, until: Int): Array[Char] = value.slice(from, until).toCharArray
}
