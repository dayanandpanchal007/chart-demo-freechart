package com.chart.demo;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/chart")
public class ChartController {

    // https://stackoverflow.com/questions/11398434/xybarchart-and-clusteredxybarrenderer-specify-customized-zooming-levels
    // https://stackoverflow.com/questions/11366717/jfreechart-xybarchart-show-separate-bars-for-each-series
    // https://stackoverflow.com/questions/11366717/jfreechart-xybarchart-show-separate-bars-for-each-series

    // https://stackoverflow.com/questions/29494440/setting-different-y-axis-for-two-series-with-jfreechart

    @Autowired
    ChartService chartService;

    @GetMapping("/bar")
    public String generateBarChart() {
        XYSeriesCollection intervalXYDataSet  = new XYSeriesCollection();
        chartService.createDummyData(intervalXYDataSet);
        JFreeChart chart = chartService.createClusteredChart(
                "Demo Chart",
                "X-Axis",
                "Y-Axis",
                intervalXYDataSet);

        File file = new File("src/main/resources/data/output/chart.png");
        try {
            ChartUtils.saveChartAsPNG(file, chart, 1200, 800);
            return "Success";
        } catch (IOException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }

    }

    @GetMapping("/2yaxis")
    public String generate2yAxisChart() {
        JFreeChart chart = chartService.create2yAxisClusteredChart(
                "Demo Chart",
                "X-Axis",
                "Y-Axis");

        File file = new File("src/main/resources/data/output/chart2.png");
        try {
            ChartUtils.saveChartAsPNG(file, chart, 1200, 800);
            return "Success";
        } catch (IOException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }

    }
}


