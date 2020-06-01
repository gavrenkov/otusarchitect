package otus.architect

import org.scalatest.funsuite.AnyFunSuite

class p1Test extends AnyFunSuite with p1Trait {

  test("Compute sum of the two 2 x 2 matrices") {
    val matrixA: Matrix = Matrix(Array(Array(1, 1), Array(1, 1)))
    val mAfilename: String = "data/test1A.txt"
    val matrixB: Matrix = Matrix(Array(Array(2, 2), Array(2, 2)))
    val mBfilename: String = "data/test1B.txt"
    val etalon: Matrix = Matrix(Array(Array(3, 3), Array(3, 3)))
    val mCfilename: String = "data/test1C.txt"

    matrixA.save(mAfilename)
    matrixB.save(mBfilename)
    addAndSave(mAfilename, mBfilename, mCfilename)

    val matrixC: Matrix = Matrix(mCfilename)

    assert(matrixC.matrix === etalon.matrix)
  }
}
