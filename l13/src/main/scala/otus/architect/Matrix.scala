package otus.architect

import scala.io.{BufferedSource, Source}

class Matrix {
  var matrix: Array[Array[Int]] = _
  var rowsNumber: Int = 0 // кол-во строк
  var colsNumber: Int = 0 // кол-во столбцов

  // Создаем пустую матрицу размера n x m
  def this(n: Int, m: Int) = {
    this()
    matrix = Array.ofDim[Int](n, m)
    rowsNumber = n
    colsNumber = m
  }

  // Создаём матрицу из файла
  def this(filename: String) = {
    this()
    matrix = read(filename)
    rowsNumber = matrix.length
    colsNumber = setColumnSize()
  }

  // Создаём матрицу из массива
  def this(array: Array[Array[Int]]) = {
    this()
    matrix = array
    rowsNumber = array.length
    colsNumber = setColumnSize()
  }

  // Вычисляем кол-во столбцов
  // Проверяем, что все строки имеют одинаковое кол-во элементов
  private def setColumnSize(): Int = {
    val m: Set[Int] = matrix.map(_.length).toSet
    if (m.size == 1) m.head
    else {
      throw new IllegalArgumentException("The matrix must contain the same number of columns")
    }
  }

  // Получить элемент матрицы
  def getElement(i: Int, j: Int): Int = matrix(i)(j)

  // Изменить элемент матрицы
  def setElement(i: Int, j: Int, value: Int): Unit = {
    matrix(i)(j) = value
  }

  // Перемножение матриц в p потоков
  // A(n x m) * B(m x l) = C(n x l)
  def multiply(other: Matrix, p: Int): Matrix = {
    // Проверяем, что размерности матриц позволяют их перемножить
    if (this.colsNumber != other.rowsNumber)
      throw new UnsupportedOperationException("Illegal matrices size for multiplication")

    if (p > 1) {
      Logger.log(s"Вычисляем произведение в $p потоков")
      val resultMatrix: Matrix = Matrix(this.rowsNumber, other.colsNumber)

      // Список ячеек для вычислений
      val cells: Seq[(Matrix, Matrix, Matrix, Int, Int)] =
        (0 until this.rowsNumber).flatMap { i =>
          (0 until other.colsNumber) map { j => (this, other, resultMatrix, i, j) }
        }

      // Количество ячеек на один поток
      val nByP: Int = math.ceil((this.rowsNumber * other.colsNumber) / p.toFloat).toInt

      // Подготавливаем p потоков, каждый рассчитает не более nByP ячеек
      val threads: Seq[Compute] = (0 until p) map { pn =>
        (pn, cells.slice(pn * nByP, (pn + 1) * nByP))
      } map { pc => new Compute(pc._1, pc._2) }

      // Запускаем потоки и ждём их окончания
      threads.foreach(_.start())
      threads.foreach(_.join())

      resultMatrix

    } else {
      Logger.log("Вычисляем произведение в один поток")
      val resultArray: Array[Array[Int]] = Array.ofDim[Int](this.rowsNumber, other.colsNumber)

      for (i <- 0 until this.rowsNumber)
        for (j <- 0 until other.colsNumber) {
          resultArray(i)(j) = compute(i, j, other)
        }

      Matrix(resultArray)
    }
  }

  // Вычисление элемента произведения матриц однопоточным методом
  def compute(i: Int, j: Int, other: Matrix): Int = {
    val ai: Array[Int] = matrix(i)
    val bj: Array[Int] = other.matrix.map(_ (j))
    ai.lazyZip(bj).map { case (x, y) => x * y }.sum
  }

  // Печать матрицы
  def print(): Unit = {
    for (i <- matrix.indices) {
      for (j <- matrix(i).indices)
        scala.Predef.print(s"${matrix(i)(j)} ")
      println()
    }
  }

  // Чтение матрицы из файла
  private def read(filename: String): Array[Array[Int]] = {
    if (new java.io.File(filename).exists) {
      val src: BufferedSource = Source.fromFile(filename)
      try {
        val lines: Iterator[String] = src.getLines
        lines.map { line =>
          line.trim.replaceAll(" +", " ").split(" ").map(_.toInt)
        }.toArray
      } finally src match {
        case s: scala.io.BufferedSource => s.close
      }
    }
    else {
      throw new IllegalArgumentException(s"File $filename doesn't exist")
    }
  }
}

// Объект-компаньон
object Matrix {
  def apply(n: Int, m: Int): Matrix = new Matrix(n, m)
  def apply(filename: String) = new Matrix(filename)
  def apply(array: Array[Array[Int]]) = new Matrix(array)
}
