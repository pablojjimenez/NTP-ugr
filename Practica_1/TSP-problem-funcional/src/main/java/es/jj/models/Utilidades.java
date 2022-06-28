package es.jj.models;

import es.jj.models.Punto;

/**
 * clase general de utilidades
 */
public class Utilidades {
   /**
    * calculo de la distancia euclidea entre dos puntos
    * @param punto1
    * @param punto2
    * @return
    */
   public static double calcularDistanciaEuclidea(Punto punto1, Punto punto2){
      return Math.sqrt(Math.pow(punto1.getX() - punto2.getX(), 2) +
                       Math.pow(punto1.getY() - punto2.getY(), 2));
   }

   /**
    * calculo de la distancia de manhattan entre dos puntos
    * @param punto1
    * @param punto2
    * @return
    */
   public static double calcularDistanciaManhattan(Punto punto1, Punto punto2){
      return Math.abs(punto1.getX() - punto2.getX()) +
             Math.abs(punto1.getY() - punto2.getY());
   }
}
