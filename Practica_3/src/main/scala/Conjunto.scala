/**
 * Clase que define el objeto Conjunto
 *
 * @param f dato miembro que define al conjunto
 */
class Conjunto(val f: Int => Boolean) {

  /**
   * Metodo que comprueba si un entero pertenece al conjunto
   *
   * @param x elemento a comprobar
   * @return true si pertenece
   */
  def apply(x: Int) = f(x)


  /**
   * Metodo toString, devuelve todos los elementos que pertenecen
   * al conjunto y estan dentro del rango -LIMITE LIMITE
   *
   * @return representación del Conjunto
   */
  override def toString: String = {
    (-Conjunto.LIMITE to Conjunto.LIMITE)
      .filter(this.f)
      .map(e => e.toString)
      .mkString(", ")
  }

  /**
   * Metodo que une un conjunto con otro
   *
   * @param unConjunto nuevo conjunto a unir
   * @return la unión de conjuntos
   */
  def union(unConjunto: Conjunto): Conjunto = new Conjunto(x => f(x) || unConjunto(x))

  /**
   * Metodo que realiza la intersección del conjunto con otro.
   * (elementos comunes)
   *
   * @param unConjunto nuevo conjunto a diferenciar
   * @return la intersección de los conjuntos
   */
  def interseccion(unConjunto: Conjunto): Conjunto = new Conjunto(x => f(x) && unConjunto(x))

  /**
   * Método que calcula la diferencia dado un conjunto
   *
   * @param unConjunto conjunto a diferenciar
   * @return Conjunto diferenciado
   */
  def diferencia(unConjunto: Conjunto): Conjunto = new Conjunto(x => f(x) && !unConjunto(x))

  /**
   * Metodo que devuelve un conjunto formado por los elementos del conjunto
   * que cumplen el parámetro
   *
   * @param cond Condición a cumplir
   * @return conjunto que cumple la condición param
   */
  def filtrar(cond: Int => Boolean): Conjunto = new Conjunto(x => f(x) && cond(x))

  /**
   * Comprueba si el predicado pasado como parámetro se cumple
   * para todos los elementos del Conjunto
   *
   * @param predicado Predicado a cumplir
   * @return true si se cumple, false en caso contrario
   */
  def paraTodo(predicado: (Int => Boolean)) = {
    @annotation.tailrec
    def _do(elemento: Int): Boolean = {
      if (elemento == Conjunto.LIMITE + 1) true
      else if (!f(elemento)) _do(elemento + 1)
      else predicado(elemento) && _do(elemento + 1)
    }

    _do(-Conjunto.LIMITE)
  }

  /**
   * Comprueba si un conjunto tiene al menos un elemento para el
   * que se cumple un el predicado pasado como param
   *
   * @param predicado predicado que se debe cumplir
   * @return true si se cumple alguna vez, false en caso contrario
   */
  def existe(predicado: (Int => Boolean)) = {
    def predicado2(x: Int) = !predicado(x)
    !paraTodo(predicado2)
  }

  /**
   * Transforma un conjunto en otro aplicando una cierta funcion
   *
   * @param fAplicar que se aplica
   * @return El nuevo conjunto con el param aplicado
   */
  def map(fAplicar: Int => Int): Conjunto = new Conjunto(y => existe((x: Int) => fAplicar(x) == y))
}

object Conjunto {
  val LIMITE = 20

  /**
   * Método que crea un conjunto dado por un único
   * elemento
   *
   * @param elementoUnico el elemento único del conjunto
   * @return el conjunto generado
   */
  def conjuntoUnElemento(elementoUnico: Int): Conjunto = new Conjunto(x => x == elementoUnico)
}