package es.jj.models;
import es.jj.computes.Distancia;
import es.jj.computes.HeuristicaTSP;
import es.jj.visualizer.Visualizador;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * clase para almacenar mapas que se resolveran mediante TSP
 */
public class MapaTSP {
    // nombre del archivo asociado
    private String nombre;

    // array de puntos
    private ArrayList<Punto> puntos;

    // el comportamiento para mostrar se delega en
    // la visualizador correspondiente
    protected Visualizador visualizador;

    // comportamiento para calculo de distancia
    protected Distancia calculadorDistancia;

    // comportamiento para resolver el problema
    protected HeuristicaTSP heuristica;

    // dato miembro para almacenar la solucion del problema
    protected Ruta solucion;

    /**
     * constructor de la clase
     * @param nombreArchivo
     */
    MapaTSP(String nombreArchivo){
        // se asigna el nombre
        this.nombre = nombreArchivo;

        // se crea la coleccion de puntos
        puntos = new ArrayList<>();

        // lectura del archivo
        try {
            leerArchivo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * metodo de acceso al nombre
     * @return
     */
    public String obtenerNombre(){
        return nombre;
    }

    /**
     * devuelve la dimension del problema
     * @return
     */
    public int obtenerDimension(){
        return puntos.size();
    }

    /**
     * devuelve el punto de una determinada posicion
     * @param indice
     * @return
     */
    public Punto obtenerPunto(int indice){
        return puntos.get(indice);
    }

    public List<Punto> obtenerTodosLosPuntos() {
        return (List<Punto>) this.puntos.clone();
    }

    /**
     * metodo mostrar delegando en el visualizador correspondiente
     */
    public void mostrarMapa(){
        // se delega en el visualizador
        visualizador.mostrar(this);
    }

    /**
     * metodo para mostrar la ruta
     */
    public void mostrarRuta(){
        visualizador.mostrar(nombre, solucion);
        System.out.println("Coste: " + solucion.obtenerCoste());
    }

    /**
     * metodo de calculo de distancia
     * @param origen
     * @param destino
     * @return
     */
    public double calcularDistancia(Punto origen, Punto destino){
        return calculadorDistancia.calcular(origen, destino);
    }

    /**
     * metodo de obtencion de la ruta
     * @return
     */
    public Ruta resolver(){
        this.solucion = heuristica.resolver();
        return this.solucion;
    }

    /**
     * metodo privado para leer datos del archivo
     */
    private void leerArchivo() throws IOException {
        Stream<String> lines = Files.lines(Path.of(this.nombre), StandardCharsets.UTF_8);

        Pattern p = Pattern.compile("\\s+");
        lines
                .filter(line -> !line.contains("DIMENSION"))
                .filter(line -> !line.contains("EOF"))
                .filter(line -> !line.trim().equals(""))
                .forEach(line -> {
                    List<String> element = Arrays.asList(p.split(line.trim()));

                    var punto = new Punto(
                            element.get(0),
                            Double.parseDouble(element.get(1)),
                            Double.parseDouble(element.get(2))
                    );
                    this.puntos.add(punto);
                });
    }

    /**
     * Obtiene el Punto mas cercano al ultimo introducido en la ruta
     * solo contempla ciudades no visitadas, esto es no contenidas ya
     * en la ruta.
     *
     * @param rutaNueva Ruta en construcción
     * @return Punto mas cercano al último en la ruta
     */
    public Punto obtenerPuntoMasCercano(Ruta rutaNueva) {
        return this.puntos.stream()
                .filter(punto -> !rutaNueva.contiene(punto))
                .min(Comparator.comparingDouble(punto2
                        -> this.calcularDistancia(rutaNueva.obtenerFin(), punto2))).get();
    }

}
