package otus.architect

class MatrixTrans(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private val matrixA: Matrix = new Matrix()
  private var matrixC: Matrix = new Matrix()
  private val invoker: Invoker = new Invoker()

  override protected def input(inputFiles: Array[String]): Unit = {
    invoker.invoke(new ReadCommand(matrixA, inputFiles(0)))
  }

  override protected def doOps(): Unit = {
    matrixC = matrixA.transpose()
  }

  override protected def output(outputFile: String): Unit = {
    invoker.invoke(new WriteCommand(matrixC, outputFile))
  }
}
