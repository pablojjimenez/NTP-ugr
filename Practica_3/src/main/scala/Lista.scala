
/**
 * Interfaz genérica para la lista
 * @tparam A
 */
sealed trait Lista[+A]

/**
 * Objeto para definir lista vacia
 */
case object Nil extends Lista[Nothing]

/**
 * Clase para definir la lista como compuesta por elemento inicial
 * (cabeza) y resto (cola)
 * @param cabeza
 * @param cola
 * @tparam A
 */
case class Cons[+A](cabeza: A, cola: Lista[A]) extends Lista[A]

object Lista {
  /**
   * Constructor fábrica del objeto Lista
   * @param elementos secuencia inicial de elementos
   * @tparam A tipo de los elementos
   * @return Lista de tipo A
   */
  def apply[A](elementos: A*): Lista[A] = {
    if (elementos.isEmpty) {
      Nil
    } else {
      Cons(elementos.head, apply(elementos.tail: _*))
    }
  }

  /**
   * Obtiene la longitud
   * @param lista
   * @tparam A
   * @return
   */
  def longitud[A](lista: Lista[A]): Int = {
    lista match {
      case Nil => 0
      case Cons(_, cola) => longitud(cola) + 1
    }
  }

  /**
   * Sumar lista de numeros
   * @param enteros
   * @return
   */
  def sumaEnteros(enteros: Lista[Int]): Double = {
    enteros match {
      case Nil => 0
      case Cons(cabeza, cola) => cabeza + sumaEnteros(cola)
    }
  }

  /**
   * Metodo para Multiplicar los Valores de una Lista de Enteros
   * @param enteros
   * @return
   */
  def productoEnteros(enteros : Lista[Int]) : Double = {
    enteros match {
      case Nil => 1
      case Cons(cabeza, cola) => cabeza.toDouble * productoEnteros(cola)
    }
  }

  /**
   * Une dos Listas en una
   * @param lista1 lista 1
   * @param lista2 lista 2
   * @return Lista union de los dos parametros
   */
  def concatenar[A](lista1: Lista[A], lista2: Lista[A]): Lista[A] = {
    lista1 match {
      case Nil => lista2
      case Cons(cabeza, cola) => Cons(cabeza, concatenar(cola, lista2))
    }
  }

  /**
   * Aplica una funcion de forma sucesiva a los elementos de la lista
   * con asociatividad por la derecha
   * @param lista la lista
   * @param neutro elemento neutro
   * @param funcion funcion a aplicar
   * @return
   */
  def foldRight[A, B](lista : Lista[A], neutro : B)(funcion: (A, B) => B): B = {
    lista match {
      case Nil => neutro
      case Cons(cabeza, cola) => funcion(cabeza, foldRight(cola, neutro)(funcion))
    }
  }

  /**
   * Aplica por la derecha la operacion suma
   * @param listaEnteros lista a la que se le aplica
   * @return
   */
  def sumaFoldRight(listaEnteros : Lista[Int]) : Double = {
    foldRight(listaEnteros, 0)((x, y) => x + y)
  }

  /**
   * Aplica por la derecha la operación producto
   * @param listaEnteros
   * @return
   */
  def productoFoldRight(listaEnteros : Lista[Int]) : Double = {
    foldRight(listaEnteros, 1)((x, y) => x * y)
  }

  /**
   * Reemplaza la cabeza por un nuevo valor. Se asume que si la lista está vacía, se
   * devuelve una lista con el nuevo elemente.
   * @param lista lista de ref
   * @param cabezaNueva nueva cabeza
   * @return lista con la cabeza modificada
   */
  def asignarCabeza[A](lista : Lista[A], cabezaNueva : A): Lista[A] = {
    lista match {
      case Nil => Cons(cabezaNueva, Nil)
      case Cons(_, cola) => Cons(cabezaNueva, cola)
    }
  }

  /**
   * Devuelve el primer elemento de la lista (si no esta vacia)
   * @param lista lista de ref
   * @return primer elemento de la lista
   */
  def head[A](lista : Lista[A]) : Option[A] = {
    lista match {
      case Nil => None
      case Cons(cabeza, _) => Some(cabeza)
    }
  }

  /**
   * Elimina el elemento cabeza de la lista
   * @param lista lista de ref
   * @return lista modificada
   */
  def tail[A](lista : Lista[A]): Lista[A] = {
    lista match {
      case Nil => Nil
      case Cons(_, cola) => cola
    }
  }

  /**
   * Elimina los n primeros elementos de una lista
   * @param lista la lista
   * @param n numero de elementos a eliminar
   * @tparam A tipo de datos
   * @return Lista moficada
   */
  def eliminar[A](lista : Lista[A], n: Int) : Lista[A] = {
    if (n == 0) {
      return lista
    }

    lista match {
      case Nil => Nil
      case Cons(_, cola) => eliminar(cola, n - 1)
      case _ => lista
    }
  }

  /**
   * Elimina elementos mientras se cumple la condicion {criterio}
   * @param lista lista
   * @param criterio predicado
   * @tparam A tipo de datos a usar
   * @return
   */
  def eliminarMientras[A](lista : Lista[A], criterio: A => Boolean) : Lista[A] = {
    lista match {
      case Nil => Nil
      case Cons(cabeza, cola) =>
        if (criterio(cabeza)) {
          eliminarMientras(cola, criterio)
        } else {
          lista
        }
    }
  }

  /**
   * Elimina el Ultimo Elemento de la Lista. Aqui no se Pueden Compartir Datos en los Objetos y Hay que Generar una
   * Nueva Lista Copiando Datos
   * @param lista lista con la que trabajar
   * @tparam A tipo de datos de la lista
   * @return
   */
  def eliminarUltimo[A](lista : Lista[A]) : Lista[A] = {
    if (1 == longitud(lista)) {
      return Nil
    }

    lista match {
      case Nil => Nil
      case Cons(cabeza, cola) => Cons(cabeza, eliminarUltimo(cola))
    }
  }

  /**
   * foldLeft con recursividad tipo tail. Funcion de utilidad para aplicar una
   * funcion de forma sucesiva a los elementos de la lista con asociatividad por la izquierda
   *
   * @param lista Lista con la que trabajar
   * @param neutro Elemento neutro
   * @param funcion Funcion a aplicar
   * @tparam A Parametros de tipo de elementos de la lista
   * @tparam B Parametro de tipo del elemento neutro
   */
  @annotation.tailrec
  def foldLeft[A, B](lista : Lista[A], neutro: B)(funcion : (B, A) => B): B = {
    lista match {
      case Nil => neutro
      case Cons(cabeza, cola) => foldLeft(cola, funcion(neutro, cabeza))(funcion)
    }
  }

}