# Práctica 1: Programación Funcional en JAVA

- [Práctica 1: Programación Funcional en JAVA](#práctica-1-programación-funcional-en-java)
  - [Entorno de desarrollo](#entorno-de-desarrollo)
  - [Modificaciones realizadas](#modificaciones-realizadas)
    - [Clase Ruta](#clase-ruta)
    - [Interfaz HeuristicaTSP](#interfaz-heuristicatsp)
    - [Clase Punto](#clase-punto)
    - [Clase ParPuntos](#clase-parpuntos)
  - [Valoración de la práctica](#valoración-de-la-práctica)
  
## Entorno de desarrollo
He tenido bastantes problemas para utilizar javaFX con JDK17 en MacOS. Lo he solucionado creando un proyecto vacío de
**IntelliJ IDEA** que es el IDE que he utilizado e incluyendo las dependencias JavaFX utilizando el gestor Maven. Las 
dependencias
relativas a JavaFX están especificadas en el fichero `pom.xml` que se sincronizan y descargan a la hora de ejecutar el proyecto.

Por tanto adjunto todos los ficheros que exporta el IntelliJ IDEA al utilizar `File > Export > Export project to ZIP`

## Modificaciones realizadas
1. Cambio nombre del fichero que contiene el `main` a `Principal.java`
2. Organizo en paquetes el proyecto:
   1. **computes** clases relativas a heuristicas y cálculos.
   2. **models** clases que representan Mapas, Ruta, Punto, Modos
   3. **visualizer** clases para la visualización
3. Hay algunos mapas que el programa no es capaz de resolver debido a que se llena la memoria del _heap_. Tenemos 
   que considerar que cuando se calcula la matriz de distancias se desperdicia mucha memoria (la matriz es simétrica)
   para un problema de 100 puntos se crea una matriz de 100x100 con información redundante. Por ello, he decidido 
   prescindir de esa matriz y calcular cada vez que se necesite la ciudad más cercana en lugar de ir a buscarlo en 
   dicha matriz. 

### Clase Ruta
- añadido método `obtenerLongitud`
- añadido método `obtenerInicio`
- añadido método `obtenerFin` 
- añadido método `asignarCoste` -> `agregarCoste` para mayor comodidad.
- Para la versión funcional he tenido que hacer que la clase realizara la interfaz `Comparator` para utilizar funciones 
  lambdas como `min()`  usando el método `compareTo`

### Interfaz HeuristicaTSP
He añadido un método `default` para calcular la ruta más óptima dado un conjunto de rutas. He decidido
mover este método a la interfaz porque todas las heurísticas requieren realizar esta operación.

### Clase Punto
- Añadido método `get` para la etiqueta.
- Métodos `equals` y `hashcode`para poder utilizar expresiones funcionales con predicados más fáciles.



## Valoración de la práctica
Me ha gustado mucho ver el patrón estrategía implementado y ver como desde capas de mayor abstracción puedes inyectar que tipo de operación quieres realizar.

Creo que está muy ligado con el concepto de inyección de dependencias altamente usado en la mayoría de Frameworks 
actuales. Se ha solucinado también mi duda histórica sobre la herencia *versus* composición.

La programación funcional que ya había descubierto someramente programando en JavaScript me ha parecido una 
herramienta super potente en Java que tiene muchos métodos que nos facilitan tareas cotidianas como obtener 
mínimos/máximos... imagino que seguiremos profundizando en este tema con las próximas prácticas en Scala.