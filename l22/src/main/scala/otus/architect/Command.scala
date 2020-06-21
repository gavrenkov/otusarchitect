package otus.architect

trait Command {
  def execute(): Unit
}

class ReadCommand(matrix: Matrix, filename: String) extends Command {
  override def toString: String = "Read Command"

  override def execute(): Unit = {
    matrix.read(filename)
  }
}

class WriteCommand(matrix: Matrix, filename: String) extends Command {
  override def toString: String = "Write Command"

  override def execute(): Unit = {
    matrix.save(filename)
  }
}

class MultiplyCommand(matrixA: Matrix, matrixB: Matrix, matrixC: Matrix) extends Command {
  override def toString: String = "Multiply Command"

  override def execute(): Unit = {
    matrixC.setMatrix(matrixA.multiply(matrixB, 1).matrix)
  }
}

class AddCommand(matrixA: Matrix, matrixB: Matrix, matrixC: Matrix) extends Command {
  override def toString: String = "Add Command"

  override def execute(): Unit = {
    matrixC.setMatrix(matrixA.add(matrixB, 1).matrix)
  }
}

class TransposeCommand(matrixA: Matrix, matrixC: Matrix) extends Command {
  override def toString: String = "Transpose Command"

  override def execute(): Unit = {
    matrixC.setMatrix(matrixA.transpose().matrix)
  }
}

class DeterminantCommand(matrixA: Matrix) extends Command {
  override def toString: String = "Determinant Command"

  override def execute(): Unit = {
    matrixA.computeDeterminant()
  }
}
