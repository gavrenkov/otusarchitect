package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class ParserTest extends AnyFunSuite {
  test("Empty input") {
    val input: List[String] = List()
    val context: StateContext = new StateContext()
    val result: List[Token] = context.parse(input)

    assert(result === List(Error("No Command")))
  }

  test("Only Command") {
    val input: List[String] = List("cmd")
    val context: StateContext = new StateContext()
    val result: List[Token] = context.parse(input)

    assert(result === List(Command("cmd")))
  }

  test("Command and one parameter") {
    val input: List[String] = List("cmd", "par")
    val context: StateContext = new StateContext()
    val result: List[Token] = context.parse(input)

    assert(result === List(Command("cmd"), Parameter("par")))
  }

  test("Command and one parameter with quotas") {
    val input: List[String] = List("cmd", "\"", "from", "to\"")
    val context: StateContext = new StateContext()
    val result: List[Token] = context.parse(input)

    assert(result === List(Command("cmd"), Parameter("\"from to\"")))
  }

  test("Command, one parameters and key with one parameter") {
    val input: List[String] = List("cmd", "par", "-a", "kp")
    val context: StateContext = new StateContext()
    val result: List[Token] = context.parse(input)

    assert(result === List(Command("cmd"), Parameter("par"), Key("-a"), Parameter("kp")))
  }

  test("Command and key needs parameter but doesn't have it") {
    val input: List[String] = List("cmd", "-a")
    val context: StateContext = new StateContext()
    val result: List[Token] = context.parse(input)

    assert(result === List(Command("cmd"), Key("-a"), Error("Missing a Parameter after Key")))
  }

  test("Command, one parameters and key with two parameters") {
    val input: List[String] = List("cmd", "par", "-n", "n1", "n2")
    val context: StateContext = new StateContext()
    val result: List[Token] = context.parse(input)

    assert(result === List(Command("cmd"), Parameter("par"), Key("-n"), Parameter("n1"), Parameter("n2")))
  }

  test("Command and two keys") {
    val input: List[String] = List("cmd", "-c", "-m")
    val context: StateContext = new StateContext()
    val result: List[Token] = context.parse(input)

    assert(result === List(Command("cmd"), Key("-c"), Key("-m")))
  }

}
