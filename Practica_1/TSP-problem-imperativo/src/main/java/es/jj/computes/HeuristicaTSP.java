package es.jj.computes;

import es.jj.models.Ruta;

import java.util.List;

/**
 * interfaz para el metodo de resolucion del problema
 * mediante alguna heuristica
 */
public interface HeuristicaTSP {
   /**
    * metodo de resolucion
    * @return
    */
   Ruta resolver();

   /**
    * Obtiene la Ruta de menor coste dado un conjunto de rutas
    * @param rutas: conjunto de rutas
    * @return La ruta de menor coste
    */
   default Ruta seleccionarRutaOptima(List<Ruta> rutas) {
      Ruta rutaMinima = rutas.get(0);
      for (Ruta ruta : rutas) {
         if(ruta.obtenerCoste() < rutaMinima.obtenerCoste()) {
            rutaMinima = ruta;
         }
      }

      return rutaMinima;
   }
}
