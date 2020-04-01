package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class MyStringArrayTest extends AnyFunSuite {
  test("string to char array") {
    val s = new MyStringArray("abc")
    assert(s.toCharArray === Array('a', 'b', 'c'))
  }

  test("substring to char array") {
    val s = new MyStringArray("abc")
    assert(s.toCharArray(1, 2) === Array('b'))
  }
}
