package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class DecoratorTest extends AnyFunSuite {
  test("Default test") {
    val inputList: List[Int] = List(5, 1, 3)
    val etalon: List[Int] = List(1, 3, 5)
    val sortedList = new Decorator(null).sorted(inputList)

    assert(sortedList === etalon)
  }

  test("Selection test") {
    val inputList: List[Int] = List(5, 1, 3)
    val etalon: List[Int] = List(1, 3, 5)
    val sortedList = (new Decorator(null)  with selectionSort).sorted(inputList)

    assert(sortedList === etalon)
  }

  test("Insertion test") {
    val inputList: List[Int] = List(5, 1, 3)
    val etalon: List[Int] = List(1, 3, 5)
    val sortedList = (new Decorator(null) with insertionSort).sorted(inputList)

    assert(sortedList === etalon)
  }

  test("Merge test") {
    val inputList: List[Int] = List(5, 1, 3)
    val etalon: List[Int] = List(1, 3, 5)
    val sortedList = (new Decorator(null) with mergeSort).sorted(inputList)

    assert(sortedList === etalon)
  }
}
