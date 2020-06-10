package otus.architect

class QuoteState extends State {
  override var name: String = ""

  override def parse(input: List[String], context: StateContext): List[Token] = {
    input match {
      case h :: t =>
        if (!isValid(h) || h.slice(0, h.length - 2).contains('"'))
          List(Error(s"$h - Incorrect Parameter"))
        else if (h(h.length - 1) == '"') {
          if (h.length == 1)
            name = name + "\""
          else
            name = name + " " + h
          context.setState(new ParameterState)
          List(Parameter(name)) ++ context.getState.parse(t, context)
        } else {
          if (name.equals("\""))
            name = name + h
          else
            name = name + " " + h
          parse(t, context)
        }
      case _ => List(Error(s"$name - Incorrect Parameter"))
    }
  }
}
