package otus.architect

import java.io.FileWriter

abstract class MatrixOps {
  val inputFiles: Array[String]
  val outputFile: String

  final def run(): Unit = {
    input(inputFiles: Array[String])
    doOps()
    output(outputFile: String)
  }

  protected def input(inputFiles: Array[String]): Unit

  protected def doOps(): Unit

  protected def output(outputFile: String): Unit
}

class MatrixMult(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private var matrixA: Matrix = _
  private var matrixB: Matrix = _
  private var matrixC: Matrix = _

  override protected def input(inputFiles: Array[String]): Unit = {
    matrixA = Matrix(inputFiles(0))
    matrixB = Matrix(inputFiles(1))
  }

  override protected def doOps(): Unit = {
    matrixC = matrixA.multiply(matrixB, 1)
  }

  override protected def output(outputFile: String): Unit = {
    matrixC.save(outputFile)
  }
}

class MatrixAdd(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private var matrixA: Matrix = _
  private var matrixB: Matrix = _
  private var matrixC: Matrix = _

  override protected def input(inputFiles: Array[String]): Unit = {
    matrixA = Matrix(inputFiles(0))
    matrixB = Matrix(inputFiles(1))
  }

  override protected def doOps(): Unit = {
    matrixC = matrixA.add(matrixB, 1)
  }

  override protected def output(outputFile: String): Unit = {
    matrixC.save(outputFile)
  }
}

class MatrixTrans(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private var matrixA: Matrix = _
  private var matrixC: Matrix = _

  override protected def input(inputFiles: Array[String]): Unit = {
    matrixA = Matrix(inputFiles(0))
  }

  override protected def doOps(): Unit = {
    matrixC = matrixA.transpose()
  }

  override protected def output(outputFile: String): Unit = {
    matrixC.save(outputFile)
  }
}

class MatrixDet(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private var matrixA: Matrix = _
  private var dt: Int = _

  override protected def input(inputFiles: Array[String]): Unit = {
    matrixA = Matrix(inputFiles(0))
  }

  override protected def doOps(): Unit = {
    dt = matrixA.det()
  }

  override protected def output(outputFile: String): Unit = {
    val fw = new FileWriter(outputFile, false)
    try {
      fw.write(s"$dt")
    }
    finally fw.close()
  }
}
