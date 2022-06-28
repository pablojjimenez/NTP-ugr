package es.jj;

import es.jj.models.*;

/**
 * Clase ProblemaPrueba para probar la funcionalidad de
 * creacion de objetos de la clase Problema mediante la
 * lectura de archivo de datos
 */
public class Principal {
   public static void main(String[] args){
      // se prueba con un archivo pequeño, de 10 ciudades
      String nombreArchivo = "/Users/pablojj/projects/NTP-ugr/Practica_1/TSP-problem-funcional/src/main/java/es/jj" +
              "/data/zi929.tsp";

      // se crea el objeto mediante el metodo factoria
      MapaTSP objeto = SolucionadorGenerico.factoria(
              nombreArchivo,
              ModoVista.GRAFICO,
              ModoDistancia.EUCLIDEA,
              ModoHeuritica.VMC
      );

      // se llama al metodo de resolucion
      objeto.resolver();

      // se llama al metodo mostrar, para ver la ruta optima
      //objeto.mostrarMapa();

      objeto.mostrarRuta();
   }
}
