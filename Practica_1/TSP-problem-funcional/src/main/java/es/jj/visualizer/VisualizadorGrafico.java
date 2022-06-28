package es.jj.visualizer;

import es.jj.models.MapaTSP;
import es.jj.models.Ruta;

public class VisualizadorGrafico implements Visualizador{
   /**
    * dato miembro para acceder a la ventana de visualizacion
    */
   private JFXVisualizador ventana;

   public VisualizadorGrafico(){
      ventana = new JFXVisualizador();
   }

   @Override
   public void mostrar(MapaTSP mapa) {
      System.out.println("Visualizacion del mapa en modo grafico");
      ventana.asignar(mapa);
      ventana.mostrar();
   }

   @Override
   public void mostrar(String nombre, Ruta ruta) {
      System.out.println("Visualizacion de ruta optima");
      ventana.asignar(nombre, ruta);
      ventana.mostrar();
   }
}
