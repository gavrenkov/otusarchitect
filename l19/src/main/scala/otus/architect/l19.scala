package otus.architect

import scala.io.StdIn.readLine

object l19 extends App {
  // Read Input, replace '_' to space, collapse spaces, split by space
  val input: List[String] = readLine()
    .trim
    .replaceAll("_", " ")
    .replaceAll(" +", " ")
    .split(" ")
    .toList
    .filter(!_.isEmpty)

  // Create Sate Context
  val context: StateContext = new StateContext()

  // Parse input
  val result: List[Token] = context.parse(input)

  // Output result of parsing
  if (result.exists {
    _ match {
      case Error(_) => true
      case _ => false
    }
  }) println("Input is not correct\n")
  else
    println("Input is correct\n")

  result.foreach { r => println(r.toString) }
}
