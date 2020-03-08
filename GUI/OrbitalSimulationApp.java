import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrbitalSimulationApp extends Application{

	private int n;
	private int l;
	private int m;
	
	private LineChart<Number, Number> lineChart;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Orbital Simulation");
		
		VBox pane = new VBox();
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
		
		n = 1;	l = 0; m = 0;
		
		HBox n_pane = new HBox();
		Slider n_slider = new Slider(1, 4, n);
		n_slider.setValue(n);
		n_slider.setMajorTickUnit(1);
		n_slider.setShowTickLabels(true);
		n_slider.setShowTickMarks(true);
		n_slider.setMinorTickCount(0);
		n_slider.valueProperty().addListener((obs, oldval, newVal) ->
	    n_slider.setValue(Math.round(newVal.doubleValue())));
		n_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				n = newValue.intValue();
				try{setChart();}catch(IOException e){System.exit(0);}
			}
		});
		n_slider.setMaxWidth(200);
		Text n_text = new Text("n: ");
		n_pane.getChildren().addAll(n_text, n_slider);
		n_pane.setPadding(new Insets(5, 5, 0, 5));
		
		HBox l_pane = new HBox();
		Slider l_slider = new Slider(0, n-1, l);
		l_slider.setValue(l);
		l_slider.setMajorTickUnit(1);
		l_slider.setShowTickLabels(true);
		l_slider.setShowTickMarks(true);
		l_slider.setMinorTickCount(0);
		l_slider.valueProperty().addListener((obs, oldval, newVal) ->
		l_slider.setValue(Math.round(newVal.doubleValue())));
		l_slider.setMaxWidth(200);
		Text l_text = new Text("l : ");
		l_pane.getChildren().addAll(l_text, l_slider);
		l_pane.setPadding(new Insets(5, 5, 0, 5));
		
		HBox m_pane = new HBox();
		Slider m_slider = new Slider(-l, l, m);
		m_slider.setValue(m);
		m_slider.setMajorTickUnit(1);
		m_slider.setShowTickLabels(true);
		m_slider.setShowTickMarks(true);
		m_slider.setMinorTickCount(0);
		m_slider.valueProperty().addListener((obs, oldval, newVal) ->
		m_slider.setValue(Math.round(newVal.doubleValue())));
		m_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				m = newValue.intValue();
				try{setChart();}catch(IOException e){System.exit(0);}
			}
		});
		m_slider.setMaxWidth(200);
		Text m_text = new Text("m: ");
		m_pane.getChildren().addAll(m_text, m_slider);
		m_pane.setPadding(new Insets(5, 5, 0, 5));
		
		n_slider.valueProperty().addListener((obs, oldval, newVal) ->
		l_slider.setMax(newVal.intValue()-1));
		
		l_slider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				l = newValue.intValue();
				m_slider.setMin(-newValue.intValue());
				m_slider.setMax(newValue.intValue());
				m_slider.setValue(0);
				try{setChart();}catch(IOException e){System.exit(0);}
			}
		});
		
		pane.getChildren().addAll(n_pane, l_pane, m_pane);
		
		final NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Probability Density");
	    final NumberAxis xAxis = new NumberAxis();
	    xAxis.setLabel("Radius(r)");
	    
	    lineChart = new LineChart<Number, Number>(
	        xAxis, yAxis);
		
		setChart();
	    
	    pane.getChildren().add(lineChart);
	    
	    stage.setMinHeight(800);
	    stage.setMinWidth(800);
		
	}
	
	private void setChart()throws IOException{
		
		lineChart.getData().clear();
		
	    lineChart.setTitle("Probability Density |n = "+n+", l = "+l+", m = "+m+" >");
	    XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
	    
	    double a = 0.529177*Math.pow(10, -10);
	    
	    for(double i = Math.pow(10, -11); i<= Math.pow(10, -9); i+=Math.pow(10, -11)){
	    
	    	double num = ProbabilityDensity.probability(n, l, m, 1, i, Math.PI/2, 0);
	    	series.getData().add(new XYChart.Data<Number, Number>(i, num));
		
	    }
	    
	    lineChart.getData().add(series);
	    
	    lineChart.setLegendVisible(false);
	    lineChart.setPadding(new Insets(20, 20, 20, 20));
		
	    lineChart.setMaxHeight(600);
	    lineChart.setMaxWidth(600);
	    
	}
	
	public void launchApp(String[] args){
		launch(args);
	}
	
}
