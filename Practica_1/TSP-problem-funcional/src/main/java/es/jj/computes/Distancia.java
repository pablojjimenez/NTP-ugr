package es.jj.computes;

import es.jj.models.Punto;

/**
 * interfaz para calculo de distancia entre dos puntos
 */
public interface Distancia {
   /**
    * metodo de calculo de distancia
    * @param origen
    * @param destino
    * @return
    */
   double calcular(Punto origen, Punto destino);
}
