package otus.architect

class KeyState extends State {
  override var name: String = _

  override def parse(input: List[String], context: StateContext): List[Token] = {
    input match {
      case h :: t =>
        if (isValid(h) && h(0) != '-') List(Error(s"$h - Should be a Key, not Parameter"))
        else if (!isKey(h)) List(Error(s"$h - Incorrect Key"))
        else {
          keys.getOrElse(h, keyType.MayHave) match {
            case keyType.NeedParameter =>
              context.setState(new ParameterState)
              context.getState.setName("parameter")
              List(Key(h)) ++ context.getState.parse(t, context)
            case keyType.MayHave =>
              context.setState(new ParameterState)
              List(Key(h)) ++ context.getState.parse(t, context)
            case keyType.NoParameter =>
              List(Key(h)) ++ parse(t, context)
          }
        }
      case _ => Nil
    }
  }
}
