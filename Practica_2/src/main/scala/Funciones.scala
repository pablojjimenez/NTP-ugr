package ntp.pablojj

import scala.math.{sqrt}

object Funciones {
  /**
   * EJERCICIO 1: Triangulo de Pascal
   *
   * @param fila    fila del triangulo
   * @param columna columna del triangulo
   * @return valor del triangulo en dicha fila y columna
   */
  def calcularValorTrianguloPascal(fila: Int, columna: Int): Int = {
    if (fila == columna || columna == 0) 1
    else calcularValorTrianguloPascal(fila - 1, columna - 1) +
      calcularValorTrianguloPascal(fila - 1, columna)
  }

  /**
   * Funcion para contar el numero de cambios posibles dada una cantidad y
   * un conjunto de monedas
   *
   * @param cantidad a devolver
   * @param monedas  monedas disponibles
   * @return diferentes formas (en número).
   */
  def listarCambiosPosibles(cantidad: Int, monedas: List[Int]): Int = {
    def cambios(cantidad: Int, monedas: List[Int]): Int = {
      if (cantidad == 0)
        1
      else if (monedas.isEmpty || cantidad < monedas.head)
        0
      else {
        var acum = 0
        for (i <- 0 to cantidad by monedas.head) {
          acum += cambios(cantidad - i, monedas.tail)
        }
        acum
      }
    }

    if (cantidad > 0)
      cambios(cantidad, monedas)
    else
      1
  }

  def main(args: Array[String]) = {
    print(listarCambiosPosibles(0, List()))
  }

  /**
   * EJERCICIO 3: Búsqueda genérica
   *
   * @param coleccion Lista de objetos
   * @param aBuscar   Elementos a buscar
   * @param criterio  Criterio para conocer el orden de los elementos
   * @tparam A Tipo de los objetos
   * @return posición del elemento a buscar
   */
  def busquedaGenerica[A](coleccion: List[A], aBuscar: A, criterio: (A, A) => Boolean): Int = {
    def busquedaLineal(start: Int, toEnd: Int): Int = {
      for (i <- start to toEnd) {
        if (coleccion(i) == aBuscar) return i
      }
      -1
    }

    def check(coleccion: List[A], aBuscar: A, bloque: Int, bloqueRepes: Int): Int = {

      val maxBloque = bloqueRepes * bloque
      val fulfilCritery: Boolean = if (maxBloque - 1 < coleccion.length) criterio(coleccion(maxBloque - 1), aBuscar) else false

      if (fulfilCritery) {
        check(coleccion, aBuscar, bloque, bloqueRepes + 1)
      } else {
        val start = maxBloque - bloque
        val toEnd = if (maxBloque - 1 >= coleccion.length) coleccion.length - 1 else maxBloque
        busquedaLineal(start, toEnd)
      }
    }

    val bloque = sqrt(coleccion.length).round.toInt
    if (coleccion.length == 0)
      -1
    else
      check(coleccion, aBuscar, bloque, 1)
  }

  /**
   * EJERCICIO 4: Búsqueda de Fibonacci
   *
   * @param coleccion Lista de objetos
   * @param aBuscar Elementos a buscar
   * @param mayor Criterio de orden para saber que un elemento de la lista
   *              es mayor que otro. Ejemplo para enteros _ > _
   * @param menor Criterio de orden para saber que un elemento de la lista
   *              es menor que otro. Ejemplo para enteros _ < _
   * @tparam A Tipo de los objetos
   * @return posición del elemento a buscar
   */
  def busquedaFibonacci[A](coleccion: List[A], aBuscar: A, mayor: (A, A) => Boolean, menor: (A, A) => Boolean): Int = {

    def check(_f0: Int, _f1: Int, _f2: Int, _inicio: Int, _n: Int): Int = {
      var f0 = _f0
      var f1 = _f1
      var f2 = _f2
      var inicio = _inicio
      val indice = Math.min(f0 + inicio, _n - 1)

      if (mayor(aBuscar, coleccion(indice))) {
        f2 = f1
        f1 = f0
        f0 = f2 - f0
        inicio = indice
      } else if (menor(aBuscar, coleccion(indice))) {
        f2 = f0
        f1 = f1 - f0
        f0 = f2 - f1
      } else {
        return indice
      }

      if (f2 > 1) {
        return check(f0, f1, f2, inicio, _n)
      }
      -1
    }

    val tam = coleccion.length

    if (tam != 0) {
      var f0 = 0
      var f1 = 1
      var f2 = f0 + f1
      while (f2 < tam) {
        f0 = f1
        f1 = f2
        f2 = f0 + f1
      }
      val inicio = -1
      check(f0, f1, f2, inicio, tam)
    } else -1
  }
}
