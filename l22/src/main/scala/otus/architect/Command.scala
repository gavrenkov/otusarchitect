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

class RunCommand(args: Array[String]) extends Command {
  override def toString: String = "Run Command"

  override def execute(): Unit = {
    val matrixOps: MatrixOps = args(0) match {
      case "*" => new MatrixMult(args.slice(1, 3), args(3))
      case "+" => new MatrixAdd(args.slice(1, 3), args(3))
      case "t" => new MatrixTrans(args.slice(1, 2), args(2))
      case "d" => new MatrixDet(args.slice(1, 2), args(2))
      case _ => println("Unknown operation")
        sys.exit(-1)
    }
    matrixOps.run()
  }
}
