package ntp.pablojj

import org.scalameter.{Key, Warmer, config}

object TiemposFibonacci extends App {

  def busquedaLineal(lista: List[Int], aBuscar: Int): Int = {
    for(i <- 0 to lista.length) {
      if (lista(i) == aBuscar) {
        return i
      }
    }
    -1
  }

  val standardConfig = config(
    Key.exec.maxWarmupRuns := 3,
    Key.exec.maxWarmupRuns := 1,
    Key.exec.benchRuns := 2,
    Key.verbose := true
  ) withWarmer (new Warmer.Default)

  val vector = (1 to 50_000).map(i => i).toList
  val item = 45_000

  val busquedaFibo = standardConfig measure {
    Funciones.busquedaFibonacci[Int](vector, vector(item), _ > _, _ < _)
  }

  val busquedaGenerica = standardConfig measure {
    Funciones.busquedaGenerica[Int](vector, vector(item), _ > _)
  }

  val busquedaScala = standardConfig measure {
    vector.find(x => x == vector(item))
  }

  val busquedaLineal = standardConfig measure {
    vector.find(x => x == vector(item))
  }

  println("tiempo búsqueda Fibonacci:    " + busquedaFibo)
  println("tiempo búsqueda genérica:     " + busquedaGenerica)
  println("tiempo búsqueda Scala find(): " + busquedaScala)
  println("tiempo búsqueda lineal:       " + busquedaLineal)
}
