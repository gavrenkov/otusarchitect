package otus.architect

import java.io.FileWriter

// Объект-одиночка для записи журнала работы приложения
object Logger {
  def log(filename: String, message: String): Unit = {
    val fw = new FileWriter(filename, true)
    try {
      fw.write(s"${new java.util.Date().toString}\t$message\n")
    }
    finally fw.close()
  }
}