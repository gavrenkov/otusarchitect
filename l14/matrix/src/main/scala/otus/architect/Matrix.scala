package otus.architect

import java.io.FileWriter

import scala.io.{BufferedSource, Source}

// Класс для работы с матрицами
class Matrix {

  var matrix: Array[Array[Int]] = _
  var rowsNumber: Int = 0 // кол-во строк
  var colsNumber: Int = 0 // кол-во столбцов
  var log: Boolean = false // флаг логирования вычислений
  var logFilename: String = _ // имя файла лога

  def setLog(filename: String): Unit = {
    log = true
    logFilename = filename
  }

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

  // Вычисляем кол-во столбцов
  // Проверяем, что все строки имеют одинаковое кол-во элементов
  private def setColumnSize(): Int = {
    val m: Set[Int] = matrix.map(_.length).toSet
    if (m.size == 1) m.head
    else {
      throw new IllegalArgumentException("The matrix must contain the same number of columns")
    }
  }

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

    if (p > 1)
      computeThreads(other, p, "*", this.rowsNumber, other.colsNumber)
    else {
      val resultArray: Array[Array[Int]] = Array.ofDim[Int](this.rowsNumber, other.colsNumber)
      for (i <- 0 until this.rowsNumber)
        for (j <- 0 until other.colsNumber) {
          resultArray(i)(j) = computeCell(i, j, other)
        }
      Matrix(resultArray)
    }
  }

  // Вычисление элемента произведения матриц однопоточным методом
  def computeCell(i: Int, j: Int, other: Matrix): Int = {
    val ai: Array[Int] = matrix(i)
    val bj: Array[Int] = other.matrix.map(_ (j))
    ai.lazyZip(bj).map { case (x, y) => x * y }.sum
  }

  // Сложение матриц в p потоков
  def add(other: Matrix, p: Int): Matrix = {
    // Проверяем, что размерности матриц позволяют их перемножить
    if ((this.rowsNumber != other.rowsNumber) || (this.colsNumber != other.colsNumber))
      throw new UnsupportedOperationException("Illegal matrices size for addition")

    if (p > 1)
      computeThreads(other, p, "+", this.rowsNumber, other.colsNumber)
    else {
      val resultArray: Array[Array[Int]] = Array.ofDim[Int](this.rowsNumber, this.colsNumber)

      for (i <- 0 until this.rowsNumber)
        for (j <- 0 until this.colsNumber) {
          resultArray(i)(j) = this.getElement(i, j) + other.getElement(i, j)
        }

      Matrix(resultArray)
    }
  }

  // Подготовка и запуск операции над матрицей в p потоков
  def computeThreads(other: Matrix, p: Int, ops: String, rowsNumber: Int, colsNumber: Int): Matrix = {
    val resultMatrix: Matrix = Matrix(rowsNumber, colsNumber)

    // Список ячеек для вычислений
    val cells: Seq[(Int, Int)] =
      (0 until rowsNumber).flatMap { i =>
        (0 until colsNumber) map { j => (i, j) }
      }

    // Количество ячеек на один поток
    val nByP: Int = math.ceil((rowsNumber * colsNumber) / p.toFloat).toInt

    // Подготавливаем p потоков, каждый рассчитает не более nByP ячеек
    val threads: Seq[ComputeCells] = (0 until p) map { pn =>
      (pn, cells.slice(pn * nByP, (pn + 1) * nByP))
    } map { pc =>
      new ComputeCells(pc._1, ops, this, other, resultMatrix, pc._2)
    }

    // Запускаем потоки и ждём их окончания
    threads.foreach(_.start())
    threads.foreach(_.join())

    resultMatrix
  }

  // Получить элемент матрицы
  def getElement(i: Int, j: Int): Int = matrix(i)(j)

  // Заполняем матрицу случайными числами от min до max
  def generate(min: Int, max: Int): Matrix = {
    for (i <- 0 until rowsNumber)
      for (j <- 0 until colsNumber)
        matrix(i)(j) = scala.util.Random.between(min, max)
    this
  }

  // Печать матрицы
  def print(): Unit = {
    for (i <- matrix.indices) {
      for (j <- matrix(i).indices)
        scala.Predef.print(s"${matrix(i)(j)} ")
      println()
    }
  }

  // Сохраняем матрицу в файл
  def save(filename: String): Unit = {
    val fw = new FileWriter(filename, false)
    try {
      for (i <- matrix.indices) {
        for (j <- matrix(i).indices)
          fw.write(s"${matrix(i)(j)} ")
        fw.write("\n")
      }
    }
    finally fw.close()
  }

  // Класс для вычисления операции над матрицей в потоке
  // Получает номер потока, символ операции, входные и выходные матрицы и список ячеек, которые надо вычислить
  // Каждый элемент списка содержит номер строки и номер столбца ячейки
  class ComputeCells(p: Int, ops: String, a: Matrix, b: Matrix, c: Matrix, cells: Seq[(Int, Int)]) extends Thread {
    override def run(): Unit = {
      cells foreach {
        case (i, j) =>
          if (a.log) Logger.log(a.logFilename, s"Thread $p computeCell cell $i $j")
          ops match {
            case "*" => c.setElement(i, j, a.computeCell(i, j, b))
            case "+" => c.setElement(i, j, a.getElement(i, j) + b.getElement(i, j))
          }
      }
    }
  }

}

// Объект-компаньон
object Matrix {
  def apply(rowsNumber: Int, colsNumber: Int): Matrix = new Matrix(rowsNumber, colsNumber)
  def apply(filename: String) = new Matrix(filename)
  def apply(array: Array[Array[Int]]) = new Matrix(array)
}