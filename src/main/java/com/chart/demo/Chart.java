package com.chart.demo;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

public class Chart {


    public static void createDummyData(XYSeriesCollection intervalDataset){

        XYSeries series1 = new XYSeries("series1");
        XYSeries series2 = new XYSeries("series2");
        XYSeries series3 = new XYSeries("series3");
        XYSeries series4 = new XYSeries("series4");
        XYSeries series5 = new XYSeries("series5");

        series1.add(6, 10);
        series1.add(7, -11);
        series1.add(10, 11);
        series1.add(17, 17);
        series1.add(21, 15);

        series2.add(10, 18);
        series2.add(7, -10);
        series2.add(10, 14);
        series2.add(17, 12);
        series2.add(21, -6);

        series3.add(6, 12);
        series3.add(7, 13);
        series3.add(10, 14);
        series3.add(17, 16);
        series3.add(21, -9);

        series4.add(6, 8);
        series4.add(7, 22);
        series4.add(10, -21);
        series4.add(17, 26);
        series4.add(21, 24);

        series5.add(6, 14);
        series5.add(7, 11);
        series5.add(10, 31);
        series5.add(17, 12);
        series5.add(21, 11);

        intervalDataset.addSeries(series1);
        intervalDataset.addSeries(series2);
        intervalDataset.addSeries(series3);
        intervalDataset.addSeries(series4);
        intervalDataset.addSeries(series5);
        int size = intervalDataset.getSeries().size();
        System.out.println("No. of series:: " + size);
        for (int i = 0; i < size; i++) {
            System.out.println("Series["+i+"]:: " + intervalDataset.getItemCount(i));
        }
    }


    public static JFreeChart createClusteredChart(String title, String categoryAxisLabel, String valueAxisLabel, XYSeriesCollection dataset) {

        NumberAxis domainAxis = new NumberAxis(categoryAxisLabel);
        domainAxis.setAutoRangeIncludesZero(false);

        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        XYBarRenderer renderer = new ClusteredXYBarRenderer();
//        renderer.setMargin(.9);
        Double margin = renderer.getMargin();
        System.out.println("Margin:: " + margin);
        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(PlotOrientation.VERTICAL);

//        ValueAxis xAxis = plot.getDomainAxis(0);
//        ValueAxis yAxis = plot.getRangeAxis(0);
//        if (dataset.getSeries().size() == 1 && dataset.getItemCount(0) == 1) {
//            xAxis.setLowerBound(xAxis.getLowerBound() - 10);
//            xAxis.setUpperBound(xAxis.getUpperBound() + 10);
//        }
//        System.out.println("xAxis[L]:: " + xAxis.getLowerBound());
//        System.out.println("xAxis[U]:: " + xAxis.getUpperBound());
//        System.out.println("yAxis[L]:: " + yAxis.getLowerBound());
//        System.out.println("yAxis[U]:: " + yAxis.getUpperBound());

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        return chart;
    }

    public static void main(String[] args) {

        ClusteredXYBarRenderer clusteredXYBarRenderer = new ClusteredXYBarRenderer();
        XYSeriesCollection intervalXYDataSet  = new XYSeriesCollection();
        createDummyData(intervalXYDataSet);
        JFreeChart chart = createClusteredChart(
                "Demo Chart",
                "X-Axis",
                "Y-Axis",
                intervalXYDataSet);

        File file = new File("src/main/resources/data/output/chart.png");
        try {
            ChartUtils.saveChartAsPNG(file, chart, 1200, 800);
            System.out.println("SUCCESS");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }

}
