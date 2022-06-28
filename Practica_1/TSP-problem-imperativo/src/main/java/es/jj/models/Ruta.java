package es.jj.models;
import java.util.ArrayList;

/**
 * clase para almacenar recorridos de ciudades
 */
public class Ruta {
   /**
    * dato miembro para almacenar las ciudades recorridas
    */
   private ArrayList<Punto> secuencia;

   /**
    * dato miembro para almacenar el coste asociado al recorrido
    * hecho
    */
   private double coste;

   /**
    * constructor por defecto de la clase ruta
    */
   public Ruta(){
      // se inicializa array vacio
      secuencia = new ArrayList<>();
      // se indica que el coste es 0
      coste = 0;
   }

   /**
    * agrega punto nuevo a la ruta, sin hacer comprobacion
    * alguna
    * @param punto punto a agregar
    * @param coste nuevo coste al añadir el punto anterior
    */
   public void agregar(Punto punto, double coste){
      secuencia.add(punto);
      this.coste += coste;
   }

   /**
    * determina si la ruta contiene al punto pasado
    * como argumento
    * @param punto
    * @return
    */
   public boolean contiene(Punto punto){
      return secuencia.contains(punto);
   }

   /**
    * devuelve la longitud de la secuencia, es decir,
    * el numero de puntos que contiene
    * @return
    */
   public int calcularLongitud(){
      return secuencia.size();
   }

   /**
    * devuelve el punto que ocupa una determinada posicion
    * @param indice
    * @return
    */
   public Punto obtenerPunto(int indice){
      return secuencia.get(indice);
   }

   /**
    * metodo para asignar el coste de la ruta
    * @param coste
    */
   public void agregarCoste(double coste){
      this.coste += coste;
   }

   /**
    * metodo de acceso al coste
    */
   public double obtenerCoste(){
      return coste;
   }

   /**
    * metodo toString
    * @return
    */
   public String toString(){
      String salida = "Secuencia: ";
      for(int i=0; i < secuencia.size(); i++){
         Punto punto = secuencia.get(i);
         salida += punto.toString();
         if(i != secuencia.size()-1){
            salida += " - ";
         }
      }
      salida += "\nCoste asociado: " + coste +"\n";

      // se devuelve la cadena
      return salida;
   }

   /**
    * Obtiene la longitud de la ruta
    * @return longitud de la ruta
    */
   public int obtenerLongitud() {
      return this.secuencia.size();
   }

   /**
    * Obtiene el punto de inicio de la ruta
    * @return punto de inicio de la ruta
    */
   public Punto obtenerInicio() {
      return this.secuencia.get(0);
   }

   /**
    * Obtiene el punto de fin de la ruta
    * @return punto de fin de la ruta
    */
   public Punto obtenerFin() {
      return this.secuencia.get(this.secuencia.size() - 1);
   }

   public void quitarFin() {
      this.secuencia.remove(this.secuencia.size() - 1);
   }

   public void quitarCoste(double calcularDistancia) {
      this.coste -= calcularDistancia;
   }
}
