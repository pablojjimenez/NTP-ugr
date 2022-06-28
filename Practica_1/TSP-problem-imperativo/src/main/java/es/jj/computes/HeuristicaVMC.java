package es.jj.computes;

import es.jj.models.MapaTSP;
import es.jj.models.Punto;
import es.jj.models.Ruta;

import java.util.ArrayList;
import java.util.List;

/**
 * interfaz para proporcionar el comportamiento de la heuristica
 * del vecino mas cercano
 */
public class HeuristicaVMC implements HeuristicaTSP {
   /**
    * referencia al problema a resolver
    */
   private MapaTSP mapa;

   /**
    * constructor de la clase
    *
    * @param mapa
    */
   public HeuristicaVMC(MapaTSP mapa) {
      // se asigna el mapa
      this.mapa = mapa;
   }

   /**
    * metodo de resolucion con heuristica del vecino
    * mas cercano
    *
    * @return
    */
   public Ruta resolver() {
      System.out.println("Interfaz HeuristicaVMC");
      System.out.println("resolucion mediante vecino mas cercano");

      Ruta elegida = heuristicaVMC();

      System.out.println("Coste = " + elegida.obtenerCoste());

      // se devuelve la ruta de minimo coste
      return elegida;
   }

   /**
    * Heuristica Vecino Mas Cercano
    * PASOS:
    * 1.para cada ciudad, se construye una ruta en que se toma como ciudad inicial, v0
    * 2. mientras queden ciudades por agregar:
    * se agrega la ciudad mas cercana a la anterior (que no haya sido visitada)
    * 3. registrar el coste de la ruta obtenida y actualizar la ruta optima si es necesario
    *
    * @return Ruta optima
    */
   private Ruta heuristicaVMC() {

      List<Ruta> rutas = new ArrayList<>();
      for (int i=0; i<this.mapa.obtenerDimension(); i++) {
         Ruta rutaNueva = new Ruta();
         rutaNueva.agregar(this.mapa.obtenerPunto(i), 0);
         this.completarRuta(rutaNueva);
         rutas.add(rutaNueva);
      }

      // Me quedo con la ruta de menor coste
      Ruta elegida = seleccionarRutaOptima(rutas);

      return elegida;
   }

   /**
    * Completa la ruta que comienza con el punto elegido
    * en el metodo heuristicaVMC()
    * Metodo recursivo, por tanto definimos caso base.
    *
    * @param rutaNueva Ruta a completar
    */
   private void completarRuta(Ruta rutaNueva) {
      if(rutaNueva.obtenerLongitud() == this.mapa.obtenerDimension()) {
         Punto pi = rutaNueva.obtenerInicio();
         Punto pf = rutaNueva.obtenerFin();
         double distancia = this.mapa.calcularDistancia(pi, pf);
         rutaNueva.agregar(pi, distancia);

      } else {
         Punto puntoMasCercano = this.mapa.obtenerPuntoMasCercano(rutaNueva);
         double distancia = this.mapa.calcularDistancia(rutaNueva.obtenerFin(), puntoMasCercano);

         rutaNueva.agregar(puntoMasCercano, distancia);
         completarRuta(rutaNueva);
      }
   }

}
