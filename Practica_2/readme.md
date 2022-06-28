# P2: programación funcional en Scala - recursisvidad

<aside>
📇 Índice de contenido

</aside>

## 1. Integración con el entorno de desarrollo

Se ha utilizado **IntelliJ IDEA** con el plgin de Scala para que reconozca la sintaxis de este lenguaje. Adjunto el árbol de directorios generado:

```
.
└── trianguloPascal
    ├── build.sbt
    ├── project
    │   ├── build.properties
    │   ├── plugins.sbt
    │   └── project
    ├── readme.md
    └── src
        ├── main
        │   └── scala
        │       ├── BusquedasBenchmark.scala
        │       └── Funciones.scala
        └── test
            └── scala
                └── TestFunciones.scala

8 directories, 7 files
```

Y se ha generado el ZIP utilizando el menú del propio IDE: `File > Export > Project to ZIP File…`

## 2. Valoración de la práctica

Desde que aprendí recursividad hace ya 4 años siempre he sido muy fan de intentar hacer así las funciones siempre que pueda. Es cierto, que algunas veces es algo más difícil y considero que siempre es bueno *debuggear* una función recursiva para asegurarte y ser capaz de encontrar fallos o condiciones no contempladas.

Respecto a Scala, cuanto más se, más me gusta. He estado investigando sobre como actúa el compilador al escribir la anotación `@tailrec` y me parece una herramienta super potente.

Tambien me parece muy pertinente que siempre que se hacen algoritmos de búsqueda se compare su rendimiento con el de otros. Yo he comparado el genérico y el de Fibonacci con el método `find` incluido en Scala y una búsqueda lineal básica. Diría que la búsqueda genérica es la más rápida, por bastante diferencia cuando hay un número de elementos considerable.

<aside>
👨🏻‍💻 Práctica realizada por **Pablo Jiménez** 

<a href="mailto:pablojjimenez0@gmail.com"><img src="https://img.shields.io/badge/pablojjimenez0@gmail.com-c14438?style=flat&logo=Gmail&logoColor=white&link=mailto:pablojjimenez0@gmail.com"></a>
</aside>