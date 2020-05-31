package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class MatrixTest extends AnyFunSuite {
  test("Compute two 2 x 2 matrices multiplications in one thread") {
    val n: Int = 2
    val matrixA: Matrix = Matrix(Array(Array(1, 0), Array(0, 3)))
    val matrixB: Matrix = Matrix(Array(Array(3, 0), Array(0, 2)))
    val etalon: Matrix = Matrix(Array(Array(3, 0), Array(0, 6)))
    val matrixC: Matrix = Matrix(n, n)

    for (i <- 0 until n)
      for (j <- 0 until n)
        matrixC.setElement(i, j, matrixA.compute(i, j, matrixB))

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 3 x 3 matrices multiplications in one thread") {
    val matrixA: Matrix = Matrix(Array(Array(0, 1, 0), Array(-1, 0, 0), Array(0, 0, 1)))
    val matrixB: Matrix = Matrix(Array(Array(-1, 0, 0), Array(0, 1, 0), Array(0, 0, 1)))
    val etalon: Matrix = Matrix(Array(Array(0, 1, 0), Array(1, 0, 0), Array(0, 0, 1)))
    val matrixC: Matrix = matrixA.multiply(matrixB, 1)

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 2 x 2 matrices multiplications in four threads") {
    val n: Int = 2
    val matrixA: Matrix = Matrix(Array(Array(1, 0), Array(0, 3)))
    val matrixB: Matrix = Matrix(Array(Array(3, 0), Array(0, 2)))
    val etalon: Matrix = Matrix(Array(Array(3, 0), Array(0, 6)))
    val matrixC: Matrix = Matrix(n, n)

    val thread1 = new Compute(1, Seq((matrixA, matrixB, matrixC, 0, 0)))
    val thread2 = new Compute(2, Seq((matrixA, matrixB, matrixC, 0, 1)))
    val thread3 = new Compute(3, Seq((matrixA, matrixB, matrixC, 1, 0)))
    val thread4 = new Compute(4, Seq((matrixA, matrixB, matrixC, 1, 1)))

    thread1.start()
    thread2.start()
    thread3.start()
    thread4.start()

    thread1.join()
    thread2.join()
    thread3.join()
    thread4.join()

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute two 3 x 3 matrices multiplications in nine thread") {
    val n: Int = 3
    val matrixA: Matrix = Matrix(Array(Array(0, 1, 0), Array(-1, 0, 0), Array(0, 0, 1)))
    val matrixB: Matrix = Matrix(Array(Array(-1, 0, 0), Array(0, 1, 0), Array(0, 0, 1)))
    val etalon: Matrix = Matrix(Array(Array(0, 1, 0), Array(1, 0, 0), Array(0, 0, 1)))
    val matrixC: Matrix = Matrix(n, n)

    val thread1 = new Compute(1, Seq((matrixA, matrixB, matrixC, 0, 0)))
    val thread2 = new Compute(2, Seq((matrixA, matrixB, matrixC, 0, 1)))
    val thread3 = new Compute(3, Seq((matrixA, matrixB, matrixC, 0, 2)))
    val thread4 = new Compute(4, Seq((matrixA, matrixB, matrixC, 1, 0)))
    val thread5 = new Compute(5, Seq((matrixA, matrixB, matrixC, 1, 1)))
    val thread6 = new Compute(6, Seq((matrixA, matrixB, matrixC, 1, 2)))
    val thread7 = new Compute(7, Seq((matrixA, matrixB, matrixC, 2, 0)))
    val thread8 = new Compute(8, Seq((matrixA, matrixB, matrixC, 2, 1)))
    val thread9 = new Compute(9, Seq((matrixA, matrixB, matrixC, 2, 2)))

    thread1.start()
    thread2.start()
    thread3.start()
    thread4.start()
    thread5.start()
    thread6.start()
    thread7.start()
    thread8.start()
    thread9.start()

    thread1.join()
    thread2.join()
    thread3.join()
    thread4.join()
    thread5.join()
    thread6.join()
    thread7.join()
    thread8.join()
    thread9.join()

    assert(matrixC.matrix === etalon.matrix)
  }
}
