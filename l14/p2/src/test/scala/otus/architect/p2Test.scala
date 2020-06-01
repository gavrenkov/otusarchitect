package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class p2Test extends AnyFunSuite with p1Trait {

  test("Compute sum of the two 10 x 10 matrices") {
    val mAfilename: String = "data/test1A.txt"
    val matrixA: Matrix = Matrix(10, 10).generate(-100, 100)
    matrixA.save(mAfilename)

    val mBfilename: String = "data/test1B.txt"
    val matrixB: Matrix = Matrix(10, 10).generate(-100, 100)
    matrixB.save(mBfilename)

    val etalon: Matrix = matrixA.add(matrixB, 100)

    val mCfilename: String = "data/test1C.txt"
    p1Adapter().p2Task(mAfilename, mBfilename, mCfilename)
    val matrixC: Matrix = Matrix(mCfilename)

    assert(matrixC.matrix === etalon.matrix)
  }
}
