package es.jj.computes;

import es.jj.models.Utilidades;
import es.jj.models.Punto;

/**
 * clase para aportar la funcionalidad de calculo de distancia
 * euclidea
 */
public class DistanciaEuclidea implements Distancia{
   /**
    * metodo de calculo de la distancia euclidea
    * @return
    */
   public double calcular(Punto origen, Punto destino) {
      return Utilidades.calcularDistanciaEuclidea(origen, destino);
   }
}
