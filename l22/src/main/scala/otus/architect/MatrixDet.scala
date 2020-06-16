package otus.architect

import java.io.FileWriter

class MatrixDet(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private val matrixA: Matrix = new Matrix()
  private var dt: Int = _
  private val invoker: Invoker = new Invoker()

  override protected def input(inputFiles: Array[String]): Unit = {
    invoker.invoke(new ReadCommand(matrixA, inputFiles(0)))
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
