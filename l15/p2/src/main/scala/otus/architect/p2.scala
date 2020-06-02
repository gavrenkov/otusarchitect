package otus.architect

import java.io.File

object p2 {
  def main(args: Array[String]): Unit = {
    val p1 = new p1Proxy()
    val logFilename: String = "p2.log"

    // Разбираем параметры запуска
    if (args.length != 5) {
      println(s"Usage: rows cols outputFile1 outputFile2 outputFile3")
      println("where:")
      println("  rows - number of rows")
      println("  cols - number of cols")
      println("  outputFile1 - file with data for the first matrix")
      println("  outputFile2 - file with data for the second matrix")
      println("  outputFile3 - file with sum of the first and second matrix")
      sys.exit(-1)
    }

    val rowsNumber: Int = parseParameter(args(0))
    val colsNumber: Int = parseParameter(args(1))

    // Генерируем и сохраняем матрицы
    Matrix(rowsNumber, colsNumber).generate(-100, 100).save(args(2))
    Matrix(rowsNumber, colsNumber).generate(-100, 100).save(args(3))

    // Удаляем файл журнала, если он есть
    if (new java.io.File(logFilename).exists)
      new File(logFilename).delete()

    // Устанавливаем имя файла журнала
    p1.setLogFilename(logFilename)

    // Суммируем матрицы и сохраняем результат
    p1.addAndSave(args(2), args(3), args(4))

    // Вызываем ещё несколько раз, имя выходного файла не указываем
    p1.addAndSave(args(2), args(3), "")
    p1.addAndSave(args(2), args(3), "")
  }

  // Функция разбора цифрового параметра
  def parseParameter(p: String): Int = {
    val n: Int =
      try {
        p.toInt
      } catch {
        case _: Throwable => exitError("couldn't parse numeric parameter")
          0
      }
    if (n < 0) exitError("incorrect numeric parameter")
    n
  }

  // Функция сообщает об ошибке и завершает работу
  def exitError(e: String): Unit = {
    println("ERROR: " + e)
    sys.exit(-1)
  }
}
