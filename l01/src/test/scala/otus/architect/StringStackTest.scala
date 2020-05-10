package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class StringStackTest extends AnyFunSuite {
  test("create empty stack") {
    val s = new StringStack()
    assert(s.isEmpty === 1)
  }

  test("create fixed size stack, check is empty") {
    val s = new StringStack(5)
    assert(s.isEmpty === 1)
  }

  test("create fixed size stack, check size") {
    val s = new StringStack(5)
    assert(s.getSize === 0)
  }

  test("check push to stack") {
    val s = new StringStack(5)
    s.push(new MyString("abc"))
    assert(s.getSize === 1)
  }

  test("check push to full stack") {
    val s = new StringStack(5)
    s.push(new MyString("s1"))
    s.push(new MyString("s2"))
    s.push(new MyString("s3"))
    s.push(new MyString("s4"))
    s.push(new MyString("s5"))
    s.push(new MyString("s6"))
    assert(s.getSize === 6)
  }

  test("check pop from stack") {
    val str: String = "abc"
    val s = new StringStack(5)
    s.push(new MyString(str))
    s.pop()
    assert(s.isEmpty === 1)
  }

  test("check pop from full stack") {
    val s = new StringStack(5)
    s.push(new MyString("s1"))
    s.push(new MyString("s2"))
    s.push(new MyString("s3"))
    s.push(new MyString("s4"))
    s.push(new MyString("s5"))
    s.pop()
    assert(s.getSize === 4)
  }

  test("check peek from stack") {
    val str: String = "abc"
    val s = new StringStack(5)
    s.push(new MyString(str))
    assert(s.peek().getValue === str)
  }

  test("get elements from stack") {
    val s = new StringStack(5)
    s.push(new MyString("s1"))
    s.push(new MyString("s2"))
    s.push(new MyString("s3"))
    s.push(new MyString("s4"))
    s.push(new MyString("s5"))
    assert(s.getValues.map(_.value) === Array("s1", "s2", "s3", "s4", "s5"))
  }

  test("check push MyString and MyStringArray") {
    val s = new StringStack(5)
    s.push(new MyString("s1"))
    s.push(new MyStringArray("sa2"))
    s.push(new MyString("s3"))
    s.push(new MyStringArray("sa4"))
    s.push(new MyString("s5"))
    s.push(new MyStringArray("sa6"))
    assert(s.getValues.map(_.value) === Array("s1", "sa2", "s3", "sa4", "s5", "sa6"))
  }
}
