package otus.architect

trait p1Trait {
  def addAndSave(inputFile1: String, inputFile2: String, outputFile: String): Unit
}

class P1 extends p1Trait {
  def addAndSave(inputFile1: String, inputFile2: String, outputFile: String): Unit = {
    // Заполняем исходные матрицы данными из файлов
    val matrixA: Matrix = Matrix(inputFile1)
    val matrixB: Matrix = Matrix(inputFile2)

    // Складываем матрицы
    val matrixC: Matrix = matrixA.add(matrixB, 1)

    // Сохраняем результат сложения в файл
    matrixC.save(outputFile)
  }
}

object P1 {
  def apply(): P1 = new P1()
}