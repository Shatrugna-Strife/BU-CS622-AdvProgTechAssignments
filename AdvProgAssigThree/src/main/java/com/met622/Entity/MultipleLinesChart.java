package com.met622.Entity;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Jfree chart based graph plotting class
 */
public class MultipleLinesChart extends JFrame {

    public MultipleLinesChart(String title, List<Integer> x, List<Double> y1, List<Double> y2) {
        super("Search methods compare");

        JPanel chartPanel = createChartPanel(title,x,y1,y2);
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * returns chart object
     * @param title
     * @param x
     * @param y1 lucene reader data list
     * @param y2 brute force data list
     * @return
     */
    private JPanel createChartPanel( String title, List<Integer> x, List<Double> y1, List<Double> y2) {
        String chartTitle = "Keyword: "+title;
        String xAxisLabel = "Number of Articles";
        String yAxisLabel = "Time Taken(s)";

        XYDataset dataset = createDataset( x, y1,  y2);

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset, PlotOrientation.VERTICAL, false, false, false);

        customizeChart(chart);

        return new ChartPanel(chart);
    }

    /**
     * data et object creation
     * @param x
     * @param y1
     * @param y2
     * @return
     */
    private XYDataset createDataset(List<Integer> x, List<Double> y1, List<Double> y2) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Lucene Reader");
        XYSeries series2 = new XYSeries("Brute Force");

        for(int i =0;i<x.size();i++){
            series1.add(x.get(i), y1.get(i));
        }
        for(int i =0;i<x.size();i++){
            series2.add(x.get(i), y2.get(i));
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    /**
     * chart coloring and customization
     * @param chart
     */
    private void customizeChart(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);

        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
        renderer.setSeriesStroke(1, new BasicStroke(4.0f));

        plot.setOutlinePaint(Color.WHITE);
        plot.setOutlineStroke(new BasicStroke(2.0f));

        plot.setRenderer(renderer);

        plot.setBackgroundPaint(Color.DARK_GRAY);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

    }
}