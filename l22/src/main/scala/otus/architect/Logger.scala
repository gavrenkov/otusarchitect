package otus.architect

import java.io.FileWriter

object Logger {
  private val filename: String = "Command.log"

  def log(message: String): Unit = {
    val fw = new FileWriter(filename, true)
    try {
      fw.write(s"${java.time.LocalTime.now()}\t$message\n")
    }
    finally fw.close()
  }
}