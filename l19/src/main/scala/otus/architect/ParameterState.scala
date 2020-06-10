package otus.architect

class ParameterState extends State {
  override var name: String = ""

  override def parse(input: List[String], context: StateContext): List[Token] = {
    input match {
      case h :: t =>
        if (!isValid(h) || h.slice(1, h.length - 2).contains('"'))
          List(Error(s"$h - Incorrect Parameter"))
        else if (h(0) == '-') {
          if (name.equals("parameter"))
            List(Error(s"$h - Should be a Parameter, not a Key"))
          else {
            context.setState(new KeyState)
            context.getState.parse(input, context)
          }
        } else if (h(0) == '"') {
          context.setState(new QuoteState)
          context.getState.setName(h)
          context.getState.parse(t, context)
        } else {
          name = ""
          List(Parameter(h)) ++ parse(t, context)
        }
      case _ =>
        if (name.equals("parameter"))
          List(Error("Missing a Parameter after Key"))
        else
          Nil
    }
  }
}
