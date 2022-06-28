package es.jj.computes;

import es.jj.models.MapaTSP;
import es.jj.models.Punto;
import es.jj.models.Ruta;

import java.util.Random;

public class Heuristica2OPT implements HeuristicaTSP {
    /**
     * referencia al problema a resolver
     */
    private MapaTSP mapa;

    private HeuristicaTSP heuristica1;

    /**
     * constructor de la clase
     * @param mapa mapa del problema
     * @param heuristica1 tsp
     */
    public Heuristica2OPT(MapaTSP mapa, HeuristicaTSP heuristica1) {
        this.mapa = mapa;
        this.heuristica1 = heuristica1;
    }

    @Override
    public Ruta resolver() {
        System.out.println("resolucion mediante Heuristica2OPT");

        Ruta rutaOptima1 = this.heuristica1.resolver();

        return heuristica2OTP(rutaOptima1);
    }


    /**
     * Heuristica 2OPT
     * @return ruta generada
     */
    private Ruta heuristica2OTP(Ruta solucionActual) {
        int k = new Random().nextInt(1, this.mapa.obtenerDimension());
        Ruta nuevaSolucion;

        for(int i=0; i<=k; i++) {
            for (int j=i+1; j<=k; j++) {
                nuevaSolucion = swaopt(solucionActual, i, j);
                if (nuevaSolucion.obtenerCoste() < solucionActual.obtenerCoste()) {
                    solucionActual = nuevaSolucion;
                }
            }
        }
        return solucionActual;
    }

    /**
     * Pasos del algoritmo:
     * 1. Se crea ruta vacia.
     * 2. Los puntos comprendidos entre las posiciones 0 e inicio-1 se copian directamente
     *    en la ruta nueva.
     * 3. Los puntos comprendidos entre inicio y fin (inclusive) se copian en la ruta nueva,
     *    pero en orden inverso.
     * 4. Los puntos a partir de fin+1 se incluyen tal cual en la ruta.
     * 5. Se rellena la ruta recordando que debe volverse a la ciudad inicial.
     * 6. Se devuelve la ruta creada de esta forma.
     *
     * @param ruta ruta a medias
     * @param inicio indice inicio
     * @param fin indice final
     * @return una nueva ruta supuestamente mejorada
     */
    private Ruta swaopt(Ruta ruta, int inicio, int fin) {
        Ruta rutaNueva = new Ruta();
        // paso 2 [0, inicio)
        for(int i=0; i<inicio; i++) {
            if(!rutaNueva.contiene(ruta.obtenerPunto(i))) {
                calcularCosteYAgregar(rutaNueva, ruta.obtenerPunto(i));
            }
        }

        // paso 3 [inicio, fin]
        for (int i=fin; i>=inicio; i--) {
            if(!rutaNueva.contiene(ruta.obtenerPunto(i))) {
                calcularCosteYAgregar(rutaNueva, ruta.obtenerPunto(i));
            }
        }

        // paso 4
        for(int i=fin+1; i<ruta.obtenerLongitud(); i++) {
            if(!rutaNueva.contiene(ruta.obtenerPunto(i))) {
                calcularCosteYAgregar(rutaNueva, ruta.obtenerPunto(i));
            }
        }

        // paso 5
        rutaNueva.agregar(
                rutaNueva.obtenerInicio(),
                this.mapa.calcularDistancia(rutaNueva.obtenerFin(), rutaNueva.obtenerInicio())
        );

        return rutaNueva;
    }

    /**
     * Calcula el coste que tiene añadir un nuevo punto a la rutaNueva y
     * lo añade.
     * @param rutaNueva ruta que se está generando
     * @param p Punto nuevo a añadir
     */
    private void calcularCosteYAgregar(Ruta rutaNueva, Punto p) {
        double coste = rutaNueva.obtenerLongitud() == 0
                ? 0
                : this.mapa.calcularDistancia(rutaNueva.obtenerFin(), p);
        rutaNueva.agregar(p, coste);
    }
}
