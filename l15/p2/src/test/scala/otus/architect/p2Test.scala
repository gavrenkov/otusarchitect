package otus.architect

import java.io.File

import org.scalatest.funsuite.AnyFunSuite

import scala.io.{BufferedSource, Source}

class p2Test extends AnyFunSuite {

  test("Compute sum of the two 10 x 10 matrices") {
    val n: Int = 10
    val min: Int = -100
    val max: Int = 100
    val logFilename: String = "p2test1.log"

    val mAfilename: String = "data/test1A.txt"
    val matrixA: Matrix = Matrix(n, n).generate(min, max)
    matrixA.save(mAfilename)

    val mBfilename: String = "data/test1B.txt"
    val matrixB: Matrix = Matrix(n, n).generate(min, max)
    matrixB.save(mBfilename)

    val etalon: Matrix = matrixA.add(matrixB, n * n)

    val mCfilename: String = "data/test1C.txt"
    val p1 = new p1Proxy()

    if (new java.io.File(logFilename).exists)
      new File(logFilename).delete()

    p1.setLogFilename(logFilename)
    p1.addAndSave(mAfilename, mBfilename, mCfilename)
    val matrixC: Matrix = Matrix(mCfilename)

    assert(matrixC.matrix === etalon.matrix)
  }

  test("Compute sum of the two 5 x 5 matrices 3 times") {
    val n: Int = 5
    val min: Int = -100
    val max: Int = 100
    val logFilename: String = "p2test2.log"

    val mAfilename: String = "data/test2A.txt"
    val matrixA: Matrix = Matrix(n, n).generate(min, max)
    matrixA.save(mAfilename)

    val mBfilename: String = "data/test2B.txt"
    val matrixB: Matrix = Matrix(n, n).generate(min, max)
    matrixB.save(mBfilename)

    val mCfilename: String = "data/test2C.txt"
    val p1 = new p1Proxy()

    if (new java.io.File(logFilename).exists)
      new File(logFilename).delete()

    p1.setLogFilename(logFilename)
    p1.addAndSave(mAfilename, mBfilename, mCfilename)
    p1.addAndSave(mAfilename, mBfilename, mCfilename)
    p1.addAndSave(mAfilename, mBfilename, mCfilename)

    val src: BufferedSource = Source.fromFile(logFilename)
    val logLines: Int =
    try {
      src.getLines.length
    } finally src match {
      case s: scala.io.BufferedSource => s.close
    }

    assert(logLines === 3)
  }
}
