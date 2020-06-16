package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class MatrixTest extends AnyFunSuite {
  test("Create matrix from file") {
    val matrixA = new Matrix()
    matrixA.read("data/addA.txt")
    val result: Array[Array[Int]] = Array(Array(-1, 0, 1), Array(0, 1, -1), Array(1, -1, 0))

    assert(matrixA.matrix === result)
  }

  test("Create empty matrix then fill it from file") {
    val matrixA = new Matrix()
    matrixA.read("data/addB.txt")
    val result: Array[Array[Int]] = Array(Array(1, 0, -1), Array(0, -1, 1), Array(-1, 1, 0))

    assert(matrixA.matrix === result)
  }

  test("Compute two 2 x 2 matrices multiplications in 1 thread") {
    val n: Int = 2
    val matrixA: Matrix = Matrix(Array(Array(1, 0), Array(0, 3)))
    val matrixB: Matrix = Matrix(Array(Array(3, 0), Array(0, 2)))
    val etalon: Matrix = Matrix(Array(Array(3, 0), Array(0, 6)))
    val matrixC: Matrix = Matrix(n, n)

    for (i <- 0 until n)
      for (j <- 0 until n)
        matrixC.setElement(i, j, matrixA.computeCell(i, j, matrixB))

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 2 x 2 matrices addition in 1 thread") {
    val n: Int = 2
    val matrixA: Matrix = Matrix(Array(Array(1, 1), Array(1, 1)))
    val matrixB: Matrix = Matrix(Array(Array(2, 2), Array(2, 2)))
    val etalon: Matrix = Matrix(Array(Array(3, 3), Array(3, 3)))
    val matrixC: Matrix = Matrix(n, n)

    for (i <- 0 until n)
      for (j <- 0 until n)
        matrixC.setElement(i, j, matrixA.getElement(i, j) + matrixB.getElement(i, j))

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Generate 10 x 20 matrix") {
    val matrixA: Matrix = Matrix(10, 20).generate(-100, 100)

    assert(matrixA.rowsNumber === 10 && matrixA.colsNumber === 20)
  }

  test("Determinant 2 x 2 matrix") {
    val matrix: Matrix = Matrix(Array(Array(1, 2), Array(3, 4)))
    val d: Int = matrix.det()

    assert(d === -2)
  }

  test("Determinant 4 x 4 matrix") {
    val matrix: Matrix = Matrix(Array(Array(0, 1, 2, 3), Array(4, 5, 6, 7), Array(8, 9, 10, 11), Array(12, 13, 14, 15)))
    val d: Int = matrix.det()

    assert(d === 0)
  }

  test("Transposition 5 x 4 matrix") {
    val matrix: Matrix = Matrix(Array(Array(1,  1,  1,   1), Array(2,  4,  8,  16), Array(3,  9, 27,  81), Array(4, 16, 64, 256), Array(5, 25, 125, 625)))
    val etalon: Matrix = Matrix(Array(Array(1, 2, 3, 4, 5), Array(1, 4, 9, 16, 25), Array(1, 8, 27, 64, 125), Array(1, 16, 81, 256, 625)))
    val trans: Matrix = matrix.transpose()

    assert(trans.matrix === etalon.matrix)
  }
}
