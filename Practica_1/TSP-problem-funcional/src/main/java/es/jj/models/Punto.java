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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Punto punto = (Punto) o;

      if (Double.compare(punto.x, x) != 0) return false;
      if (Double.compare(punto.y, y) != 0) return false;
      return etiqueta.equals(punto.etiqueta);
   }

   @Override
   public int hashCode() {
      int result;
      long temp;
      result = etiqueta.hashCode();
      temp = Double.doubleToLongBits(x);
      result = 31 * result + (int) (temp ^ (temp >>> 32));
      temp = Double.doubleToLongBits(y);
      result = 31 * result + (int) (temp ^ (temp >>> 32));
      return result;
   }

   /**
    * devuelve cadena con contenido del objeto
    */
   public String toString(){
      return etiqueta + "(" + x + ", " + y + ")";
   }

   /**
    * @return etiqueta para identificar al punto
    */
    public String getLabel() {
      return this.etiqueta;
    }
}
