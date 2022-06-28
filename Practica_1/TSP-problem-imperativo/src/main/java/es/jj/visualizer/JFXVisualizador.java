package es.jj.visualizer;
import es.jj.models.MapaTSP;
import es.jj.models.Punto;
import es.jj.models.Ruta;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

/**
 * clase para visualizar los datos de un mapa mediante JFX
 */
public class JFXVisualizador extends Application{
   /**
    * datos miembro para almacenar los datos del mapa
    * NOTA: deben ser privados por la forma en que se ejecutan
    * las aplicaciones de java fx
    */
   static private MapaTSP mapa;
   static private Ruta ruta;
   static private String nombre;
   static private int dimension;
   static private boolean mostrarRuta = false;

   /**
    * serie para almacenar los datos
    */
   static private XYChart.Series coordenadas;
   static private XYChart.Series serie1;
   static private XYChart.Series serie2;

   /**
    * metodo para asignar el mapa
    * @param mapa
    */
   public void asignar(MapaTSP mapa){
      // se asigna el mapa
      this.mapa = mapa;

      // se asignan nombre y dimension
      nombre = mapa.obtenerNombre();
      dimension = mapa.obtenerDimension();
   }

   /**
    * metodo para asignar la ruta
    * @param nombre
    * @param ruta
    */
   public void asignar(String nombre, Ruta ruta){
      System.out.println("asignado nombre: " + nombre);
      this.nombre = nombre;
      this.ruta = ruta;
      dimension = ruta.calcularLongitud()-1;

      // se activa el flag para mostrar ruta
      mostrarRuta = true;
   }

   /**
    * desencadena el pintado y dependera de como este
    * el flag de la ruta
    * @param stage
    */
   public void start(Stage stage) {
      System.out.println("llamada a metodo start......");
      stage.setTitle("Mapa para " + nombre);

      // se determina el tipo de grafico a generar para
      // obtener los datos correspondientes
      Pane panel;
      if(mostrarRuta){
         panel = generarGraficosRuta(stage);
      }
      else{
         panel = generarGraficosMapa(stage);
      }

      // se crea la escena
      Scene escena = new Scene(panel);
      panel.minWidthProperty().bind(escena.widthProperty());

      // se agrega la escena
      stage.setScene(escena);

      // mostrar contenido de la ventana
      stage.show();
   }

   /**
    * metodo para visualizacion de la ruta optima
    * @param escenario
    * @return
    */
   private Pane generarGraficosRuta(Stage escenario){
      escenario.setTitle("Mapa para ruta de " + nombre);

      // definicion de ejes
      NumberAxis xAxis = new NumberAxis();
      xAxis.setLabel("X");
      NumberAxis yAxis = new NumberAxis();
      yAxis.setLabel("Y");

      // creacion de graficos de puntos y linea para la ruta
      ScatterChart<String, Number> scatterChart =
              new ScatterChart(xAxis, yAxis);
      scatterChart.setLegendVisible(false);
      scatterChart.setPrefWidth(800);
      scatterChart.setPrefHeight(600);

      LineChart<Number, Number> linea = new LineChart<>(xAxis, yAxis);
      LineChart.SortingPolicy policy = LineChart.SortingPolicy.NONE;
      linea.setAxisSortingPolicy(policy);

      // se crean las series de datos para el grafico
      //coordenadas = new XYChart.Series();
      serie1 = new XYChart.Series();
      serie2 = new XYChart.Series();

      Platform.runLater(() ->
              serie1.getNode().lookup(".chart-series-line").
                      setStyle("-fx-stroke: black;")
      );
      linea.setPrefWidth(800);
      linea.setPrefHeight(600);
      linea.setLegendVisible(false);

      // lectura de datos a partir de la ruta
      for(int i=0; i < ruta.calcularLongitud(); i++){
         Punto punto = ruta.obtenerPunto(i);
         //coordenadas.getData().add(new XYChart.Data(punto.getX(), punto.getY()));
         serie1.getData().add(new XYChart.Data(punto.getX(), punto.getY()));
      }
      Punto inicio = ruta.obtenerPunto(0);
      serie2.getData().add(new XYChart.Data(inicio.getX(), inicio.getY()));
      Punto fin = ruta.obtenerPunto(ruta.calcularLongitud()-2);
      serie2.getData().add(new XYChart.Data(fin.getX(), fin.getY()));

      System.out.println("numero de puntos en la ruta: " + ruta.calcularLongitud());

      // se agregan las coordenadas a los graficos
      //scatterChart.getData().add(coordenadas);
      linea.getData().add(serie1);
      linea.getData().add(serie2);

      // se crea un panel para posicionar los graficos
      Pane pane = new Pane();
      //pane.getChildren().add(scatterChart);
      pane.getChildren().add(linea);

      // se devuelve el panel
      return pane;
   }

   /**
    * mapa para visualizacion de los puntos del mapa
    * @param escenario
    * @return
    */
   private Pane generarGraficosMapa(Stage escenario){
      escenario.setTitle("Mapa para " + nombre);

      // definicion de ejes
      NumberAxis xAxis = new NumberAxis();
      xAxis.setLabel("X");
      NumberAxis yAxis = new NumberAxis();
      yAxis.setLabel("Y");

      // creacion de graficos de puntos y linea para la ruta
      ScatterChart<String, Number> scatterChart =
              new ScatterChart(xAxis, yAxis);
      scatterChart.setLegendVisible(false);
      scatterChart.setPrefWidth(800);
      scatterChart.setPrefHeight(600);

      // se crea la serie de datos para el grafico
      coordenadas = new XYChart.Series();
      System.out.println("numero de puntos para pintar: " + mapa.obtenerDimension());

      // lectura de datos a partir del mapa
      for(int i=0; i < mapa.obtenerDimension(); i++){
         Punto punto = mapa.obtenerPunto(i);
         coordenadas.getData().add(new XYChart.Data(punto.getX(), punto.getY()));
      }

      // se agregan las coordenadas a los graficos
      scatterChart.getData().add(coordenadas);

      // se crea un panel para posicionar los graficos
      Pane pane = new Pane();
      pane.getChildren().add(scatterChart);

      // se devuelve el panel
      return pane;
   }

   /**
    * metodo para invocar la presentacion de la ventana
    */
   public void mostrar() {
      launch(null);
   }
}
