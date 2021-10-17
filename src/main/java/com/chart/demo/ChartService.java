package com.chart.demo;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class ChartService {

    public void createDummyData(XYSeriesCollection intervalDataset){

        XYSeries series1 = new XYSeries("series1");
//        XYSeries series2 = new XYSeries("series2");
//        XYSeries series3 = new XYSeries("series3");
//        XYSeries series4 = new XYSeries("series4");
//        XYSeries series5 = new XYSeries("series5");

        series1.add(6, 10);
        series1.add(7, -11);
//        series1.add(10, 11);
//        series1.add(17, 17);
//        series1.add(21, 15);

//        series2.add(10, 18);
//        series2.add(7, -10);
//        series2.add(10, 14);
//        series2.add(17, 12);
//        series2.add(21, -6);
//
//        series3.add(6, 12);
//        series3.add(7, 13);
//        series3.add(10, 14);
//        series3.add(17, 16);
//        series3.add(21, -9);
//
//        series4.add(6, 8);
//        series4.add(7, 22);
//        series4.add(10, -21);
//        series4.add(17, 26);
//        series4.add(21, 24);
//
//        series5.add(6, 14);
//        series5.add(7, 11);
//        series5.add(10, 31);
//        series5.add(17, 12);
//        series5.add(21, 11);

        intervalDataset.addSeries(series1);
//        intervalDataset.addSeries(series2);
//        intervalDataset.addSeries(series3);
//        intervalDataset.addSeries(series4);
//        intervalDataset.addSeries(series5);
        int size = intervalDataset.getSeries().size();
        System.out.println("No. of series:: " + size);
        for (int i = 0; i < size; i++) {
            System.out.println("Series["+i+"]:: " + intervalDataset.getItemCount(i));
        }
    }


    public JFreeChart createClusteredChart(String title, String categoryAxisLabel, String valueAxisLabel, XYSeriesCollection dataset) {

        NumberAxis domainAxis = new NumberAxis(categoryAxisLabel);
        domainAxis.setAutoRangeIncludesZero(false);

        ValueAxis valueAxis = new NumberAxis(valueAxisLabel);

        XYBarRenderer renderer = new ClusteredXYBarRenderer();
        renderer.setMargin(0.9);
        Double margin = renderer.getMargin();
        System.out.println("Margin:: " + margin);
        XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(PlotOrientation.VERTICAL);

        ValueAxis xAxis = plot.getDomainAxis(0);
        ValueAxis yAxis = plot.getRangeAxis(0);
//        if (dataset.getSeries().size() == 1 && dataset.getItemCount(0) == 1) {
//            xAxis.setLowerBound(xAxis.getLowerBound() - 10);
//            xAxis.setUpperBound(xAxis.getUpperBound() + 10);
//        }
        System.out.println("xAxis[L]:: " + xAxis.getLowerBound());
        System.out.println("xAxis[U]:: " + xAxis.getUpperBound());
        System.out.println("yAxis[L]:: " + yAxis.getLowerBound());
        System.out.println("yAxis[U]:: " + yAxis.getUpperBound());

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
//        CategoryPlot categoryPlot = chart.getCategoryPlot();
//        BarRenderer br = (BarRenderer) categoryPlot.getRenderer();ClusteredXYBarRenderer
//        br.setItemMargin(0.3);
        return chart;
    }


    public JFreeChart create2yAxisClusteredChart(String title, String categoryAxisLabel, String valueAxisLabel) {

        XYSeries series1 = new XYSeries("series1");
        XYSeries series2 = new XYSeries("series2");
        series2.add(200, 1000);
        series2.add(300, 1150);
        series2.add(400, 1250);
        series2.add(500, -1250);

        series1.add(1000, 111250);
        series1.add(1150, 211250);
        series1.add(1250, 311250);
        series1.add(1350, -211250);

        double minYValue1 = series1.getMinY();
        double maxYValue1 = series1.getMaxY();
        double minYValue2 = series2.getMinY();
        double maxYValue2 = series2.getMaxY();

        System.out.println("Series 1 [minY]:: " + minYValue1);
        System.out.println("Series 1 [maxY]:: " + maxYValue1);
        System.out.println("Series 2 [minY]:: " + minYValue2);
        System.out.println("Series 2 [maxY]:: " + maxYValue2);



        //create the datasets
        XYSeriesCollection dataset1 = new XYSeriesCollection();
        XYSeriesCollection dataset2 = new XYSeriesCollection();
        dataset1.addSeries(series1);
        dataset2.addSeries(series2);

        //construct the plot
        XYPlot plot = new XYPlot();
        plot.setDataset(0, dataset1);
        plot.setDataset(1, dataset2);



        XYBarRenderer renderer0 = new ClusteredXYBarRenderer();
        XYBarRenderer renderer1 = new ClusteredXYBarRenderer();
        renderer0.setMargin(8);
        renderer1.setMargin(8);
        renderer0.setSeriesFillPaint(0, Color.BLUE);
        renderer1.setSeriesFillPaint(0, Color.RED);
        plot.setRenderer(0, renderer0);
        plot.setRenderer(1, renderer1);
        plot.setOrientation(PlotOrientation.VERTICAL);

        //customize the plot with renderers and axis
        plot.setRangeAxis(0, new NumberAxis("Series 1"));
        plot.setRangeAxis(1, new NumberAxis("Series 2"));
        plot.setDomainAxis(new NumberAxis("X Axis"));

        // https://www.jfree.org/forum/viewtopic.php?t=24697
        if (minYValue1 < 0 || minYValue2 < 0 || maxYValue1 < 0 || maxYValue2 < 0) {
            ValueAxis xAxis = plot.getDomainAxis(0);
            ValueAxis yAxis1 = plot.getRangeAxis(0);
            ValueAxis yAxis2 = plot.getRangeAxis(1);

            System.out.println("x-axis [lb]:: " + xAxis.getLowerBound());
            System.out.println("x-axis [ub]:: " + xAxis.getUpperBound());
            System.out.println("y-axis 1 [lb]:: " + yAxis1.getLowerBound());
            System.out.println("y-axis 1 [ub]:: " + yAxis1.getUpperBound());
            System.out.println("y-axis 2 [lb]:: " + yAxis2.getLowerBound());
            System.out.println("y-axis 2 [ub]:: " + yAxis2.getUpperBound());

            double minAbsValue1 = Math.abs(minYValue1);
            double maxAbsValue1 = Math.abs(maxYValue1);
            double minAbsValue2 = Math.abs(minYValue2);
            double maxAbsValue2 = Math.abs(maxYValue2);

//            double minValue2 = minAbsValue2 > maxAbsValue2 ? -1.25 * minAbsValue2 : -1.25 * maxAbsValue2;
//            double maxValue2 = minAbsValue2 > maxAbsValue2 ? minAbsValue2 : maxAbsValue2;
            double rangeValue1 = minAbsValue1 > maxAbsValue1 ? minAbsValue1 : maxAbsValue1;
            double rangeValue2 = minAbsValue2 > maxAbsValue2 ? minAbsValue2 : maxAbsValue2;

            yAxis1.setLowerBound(-rangeValue1 * 1.05);
            yAxis1.setUpperBound(rangeValue1 * 1.05);
            yAxis1.centerRange(0);

            yAxis2.setLowerBound(-rangeValue2 * 1.05);
            yAxis2.setUpperBound(rangeValue2 * 1.05);
            yAxis2.centerRange(0);

            System.out.println("***** After *****");
            System.out.println("rangeValue:: " + rangeValue2);
            System.out.println("y-axis 1 [lb]:: " + yAxis1.getLowerBound());
            System.out.println("y-axis 1 [ub]:: " + yAxis1.getUpperBound());
            System.out.println("y-axis 2 [lb]:: " + yAxis2.getLowerBound());
            System.out.println("y-axis 2 [ub]:: " + yAxis2.getUpperBound());

//            yAxis2.setAutoRange(true);
            /*
            double abs_axis0lb = Math.abs(yAxis0.getLowerBound());
            double abs_axis1lb = Math.abs(yAxis1.getLowerBound());
            double axis0ub = yAxis0.getUpperBound();
            double axis1ub = yAxis1.getUpperBound();

            double axis0upper = (abs_axis0lb > axis0ub) ? abs_axis0lb : axis0ub;
            double axis1upper = (abs_axis1lb > axis1ub) ? abs_axis1lb : axis1ub;

            yAxis0.setUpperBound(axis0upper);
            yAxis1.setUpperBound(axis1upper);

            // now set the lower range if no negative values set to zero
            if ((yAxis0.getLowerBound() >= 0) && (yAxis1.getLowerBound() >= 0)) {
                yAxis0.setLowerBound(0);
                yAxis1.setLowerBound(0);
            } else {
                yAxis0.setLowerBound(-axis0upper);
                yAxis1.setLowerBound(-axis1upper);
            }
             */
        }


        //Map the data to the appropriate axis
        plot.mapDatasetToRangeAxis(0, 0);
        plot.mapDatasetToRangeAxis(1, 1);

        JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
        return chart;
    }
}
