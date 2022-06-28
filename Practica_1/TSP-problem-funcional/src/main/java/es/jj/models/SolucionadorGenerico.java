package es.jj.models;

import es.jj.computes.*;
import es.jj.visualizer.VisualizadorGrafico;
import es.jj.visualizer.VisualizadorTexto;

/**
 * No es preciso crear una clase especifica para cada combinacion
 * de visualizacion - distancia - heuristica. Basta con disponer
 * de un mecanismo claro de construccion de objetos. En este caso
 * se usa el patron metodo-factoria, que recibe como argumentos el
 * nombre del fichero y enumerados que indican la forma en que
 * se quiere hacer cada funcion
 */
public class SolucionadorGenerico extends MapaTSP {

   /**
    * Creacion de objeto a partir de nombre de archivo
    *
    * @param nombreArchivo
    */
   public SolucionadorGenerico(String nombreArchivo) {
      super(nombreArchivo);
   }

   /**
    * Metodo que implementa el patron factoria para crear los
    * objetos con las caracteristicas adecuadas
    * @param nombreArchivo nombre del archivo
    * @param modoVista modo de la vista
    * @param modoDistancia modo de calculo de la distancia
    * @param modoHeuristica modo de la heuristica
    * @param modo2opt de la heuristica inicial para 2OPT
    * @return MapaTSP
    */
   public static MapaTSP factoria(String nombreArchivo, ModoVista modoVista, ModoDistancia modoDistancia,
                                  ModoHeuritica modoHeuristica, ModoHeuInicial2OPT modo2opt){
      MapaTSP objeto = new SolucionadorGenerico(nombreArchivo);

      // se procesa el modo de visualizacion deseado
      switch (modoVista){
         case TEXTO -> objeto.visualizador = new VisualizadorTexto();
         case GRAFICO -> objeto.visualizador = new VisualizadorGrafico();
      }

      // se considera la forma de calculo de distancia
      switch (modoDistancia){
         case EUCLIDEA -> objeto.calculadorDistancia = new DistanciaEuclidea();
         case MANHATTAN -> objeto.calculadorDistancia = new DistanciaManhattan();
      }

      HeuristicaTSP heuristicaInicial2OPT = null;
      switch (modo2opt) {
         case MC -> heuristicaInicial2OPT = new HeuristicaMC(objeto);
         case VMC -> heuristicaInicial2OPT = new HeuristicaVMC(objeto);
      }

      // se procesa la heuristica a considerar
      switch (modoHeuristica) {
         case MC -> objeto.heuristica = new HeuristicaMC(objeto);
         case VMC -> objeto.heuristica = new HeuristicaVMC(objeto);
         case OPT2 -> objeto.heuristica = new Heuristica2OPT(objeto, heuristicaInicial2OPT);
      }


      // se devuelve el objeto una vez configurado
      return objeto;
   }

   /**
    * Metodo que implementa el patron factoria para crear los
    * objetos con las caracteristicas adecuadas
    * @param nombreArchivo nombre del archivo
    * @param modoVista modo de la vista
    * @param modoDistancia modo de calculo de la distancia
    * @param modoHeuristica modo de la heuristica
    * @return MapaTSP
    */
   public static MapaTSP factoria(String nombreArchivo, ModoVista modoVista, ModoDistancia modoDistancia,
                                  ModoHeuritica modoHeuristica) {
      return SolucionadorGenerico.factoria(
              nombreArchivo,
              modoVista,
              modoDistancia,
              modoHeuristica,
              ModoHeuInicial2OPT.MC // Si no se indica se utiliza MC
      );
   }
}
