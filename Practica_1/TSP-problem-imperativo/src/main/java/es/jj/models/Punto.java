package es.jj.models;

/**
 * clase base para representar ubicaciones en un problema
 * del TSP
 */
public class Punto {
   /**
    * dato miembro para almacenar una etiqueta de la ubicacion
    * del punto
    */
   private String etiqueta;

   /**
    * datos miembro para almacenar las coordenadas del punto
    */
   private double x;
   private double y;

   /**
    * constructor de la clase
    *
    * @param etiqueta
    * @param coordx
    * @param coordy
    */
   public Punto(String etiqueta, double coordx, double coordy) {
      this.etiqueta = etiqueta;
      x = coordx;
      y = coordy;
   }

   /**
    * metodo de acceso al dato miembro X
    */
   public double getX(){
      return x;
   }

   /**
    * metodo de acceso al dato miembro Y
    */
   public double getY(){
      return y;
   }

   /**
    * devuelve cadena con contenido del objeto
    */
   public String toString(){
      String salida = etiqueta + "(" + x + ", " + y + ")";
      return salida;
   }
}
