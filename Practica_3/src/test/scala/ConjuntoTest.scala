import org.scalatest.funsuite.AnyFunSuite

import scala.util.Random

class ConjuntoTest extends AnyFunSuite {
  test("Test conjunto entre un rango inválido") {
    val naturales = (x: Int) => x >= 0
    val conjuntoNaturales = new Conjunto(naturales)

    assert((-50 to 50).forall(e => naturales(e) == conjuntoNaturales(e)))
  }

  test("Test conjunto entre un rango válido") {
    val naturales = (x: Int) => x >= 0
    val conjuntoNaturales = new Conjunto(naturales)

    assert((0 to Conjunto.LIMITE).forall(e => conjuntoNaturales(e)))
  }

  test("Test toString") {
    val naturales = (x: Int) => x >= 0
    val conjuntoNaturales = new Conjunto(naturales)

    val vector = (-Conjunto.LIMITE to Conjunto.LIMITE)
      .filter(e => conjuntoNaturales(e))
      .map(e => e.toString)
    assert(conjuntoNaturales.toString.equals(vector.mkString(", ")))
  }

  test("Test conjuntoUnElemento") {
    val conjunto1 = Conjunto.conjuntoUnElemento(5)
    (0 to 10).foreach(i => {
      if (i == 5) {
        assert(conjunto1(i))
      } else {
        assert(!conjunto1(i))
      }
    })
  }

  test("Test union 1") {
    val conjunto1 = new Conjunto((x: Int) => x > 5)
    val conjunto2 = new Conjunto((x: Int) => x < 0)
    val conjuntoUnion = conjunto1.union(conjunto2)

    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(e => (conjunto1(e) || conjunto2(e)) == conjuntoUnion(e)))
  }

  test("Test union 2 (infinito - {0})") {
    val conjunto1 = new Conjunto((x: Int) => x > 0)
    val conjunto2 = new Conjunto((x: Int) => x < 0)
    val conjuntoUnion = conjunto1.union(conjunto2)

    assert(!conjuntoUnion(0))
    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(e => (conjunto1(e) || conjunto2(e)) == conjuntoUnion(e)))
  }

  test("Test union 3 (infinito)") {
    val conjunto1 = new Conjunto((x: Int) => x > 0)
    val conjunto2 = new Conjunto((x: Int) => x <= 0)
    val conjuntoUnion = conjunto1.union(conjunto2)
    (0 to 10).foreach(_ => {
      val r = Random.between(Int.MinValue, Int.MaxValue)
      assert(conjuntoUnion(r))
    })
    assert(conjuntoUnion(0))
  }

  test("Test interseccion") {
    val conjunto1 = new Conjunto((x: Int) => x > 5)
    val conjunto2 = new Conjunto((x: Int) => x < -5)
    val conjuntoInterseccion = conjunto1.interseccion(conjunto2)

    (0 to 10).foreach(_ => {
      val r = Random.between(Int.MinValue, Int.MaxValue)
      assert(!conjuntoInterseccion(r))
    })
  }

  test("Test interseccion 2") {
    val conjunto1 = new Conjunto((x: Int) => x > 0)
    val conjunto2 = new Conjunto((x: Int) => x < 5)
    val conjuntoInterseccion = conjunto1.interseccion(conjunto2)

    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall((e: Int) => (conjunto1(e) && conjunto2(e)) == conjuntoInterseccion(e)))
  }

  test("Test diferencia 1") {
    val conjunto1 = new Conjunto((x : Int) => x > 5)
    val conjunto2 = new Conjunto((x : Int) => x < -5)
    val conjuntoDiferencia = conjunto1.diferencia(conjunto2)

    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(e => (conjunto1(e) && !conjunto2(e)) == conjuntoDiferencia(e)))
  }

  test("Test diferencia 2") {
    val conjunto1 = new Conjunto((x : Int) => x > 0)
    val conjunto2 = new Conjunto((x : Int) => x < 5)
    val conjuntoDiferencia = conjunto1.diferencia(conjunto2)

    val c1 = (-100 to 100).filter(conjunto1.apply).toSet
    val cD = (-100 to 100).filter(conjunto2.apply).toSet
    val expected = (-100 to 100).filter(conjuntoDiferencia.apply).toSet
    val scalaDifference = c1.diff(cD)

    assert(scalaDifference.equals(expected))
  }

  test("Test filtrar 1") {
    val funcion = (x : Int) => x > 0
    val conjunto1 = new Conjunto((x : Int) => x < 0)
    val conjuntoFiltrar = conjunto1.filtrar(funcion)
    // es el conjunto vacío
    assert(conjuntoFiltrar.toString.equals(""))
  }

  test("Test filtrar 2") {
    val conjunto1 = new Conjunto((x : Int) => x < -5)
    val funcion = (x : Int) => x < -10
    val conjuntoFiltrar = conjunto1.filtrar(funcion)

    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall((e : Int) => (conjunto1(e) && funcion(e)) == conjuntoFiltrar(e)))
  }

  test("Test paraTodo 2") {
    val conjunto1 = new Conjunto((x : Int) => x > 0)
    val predicado = (x : Int) => x >= 1
    val paraTodo = conjunto1.paraTodo(predicado)

    assert(paraTodo)
  }

  test("Test paraTodo 3") {
    val conjunto1 = new Conjunto((x : Int) =>  x >= 0)
    val predicado = (x : Int) => x > 1
    val paraTodo = conjunto1.paraTodo(predicado)

    assert(!paraTodo)
  }

  test("Test existe") {
    val conjunto1 = new Conjunto((x : Int) => x > 0)
    val predicado = (x : Int) => x < 0
    val existe = conjunto1.existe(predicado)

    assert(!existe)
  }

  test("Test existe 2") {
    val conjunto1 = new Conjunto((x : Int) => x > 0)
    val predicado = (x : Int) => x % 2 == 0
    val existe = conjunto1.existe(predicado)

    assert(existe)
  }

  test("Test existe 3") {
    val conjunto1 = new Conjunto((x : Int) => x >= 0)
    val predicado = (x : Int) => x == 0
    val existe = conjunto1.existe(predicado)

    assert(existe)
  }

  test("Test map") {
    val conjunto1 = new Conjunto((x : Int) => x > 0)
    val funcion = (x : Int) => x*2
    val conjuntoMap = conjunto1.map(funcion)

    assert((-Conjunto.LIMITE to Conjunto.LIMITE).forall(e => conjunto1(e) == conjuntoMap(funcion(e))))
  }
}
