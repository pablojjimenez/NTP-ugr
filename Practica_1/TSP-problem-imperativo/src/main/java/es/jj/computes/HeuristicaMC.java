package es.jj.computes;

import es.jj.models.MapaTSP;
import es.jj.models.Punto;
import es.jj.models.Ruta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * interfaz para proporcionar el comportamiento de la heuristica
 * mediante simulacion Montecarlo
 */
public class HeuristicaMC implements HeuristicaTSP {

   /**
    * Var para
    */
   private static final int REPES_I = 10_000;

   /**
    * Número de iteraciones
    */
   private int iteraciones;

   /**
    * referencia al problema a resolver
    */
   private MapaTSP mapa;

   /**
    * constructor de la clase
    * @param mapa
    */
   public HeuristicaMC(MapaTSP mapa){
      this.mapa = mapa;
      this.iteraciones = REPES_I * this.mapa.obtenerDimension();
   }

   /**
    * metodo de resolucion mediante heuristica montecarlo
    * @return Ruta optima
    */
   public Ruta resolver(){
      System.out.println("Interfaz HeuristicaMC");
      System.out.println("resolucion mediante simulacion Montecarlo");

      Ruta elegida = calcularHeuristica();

      System.out.println("Se ha elegido la ruta con coste: " + elegida.obtenerCoste());
      return elegida;
   }

   /**
    * Se encarga de calcular la heuristica MonteCarlo imperativamente.
    * PASOS:
    * 1. Genero rutas aleatorias, tantas como me indica la varibale iteraciones.
    * 2. Me quedo con la ruta de menor coste.
    * @return Ruta optima.
    */
   private Ruta calcularHeuristica() {
      List<Ruta> rutas = new ArrayList<>();
      for (int i=0; i<this.iteraciones; i++) {
         List<Punto> puntos = this.mapa.obtenerTodosLosPuntos();
         Collections.shuffle(puntos);
         Ruta ruta = new Ruta();
         for (Punto p : puntos) {
            double d = ruta.obtenerLongitud() == 0
                    ? 0
                    : this.mapa.calcularDistancia(ruta.obtenerFin(), p);
            ruta.agregar(p, d);
         }
         // coste 0 porque ya he contemplado arriba la ultima
         double coste = this.mapa.calcularDistancia(ruta.obtenerInicio(), ruta.obtenerFin());
         ruta.agregar(ruta.obtenerInicio(), coste);

         rutas.add(ruta);
      }
      System.out.printf("Rutas generadas = %,d %n", rutas.size());
      return seleccionarRutaOptima(rutas);
   }
}
