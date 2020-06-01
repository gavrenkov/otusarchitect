package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class MatrixTest extends AnyFunSuite {
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

  test("Compute two 3 x 3 matrices multiplications in 1 thread") {
    val matrixA: Matrix = Matrix(Array(Array(0, 1, 0), Array(-1, 0, 0), Array(0, 0, 1)))
    val matrixB: Matrix = Matrix(Array(Array(-1, 0, 0), Array(0, 1, 0), Array(0, 0, 1)))
    val etalon: Matrix = Matrix(Array(Array(0, 1, 0), Array(1, 0, 0), Array(0, 0, 1)))
    val matrixC: Matrix = matrixA.multiply(matrixB, 1)

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 3 x 3 matrices addition in 1 thread") {
    val matrixA: Matrix = Matrix(Array(Array(1, 1, 1), Array(1, 1, 1), Array(1, 1, 1)))
    val matrixB: Matrix = Matrix(Array(Array(2, 2, 2), Array(2, 2, 2), Array(2, 2, 2)))
    val etalon: Matrix = Matrix(Array(Array(3, 3, 3), Array(3, 3, 3), Array(3, 3, 3)))
    val matrixC: Matrix = matrixA.add(matrixB, 1)

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 2 x 2 matrices multiplications in 4 threads") {
    val matrixA: Matrix = Matrix(Array(Array(1, 0), Array(0, 3)))
    val matrixB: Matrix = Matrix(Array(Array(3, 0), Array(0, 2)))
    val etalon: Matrix = Matrix(Array(Array(3, 0), Array(0, 6)))
    val matrixC: Matrix = matrixA.multiply(matrixB, 4)

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 2 x 2 matrices addition in 4 threads") {
    val matrixA: Matrix = Matrix(Array(Array(1, 0), Array(0, 1)))
    val matrixB: Matrix = Matrix(Array(Array(0, 1), Array(1, 0)))
    val etalon: Matrix = Matrix(Array(Array(1, 1), Array(1, 1)))
    val matrixC: Matrix = matrixA.add(matrixB, 4)

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 3 x 3 matrices multiplications in 9 thread") {
    val matrixA: Matrix = Matrix(Array(Array(0, 1, 0), Array(-1, 0, 0), Array(0, 0, 1)))
    val matrixB: Matrix = Matrix(Array(Array(-1, 0, 0), Array(0, 1, 0), Array(0, 0, 1)))
    val etalon: Matrix = Matrix(Array(Array(0, 1, 0), Array(1, 0, 0), Array(0, 0, 1)))
    val matrixC: Matrix = matrixA.multiply(matrixB, 9)

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 3 x 3 matrices addition in 9 thread") {
    val matrixA: Matrix = Matrix(Array(Array(-1, 0, 1), Array(0, 1, -1), Array(1, -1, 0)))
    val matrixB: Matrix = Matrix(Array(Array(1, 0, -1), Array(0, -1, 1), Array(-1, 1, 0)))
    val etalon: Matrix = Matrix(Array(Array(0, 0, 0), Array(0, 0, 0), Array(0, 0, 0)))
    val matrixC: Matrix = matrixA.add(matrixB, 9)

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Generate 10 x 20 matrix") {
    val matrixA: Matrix = Matrix(10, 20).generate(-100, 100)

    assert(matrixA.rowsNumber === 10 && matrixA.colsNumber === 20)
  }
}