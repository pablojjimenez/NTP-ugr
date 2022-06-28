package ntp.pablojj

import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.{all, forAll, propBoolean}

object FuncionesTest extends Properties("FuncionesTest"){

  val MAXIMO = 20

  val coordenadasExtremos = for {
    fila <- Gen.choose(0, MAXIMO)
    columna <- Gen.oneOf(0, fila)
  } yield (fila, columna)

  property("Triangulo de Pascal: Elementos en lados del triangulo valen 1") = {
    forAll(coordenadasExtremos) {
      (i) => {
        val resultado = Funciones.calcularValorTrianguloPascal(i._1, i._2)
        resultado == 1
      }
    }
  }

  val coordenadasInternas = for {
    fila <- Gen.choose(2, MAXIMO)
    columna <- Gen.oneOf(1, fila-1)
  } yield (fila, columna)

  property("Triangulo de Pascal: Los valores internos son la suma de los 2 superiores") = {
    forAll(coordenadasInternas) {
      (i) => {
        val resultado=Funciones.calcularValorTrianguloPascal(i._1, i._2)
        resultado == Funciones.calcularValorTrianguloPascal(i._1-1, i._2-1) +
          Funciones.calcularValorTrianguloPascal(i._1-1, i._2)
      }
    }

  }

  property("Test cambio monedas") = {
    val cambio1 = Funciones.listarCambiosPosibles(23, List(1,2,5,10)) == 52
    val cambio2 = Funciones.listarCambiosPosibles(4, List(1,2)) == 3
    val cambio3 = Funciones.listarCambiosPosibles(15, List(20,25)) == 0
    val cambio4 = Funciones.listarCambiosPosibles(8, List(2,4,8)) == 4
    val cambio5 = Funciones.listarCambiosPosibles(0, List(1,2,3,4,5)) == 1
    val cambio6 = Funciones.listarCambiosPosibles(20, List()) == 0

    all (cambio1, cambio2, cambio3, cambio4, cambio5, cambio6)
  }

  property("Test busqueda generica") = {
    val lista = (1 to 10).map(i => i).toList
    val rtado1 = Funciones.busquedaGenerica[Int](lista, 1, _ < _) == 0
    val rtado2 = Funciones.busquedaGenerica[Int](lista, 10, _ < _) == 9
    val rtado3 = Funciones.busquedaGenerica[Int](lista, 50, _ < _) == -1
    val rtado4 = Funciones.busquedaGenerica[Int](lista, 7, _ < _) == 6
    val rtado5 = Funciones.busquedaGenerica[Int](lista, 3, _ < _) == 2

    all (rtado1, rtado2, rtado3, rtado4, rtado5)
  }

  property("Test busqueda Fibonacci 1") = {
    val lista = (1 to 10).map(i => i).toList
    val rtado1 = Funciones.busquedaFibonacci[Int](lista, 1, _ > _, _ < _) == 0
    val rtado2 = Funciones.busquedaFibonacci[Int](lista, 10, _ > _, _ < _) == 9
    val rtado3 = Funciones.busquedaFibonacci[Int](lista, 50, _ > _, _ < _) == -1
    val rtado4 = Funciones.busquedaFibonacci[Int](lista, 7, _ > _, _ < _) == 6
    val rtado5 = Funciones.busquedaFibonacci[Int](lista, 3, _ > _, _ < _) == 2

    all (rtado1, rtado2, rtado3, rtado4, rtado5)
  }

  property("Test busqueda Fibonacci 1") = {
    val lista = (1 to 100).map(i => i).toList
    val rtado1 = Funciones.busquedaFibonacci[Int](lista, 1, _ > _, _ < _) == 0
    val rtado2 = Funciones.busquedaFibonacci[Int](lista, 10, _ > _, _ < _) == 9
    val rtado3 = Funciones.busquedaFibonacci[Int](lista, 50, _ > _, _ < _) == 49
    val rtado4 = Funciones.busquedaFibonacci[Int](lista, 7, _ > _, _ < _) == 6
    val rtado5 = Funciones.busquedaFibonacci[Int](lista, 3, _ > _, _ < _) == 2

    all (rtado1, rtado2, rtado3, rtado4, rtado5)
  }

}
