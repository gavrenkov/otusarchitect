package otus.architect

class MatrixTranspose(override val inputFiles: Array[String], override val outputFile: String) extends MatrixOps {
  private val matrixA: Matrix = new Matrix()
  private val matrixC: Matrix = new Matrix()
  private val invoker: Invoker = new Invoker()

  override protected def input(inputFiles: Array[String]): Unit = {
    invoker.execute(new ReadCommand(matrixA, inputFiles(0)))
  }

  override protected def doOps(): Unit = {
    invoker.execute(new TransposeCommand(matrixA, matrixC))
  }

  override protected def output(outputFile: String): Unit = {
    invoker.execute(new WriteCommand(matrixC, outputFile))
  }
}
