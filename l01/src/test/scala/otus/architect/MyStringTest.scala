package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class MyStringTest extends AnyFunSuite {
  test("no parameter constructor, check value") {
    val s = new MyString()
    assert(s.getValue === "")
  }

  test("no parameter constructor, check size") {
    val s = new MyString()
    assert(s.getSize === 0)
  }

  test("string constructor, check value") {
    val testString: String = "abc"
    val s = new MyString(testString)
    assert(s.getValue === testString)
  }

  test("string constructor, check size") {
    val testString: String = "abc"
    val s = new MyString(testString)
    assert(s.getSize === testString.length)
  }

  test("char constructor, check value") {
    val s = new MyString('a')
    assert(s.getValue === "a")
  }

  test("char constructor, check size") {
    val s = new MyString('a')
    assert(s.getSize === 1)
  }
}
