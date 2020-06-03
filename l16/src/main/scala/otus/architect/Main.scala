package otus.architect

import java.io.FileWriter
import scala.io.{BufferedSource, Source}

object Main {
  def main(args: Array[String]): Unit = {
    // Разбираем параметры запуска
    if (args.length != 3) {
      println(s"Usage: sortType inputFile outputFile")
      println("where:")
      println("  sortType - type of sort: selection, insertion, merge")
      println("  inputFile - file with the input array")
      println("  outputFile - file with the sorted array")
      sys.exit(-1)
    }

    // Читаем массив из файла
    val inputList: List[Int] = read(args(1))

    val sortedList: List[Int] =
      (args(0).toLowerCase match {
        case "selection" => new Decorator(args(2)) with selectionSort
        case "insertion" => new Decorator(args(2)) with insertionSort
        case "merge" => new Decorator(args(2)) with mergeSort
        case _ => new Decorator(args(2))
      }).sorted(inputList)

    // Записываем отсортированный массив в файл
    write(sortedList, args(2))
  }

  // Читаем массив из файла
  def read(filename: String): List[Int] = {
    if (new java.io.File(filename).exists) {
      val src: BufferedSource = Source.fromFile(filename)
      try {
        val lines: Iterator[String] = src.getLines
        lines.flatMap { line =>
          line.trim.replaceAll(" +", " ").split(" ").map(_.toInt)
        }.toList
      } finally src match {
        case s: scala.io.BufferedSource => s.close
      }
    }
    else {
      throw new IllegalArgumentException(s"File $filename doesn't exist")
    }
  }

  // Записываем массив в файл
  def write(arr: List[Int], filename: String): Unit = {
    val fw = new FileWriter(filename, true)
    try {
      arr.foreach { a => fw.write(s"$a\n") }
    }
    finally fw.close()
  }
}
