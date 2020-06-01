package otus.architect

// Адаптер для P1
// Заменяет метод addAndSave P1 методом p2Task
class p1Adapter extends p1Trait {
  def p2Task(inputFile1: String, inputFile2: String, outputFile: String): Unit =
    addAndSave(inputFile1, inputFile2, outputFile)
}

object p1Adapter {
  def apply(): p1Adapter = new p1Adapter()
}