package otus.architect

trait State {
  // List of known keys
  val keys: Map[String, keyType.Value] = Map("-a" -> keyType.NeedParameter,
    "-a" -> keyType.NeedParameter,
    "-r" -> keyType.NeedParameter,
    "-p" -> keyType.NeedParameter,
    "-n" -> keyType.MayHave,
    "-m" -> keyType.MayHave,
    "-l" -> keyType.MayHave,
    "-s" -> keyType.MayHave,
    "-c" -> keyType.NoParameter
  )

  var name: String

  def getName: String = name

  def setName(name: String): Unit = {
    this.name = name
  }

  def isCommand(name: String): Boolean = name.matches("[a-zA-Z]+")

  def isValid(name: String): Boolean = name.matches("[a-zA-Z0-9-\"]+")

  def isKey(name: String): Boolean = keys.keySet.contains(name)

  def parse(input: List[String], context: StateContext): List[Token]

  object keyType extends Enumeration {
    val NeedParameter, MayHave, NoParameter = Value
  }

}
