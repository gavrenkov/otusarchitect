package otus.architect

class CommandState extends State {
  override var name: String = _

  override def parse(input: List[String], context: StateContext): List[Token] = {
    input match {
      case h :: t =>
        if (isCommand(h)) {
          context.setState(new ParameterState)
          List(Command(h)) ++ context.getState.parse(t, context)
        } else List(Error(s"$h - Incorrect Command"))
      case _ => List(Error("No Command"))
    }
  }
}
