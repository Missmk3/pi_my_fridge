package pmf.view;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import pmf.model.*;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graphview extends JFrame implements Observer {
	private model model;
	private XYSeries Temperature_InterieureSeries;
	private XYSeries Humidite_InterieureSeries;
	private XYSeries Temperature_ExterieureSeries;
	public Graphview(model model) {
		this.model=model;
		this.model.addObserver(this);
		Temperature_InterieureSeries=new XYSeries("Température intérieure");
		Humidite_InterieureSeries=new XYSeries("Humidité intérieure");
		Temperature_ExterieureSeries=new XYSeries("Température extérieure");
		initUI();
	}
	private void initUI() {
        JFreeChart chart = createChart();
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        setContentPane(chartPanel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);}
	 private JFreeChart createChart() {
	        XYSeriesCollection dataset = new XYSeriesCollection();
	        dataset.addSeries(Temperature_InterieureSeries);
	        dataset.addSeries(Humidite_InterieureSeries);
	        dataset.addSeries(Temperature_ExterieureSeries);

	        JFreeChart chart = ChartFactory.createXYLineChart(
	                "Évolution des Mesures",
	                "Temps",
	                "Valeur",
	                dataset
	        );
	        XYPlot plot = chart.getXYPlot();
	        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
	        yAxis.setAutoRangeIncludesZero(false);

	        return chart;
	       }
	       public void update(Observable o, Object arg) {
	        Temperature_InterieureSeries.addOrUpdate(System.currentTimeMillis(), model.getTemperature_Interieure());
	        Humidite_InterieureSeries.addOrUpdate(System.currentTimeMillis(), model.getHumidite_Interieure());
	        Temperature_ExterieureSeries.addOrUpdate(System.currentTimeMillis(), model.getTemperature_Exterieure());
	    }
	  

}
