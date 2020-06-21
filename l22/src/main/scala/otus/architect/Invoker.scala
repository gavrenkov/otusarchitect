package otus.architect

class Invoker {
  def execute(command: Command): Unit = {
    Logger.log(s"Execute ${command.toString}")
    command.execute()
  }
}
