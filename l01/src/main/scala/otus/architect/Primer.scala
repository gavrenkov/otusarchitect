package otus.architect

object Primer extends App {
  println("Проверяем работу класса 'Строка'")
  println("Создаём пустую строку")
  val ms1 = new MyString()
  println(ms1.getValue)

  println("Создаём непустую строку")
  val ms2 = new MyString("abc")
  println(ms2.getValue)

  println("Создаём строку из символа")
  val ms3 = new MyString('c')
  println(ms3.getValue)

  println()
  println("Проверяем работу расширенного класса 'Строка'")
  val msa1 = new MyStringArray("abc")
  println("Преобразуем строку в массив")
  msa1.toCharArray.foreach(println)

  println("Выводим второй элемент массива, полученного из строки")
  msa1.toCharArray(1, 2).foreach(println)

  println()
  println("Проверяем стек для строк")
  println("Создаём пустой стек")
  val s = new StringStack(5)
  println(s"Stack size = ${s.getSize}")
  println(s"Stack empty: ${s.isEmpty}")

  println("Добавляем в стек один элемент типа 'Строка'")
  s.push(new MyString("s1"))
  println(s"Stack size = ${s.getSize}")
  println(s"Stack empty: ${s.isEmpty}")
  print("Stack elements: ")
  s.getValues.map(_.value).foreach(v => print(s"$v "))
  println()

  println("Добавляем в стек один элемент типа 'Расширенная Строка'")
  s.push(new MyStringArray("sa2"))
  println(s"Stack size = ${s.getSize}")
  print("Stack elements: ")
  s.getValues.map(_.value).foreach(v => print(s"$v "))
  println()

  println("Получаем верхний элемент стека без его удаления")
  println(s"Top stack element: ${s.peek().getValue}")
  println(s"Stack size = ${s.getSize}")

  println("Удаляем элемент из стека")
  s.pop()
  println(s"Stack size = ${s.getSize}")
  print("Stack elements: ")
  s.getValues.map(_.value).foreach(v => print(s"$v "))
  println()

}
