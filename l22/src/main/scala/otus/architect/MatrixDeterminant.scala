package otus.architect

import java.io.FileWriter

class MatrixDeterminant(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private val matrixA: Matrix = new Matrix()
  private val invoker: Invoker = new Invoker()

  override protected def input(inputFiles: Array[String]): Unit = {
    invoker.execute(new ReadCommand(matrixA, inputFiles(0)))
  }

  override protected def doOps(): Unit = {
    invoker.execute(new DeterminantCommand(matrixA))
  }

  override protected def output(outputFile: String): Unit = {
    val fw = new FileWriter(outputFile, false)
    try {
      fw.write(s"${matrixA.det}")
    }
    finally fw.close()
  }
}
