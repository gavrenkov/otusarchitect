package otus.architect

class Invoker {
  def invoke(command: Command): Unit = {
    Logger.log(s"Execute ${command.toString}")
    command.execute()
  }
}
