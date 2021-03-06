# P2: programaci贸n funcional en Scala - recursisvidad


> 馃搰 脥ndice de contenido
> - [P2: programaci贸n funcional en Scala - recursisvidad](#p2--programaci-n-funcional-en-scala---recursisvidad)
>  * [1. Integraci贸n con el entorno de desarrollo](#1-integraci-n-con-el-entorno-de-desarrollo)
>  * [2. Valoraci贸n de la pr谩ctica](#2-valoraci-n-de-la-pr-ctica)


## 1. Integraci贸n con el entorno de desarrollo

Se ha utilizado **IntelliJ IDEA** con el plgin de Scala para que reconozca la sintaxis de este lenguaje. Adjunto el 谩rbol de directorios generado:

```
.
鈹斺攢鈹? trianguloPascal
    鈹溾攢鈹? build.sbt
    鈹溾攢鈹? project
    鈹偮犅? 鈹溾攢鈹? build.properties
    鈹偮犅? 鈹溾攢鈹? plugins.sbt
    鈹偮犅? 鈹斺攢鈹? project
    鈹溾攢鈹? readme.md
    鈹斺攢鈹? src
        鈹溾攢鈹? main
        鈹偮犅? 鈹斺攢鈹? scala
        鈹偮犅?     鈹溾攢鈹? BusquedasBenchmark.scala
        鈹偮犅?     鈹斺攢鈹? Funciones.scala
        鈹斺攢鈹? test
            鈹斺攢鈹? scala
                鈹斺攢鈹? TestFunciones.scala

8 directories, 7 files
```

Y se ha generado el ZIP utilizando el men煤 del propio IDE: `File > Export > Project to ZIP File鈥

## 2. Valoraci贸n de la pr谩ctica

Desde que aprend铆 recursividad hace ya 4 a帽os siempre he sido muy fan de intentar hacer as铆 las funciones siempre que pueda. Es cierto, que algunas veces es algo m谩s dif铆cil y considero que siempre es bueno *debuggear* una funci贸n recursiva para asegurarte y ser capaz de encontrar fallos o condiciones no contempladas.

Respecto a Scala, cuanto m谩s se, m谩s me gusta. He estado investigando sobre como act煤a el compilador al escribir la anotaci贸n `@tailrec` y me parece una herramienta super potente.

Tambien me parece muy pertinente que siempre que se hacen algoritmos de b煤squeda se compare su rendimiento con el de otros. Yo he comparado el gen茅rico y el de Fibonacci con el m茅todo `find` incluido en Scala y una b煤squeda lineal b谩sica. Dir铆a que la b煤squeda gen茅rica es la m谩s r谩pida, por bastante diferencia cuando hay un n煤mero de elementos considerable.


> 馃懆馃徎鈥嶐煉? Pr谩ctica realizada por **Pablo Jim茅nez** <a href="mailto:pablojjimenez0@gmail.com"><img src="https://img.shields.io/badge/pablojjimenez0@gmail.com-c14438?style=flat&logo=Gmail&logoColor=white&link=mailto:pablojjimenez0@gmail.com"></a>
