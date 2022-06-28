package es.jj.computes;

import es.jj.models.Utilidades;
import es.jj.models.Punto;

/**
 * clase para claculo de distancia de manhattan
 */
public class DistanciaManhattan implements Distancia{
   /**
    * metodo de calculo de la distancia de Manhattan
    * @return
    */
   public double calcular(Punto origen, Punto destino) {
      return Utilidades.calcularDistanciaManhattan(origen, destino);
   }
}
