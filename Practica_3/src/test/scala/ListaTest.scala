import org.scalatest.funsuite.AnyFunSuite

class ListaTest extends AnyFunSuite {
  test("Test apply"){
    assert(Lista(1, Int.MaxValue).isInstanceOf[Cons[Int]])
    assert(Lista(1.3, 3.5).isInstanceOf[Cons[Double]])
    assert(Lista('a', 'b').isInstanceOf[Cons[Char]])
    assert(Lista("Variable").isInstanceOf[Cons[String]])
  }

  test("Test longitud") {
    val tam = Lista.longitud(Lista(1,1,1,1,1))
    assert(tam == 5)
  }

  test("Test sumaEnteros") {
    val scalaList = List(1,2,3,4,5,6,7,8,9,10)
    val list = Lista(1,2,3,4,5,6,7,8,9,10)
    assert(Lista.sumaEnteros(list) == scalaList.sum)
  }

  test("Test productoEnteros") {
    val list = Lista(1,2,3)
    assert(Lista.productoEnteros(list) == 6)
  }

  test("Test concatenar") {
    val list = Lista(1,2,3)
    val list2 = Lista(4,5,6)
    assert(Lista.concatenar(list, list2) == Lista(1,2,3,4,5,6))
  }

  test("Test foldRight") {
    val list = Lista(1,2,3,4,5)
    def f(a: Int, b: Int) = a * b

    val rtado = Lista.foldRight(list, 1)(f)
    assert(rtado == 120)
  }

  test("Test sumaFoldRight") {
    val list = Lista(1,2,3,4,5)
    def f(a: Int, b: Int) = a + b

    val rtado = Lista.foldRight(list, 0)(f)
    val rtadoSuma = Lista.sumaFoldRight(list)
    assert(rtado == rtadoSuma)
  }

  test("Test productoFoldRight") {
    val list = Lista(1,2,3,4,5)
    def f(a: Int, b: Int) = a * b

    val rtado = Lista.foldRight(list, 1)(f)
    val rtadoProd = Lista.productoFoldRight(list)
    assert(rtado == rtadoProd)
  }

  test("Test asignarCabeza") {
    val list = Lista(1,2,3,4,5)
    val listMod = Lista.asignarCabeza(list, 0)
    assert(Lista.head(listMod)  == Some(0))
  }

  test("Test head1") {
    val list = Lista(1,2,3,4,5)
    val actual = Lista.head(list)
    assert(actual == Some(1))
  }

  test("Test head2") {
    val list = Lista()
    val actual = Lista.head(list)
    assert(actual == None)
  }

  test("TEst tail1") {
    val list = Lista()
    val actual = Lista.tail(list)
    assert(actual == Nil)
  }

  test("TEst tail2") {
    val list = Lista(1,2,3)
    val actual = Lista.tail(list)
    assert(actual == Lista(2,3))
  }

  test("TEst Elimina") {
    val list = Lista(1,2,3,4,5,6,7)
    val actual = Lista.eliminar(list, 3)
    assert(actual == Lista(4,5,6,7))
  }

  test("Test Elimina con N mayor al tam") {
    val list = Lista(1,2,3,4,5,6,7)
    val actual = Lista.eliminar(list, 30)
    assert(actual == Nil)
  }

  test("Test EliminMientras") {
    val list = Lista(2,4,5,6,7)
    def f(a: Int) = a % 2 == 0
    val actual = Lista.eliminarMientras(list, f)
    assert(actual == Lista(5,6,7))
  }

  test("Test EliminarUltimo1") {
    val list = Lista(2,4,5,6,7)
    val actual = Lista.eliminarUltimo(list)
    assert(actual == Lista(2,4,5,6))
  }

  test("Test EliminarUltimo2") {
    val list = Lista()
    val actual = Lista.eliminarUltimo(list)
    assert(actual == Nil)
  }

  test("Test foldLeft") {
    val list = Lista(1,4,5,6,3,8,9,0,10)
    def f(a: Int, b: Int) = a - b
    val actual = Lista.foldLeft(list, 0)(f)
    assert(actual == -46)
  }
}
