package otus.architect

class StateContext {
  private var state: State = new CommandState()

  def getState: State = state

  def setState(state: State): Unit = {
    this.state = state
  }

  def parse(input: List[String]): List[Token] =
    state.parse(input, this)
}
