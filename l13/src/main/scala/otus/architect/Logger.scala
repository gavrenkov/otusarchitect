package otus.architect

import java.io.FileWriter

// Объект-одиночка для записи журнала работы приложения
object Logger {
  private val filename: String = "MatrixMultiplication.log"

  def log(message: String): Unit = {
    val fw = new FileWriter(filename, true)
    try {
      fw.write(s"${java.time.LocalTime.now()}\t$message\n")
    }
    finally fw.close()
  }
}
