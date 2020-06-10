package otus.architect

abstract class Token

case class Command(name: String) extends Token {
  override def toString: String = s"Command:  \t$name"
}

case class Key(name: String) extends Token {
  override def toString: String = s"Key:      \t$name"
}

case class Parameter(name: String) extends Token {
  override def toString: String = s"Parameter:\t$name"
}

case class Error(name: String) extends Token {
  override def toString: String = s"Error:    \t$name"
}
