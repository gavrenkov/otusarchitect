package otus.architect

import java.io.FileWriter

trait Sorting {
  val outputFilename: String

  def writeType(sortType: String, outputFilename: String): Unit = {
    if (outputFilename != null) {
      val fw = new FileWriter(outputFilename, false)
      try {
        fw.write(s"$sortType\n")
      }
      finally fw.close()
    }
  }

  def sorted(list: List[Int])(implicit ord: Ordering[Int]): List[Int]
}

class Decorator(override val outputFilename: String) extends Sorting {
  def sorted(list: List[Int])(implicit ord: Ordering[Int]): List[Int] = {
    // Записываем метку в файл
    writeType("Default Sort", outputFilename)
    list.sorted
  }
}

// Сортировка выбором
trait selectionSort extends Sorting {
  override def sorted(list: List[Int])(implicit ord: Ordering[Int]): List[Int] = {
    // Записываем метку в файл
    writeType("Selection Sort", outputFilename)

    def remove(e: Int, list: List[Int]): List[Int] =
      list match {
        case Nil => Nil
        case x :: xs if x == e => xs
        case x :: xs => x :: remove(e, xs)
      }

    list match {
      case Nil => Nil
      case _ =>
        val min = list.min
        min :: sorted(remove(min, list))
    }
  }
}

// Сортировка вставкой
trait insertionSort extends Sorting {
  override def sorted(list: List[Int])(implicit ord: Ordering[Int]): List[Int] = {
    // Записываем метку в файл
    writeType("Insertion Sort", outputFilename)

    def insert(list: List[Int], value: Int): List[Int] = list.span(x => ord.lt(x, value)) match {
      case (lower, upper) => lower ::: value :: upper
    }

    list.foldLeft(List.empty[Int])(insert)
  }
}

// Сортировка слиянием
trait mergeSort extends Sorting {
  override def sorted(input: List[Int])(implicit ord: Ordering[Int]): List[Int] = {
    // Записываем метку в файл
    writeType("Merge Sort", outputFilename)

    def merge(left: List[Int], right: List[Int]): LazyList[Int] = (left, right) match {
      case (x :: xs, y :: _) if x <= y => x #:: merge(xs, right)
      case (_ :: _, y :: ys) => y #:: merge(left, ys)
      case _ => if (left.isEmpty) right.to(LazyList) else left.to(LazyList)
    }

    def sort(input: List[Int], length: Int): List[Int] = input match {
      case Nil | List(_) => input
      case _ =>
        val middle = length / 2
        val (left, right) = input splitAt middle
        merge(sort(left, middle), sort(right, middle + length % 2)).toList
    }

    sort(input, input.length)
  }
}