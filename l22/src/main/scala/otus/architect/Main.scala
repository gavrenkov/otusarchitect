package otus.architect

object Main extends App {
  val invoker: Invoker = new Invoker()
  invoker.invoke(new RunCommand(args))
}
