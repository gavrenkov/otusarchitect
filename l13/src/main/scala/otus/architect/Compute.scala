package otus.architect

// Класс для вычисления произведение в потоке
// Получает номер потока и список ячеек, которые надо вычислить
// Каждый элемент списка содержит:
// - левую матрицу произведения
// - правую матрицу произведения
// - матрицу для произведения
// - номер строки ячейки
// - номер столбца ячейки
class Compute(p: Int, cells: Seq[(Matrix, Matrix, Matrix, Int, Int)]) extends Thread {
  override def run(): Unit = {
    cells foreach {
      case (a, b, c, i, j) => new Singleton().log(s"Thread $p compute cell $i $j")
        c.setElement(i, j, a.compute(i, j, b))
    }
  }
}
