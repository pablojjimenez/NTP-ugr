package es.jj.visualizer;

import es.jj.models.MapaTSP;
import es.jj.models.Ruta;

/**
 * interfaz para visualizacion de problemas y rutas de
 * TSP
 */
public interface Visualizador {
   /**
    * metodo de visualizacion del mapa completo, con todos
    * sus puntos
    * @param mapa
    */
   public void mostrar(MapaTSP mapa);

   /**
    * metodo de visualizacion de la ruta obtenida como
    * solucion al problema
    */
   public void mostrar(String nombre, Ruta ruta);
}
