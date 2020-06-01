package otus.architect

object MatrixMultiplication {
  def main(args: Array[String]): Unit = {

    // Разбираем параметры запуска
    if (args.length != 4) {
      println(s"Usage: -p P file1 file2")
      println("where:")
      println("  -p P  - number of threads")
      println("  file1 - file with the first matrix data")
      println("  file2 - file with the second matrix data")
      sys.exit(-1)
    }

    // Заполняем исходные матрицы данными из файлов
    val matrixA: Matrix =
      try {
      Matrix(args(2))
    } catch {
      case e: Throwable => exitError(e.toString)
        new Matrix()
    }

    println(s"Matrix A: ${matrixA.rowsNumber} x ${matrixA.colsNumber}")
    matrixA.print()

    val matrixB: Matrix =
      try {
        Matrix(args(3))
      } catch {
        case e: Throwable => exitError(e.toString)
          new Matrix()
      }

    println(s"\nMatrix B: ${matrixB.rowsNumber} x ${matrixB.colsNumber}")
    matrixB.print()

    // Количество потоков не может быть больше кол-ва ячеек
    val p: Int = math.min(matrixA.rowsNumber * matrixB.colsNumber, parseParameter(args(1)))

    // Перемножаем матрицы
    val matrixC: Matrix = try {
      matrixA.multiply(matrixB, p)
    } catch {
      case e: Throwable => exitError(e.toString)
        new Matrix()
    }

    // Выводим результат перемножения матриц
    println("\nA x B")
    matrixC.print()
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
