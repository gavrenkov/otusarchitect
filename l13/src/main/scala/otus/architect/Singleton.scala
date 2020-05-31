package otus.architect

import java.io.FileWriter

// On Demand Holder
class Singleton {
  private object SingletonHolder {
    val instance: Singleton = new Singleton
  }

  def getInstance: Singleton = SingletonHolder.instance

  private val filename: String = "MatrixMultiplication.log"

  def log(message: String): Unit = {
    val fw = new FileWriter(filename, true)
    try {
      fw.write(s"${java.time.LocalTime.now()}\t$message\n")
    }
    finally fw.close()
  }
}
