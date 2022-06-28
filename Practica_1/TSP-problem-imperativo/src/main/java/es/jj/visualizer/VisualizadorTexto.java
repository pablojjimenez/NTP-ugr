package es.jj.visualizer;

import es.jj.models.MapaTSP;
import es.jj.models.Ruta;

/**
 * clase para implementar funcion de visualizacion mediante texto
 */
public class VisualizadorTexto implements Visualizador{
   @Override
   public void mostrar(MapaTSP mapa) {
      System.out.println("Visualizacion del mapa en modo texto");
      System.out.println(mapa);
   }

   @Override
   public void mostrar(String nombre, Ruta ruta) {
      System.out.println("Visualizacion de la ruta en modo texto");
      System.out.println(ruta);
   }
}
