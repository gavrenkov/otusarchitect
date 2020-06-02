package otus.architect

object Main {
  def main(args: Array[String]): Unit = {
    // Разбираем параметры запуска
    if (args.length != 3) {
      println(s"Usage: inputFile1 inputFile2 outputFile")
      println("where:")
      println("  inputFile1 - file with the first matrix data")
      println("  inputFile2 - file with the second matrix data")
      println("  outputFile - file with the sum of first and second matrices")
      sys.exit(-1)
    }

    // Читаем, складываем и сохраняем
    try {
      P1().addAndSave(args(0), args(1), args(2))
    } catch {
      case e: Throwable => println("ERROR: " + e.toString)
        sys.exit(-1)
    }
  }
}