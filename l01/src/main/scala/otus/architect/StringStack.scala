package otus.architect

class StringStack (val initialSize: Int) {
  private var values: Array[MyString] = new Array[MyString](initialSize)
  private var size = 0

  def this() {
    this(0)
  }

  def push(e: MyString): Unit = {
    if (size >= initialSize) {
      val a = new Array[MyString](size + 1)
      values.copyToArray(a)
      a(size) = e
      values = a
    } else {
      values(size) = e
    }
    size = size + 1
  }

  def pop(): MyString = {
    if (size > 0) {
      size = size - 1
      values(size)
    } else {
      throw new IndexOutOfBoundsException("Stack is empty")
    }
  }

  def peek(): MyString = {
    values(size - 1)
  }

  def isEmpty: Int = {
    if (size > 0) 0
    else 1
  }

  def getValues: Array[MyString] = {
    values.slice(0, size)
  }

  def getSize: Int = size
}
