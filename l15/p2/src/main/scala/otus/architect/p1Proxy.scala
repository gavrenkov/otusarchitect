package otus.architect

// Proxy для P1
class p1Proxy extends p1Trait {
  private val p1: P1 = new P1()
  private var outputFilename: String = _
  private var logFilename: String = _

  // Заменяем метод addAndSave класса P1
  override def addAndSave(inputFile1: String, inputFile2: String, outputFile: String): Unit = {
    // Сохраняем имя выходного файла при первом вызове
    if (outputFilename == null) outputFilename = outputFile

    // Записываем вызов функции в журнал
    Logger.log(logFilename, s"Save $inputFile1 + $inputFile2 to $outputFilename")

    // Вызываем метод класса P1
    p1.addAndSave(inputFile1, inputFile2, outputFilename)
  }

  // Установка имени файла журнала
  def setLogFilename(filename: String): Unit = {
    this.logFilename = filename
  }
}
