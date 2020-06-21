package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class MatrixTest extends AnyFunSuite {
  test("Create matrix from file") {
    val matrixA = new Matrix()
    val invoker: Invoker = new Invoker()

    invoker.execute(new ReadCommand(matrixA, "data/addA.txt"))
    val result: Array[Array[Int]] = Array(Array(-1, 0, 1), Array(0, 1, -1), Array(1, -1, 0))

    assert(matrixA.matrix === result)
  }

  test("Compute two 2 x 2 matrices multiplications") {
//    val n: Int = 2
    val matrixA: Matrix = Matrix(Array(Array(1, 0), Array(0, 3)))
    val matrixB: Matrix = Matrix(Array(Array(3, 0), Array(0, 2)))
    val etalon: Matrix = Matrix(Array(Array(3, 0), Array(0, 6)))
    val matrixC: Matrix = new Matrix()
    val invoker: Invoker = new Invoker()

    invoker.execute(new MultiplyCommand(matrixA, matrixB, matrixC))

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 2 x 2 matrices addition") {
//    val n: Int = 2
    val matrixA: Matrix = Matrix(Array(Array(1, 1), Array(1, 1)))
    val matrixB: Matrix = Matrix(Array(Array(2, 2), Array(2, 2)))
    val etalon: Matrix = Matrix(Array(Array(3, 3), Array(3, 3)))
    val matrixC: Matrix = new Matrix()
    val invoker: Invoker = new Invoker()

    invoker.execute(new AddCommand(matrixA, matrixB, matrixC))

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Determinant 2 x 2 matrix") {
    val matrixA: Matrix = Matrix(Array(Array(1, 2), Array(3, 4)))
    val invoker: Invoker = new Invoker()

    invoker.execute(new DeterminantCommand(matrixA))

    assert(matrixA.det === -2)
  }

  test("Transposition 5 x 4 matrix") {
    val matrixA: Matrix = Matrix(Array(Array(1,  1,  1,   1), Array(2,  4,  8,  16), Array(3,  9, 27,  81), Array(4, 16, 64, 256), Array(5, 25, 125, 625)))
    val etalon: Matrix = Matrix(Array(Array(1, 2, 3, 4, 5), Array(1, 4, 9, 16, 25), Array(1, 8, 27, 64, 125), Array(1, 16, 81, 256, 625)))
    val matrixC: Matrix = new Matrix()
    val invoker: Invoker = new Invoker()

    invoker.execute(new TransposeCommand(matrixA, matrixC))

    assert(matrixC.matrix === etalon.matrix)
  }
}
