package com.qaitdevlabs.qualityassessor.util;

import java.awt.Dimension;
import java.util.Iterator;
import java.util.List;
import java.awt.image.BufferedImage;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.springframework.stereotype.Component;

import com.qaitdevlabs.qualityassessor.dto.RadarChartInfo;
import com.qaitdevlabs.qualityassessor.dto.TreeNodeDTO;

@Component
public class RadarChart  {
	
	private static final long serialVersionUID = 1L;

	

	public DefaultCategoryDataset dataset;
	public SpiderWebPlot plot;

	public JFreeChart getJFreeChart(List<RadarChartInfo> nodes) {

		

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Iterator<RadarChartInfo> it = nodes.iterator();
		while (it.hasNext()) {
		
			RadarChartInfo node = it.next();
			System.out.println("dfsasdfasgfagreggwergwgwg"+node.getScore()+node.getTitle());
			dataset.addValue(node.getScore(), node.getCategory(), node.getTitle());
		}
				SpiderWebPlot plot = new SpiderWebPlot(dataset);
		plot.setStartAngle(54);
		plot.setInteriorGap(0.40);
		plot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
		LegendTitle legendtitle = new LegendTitle(plot);
        legendtitle.setPosition(RectangleEdge.BOTTOM);
        
		JFreeChart chart = new JFreeChart("", TextTitle.DEFAULT_FONT, plot,
				false);
		chart.addSubtitle(legendtitle);
		ChartUtilities.applyCurrentTheme(chart);
		ChartPanel chartPanel = new ChartPanel(chart);
		this.plot = (SpiderWebPlot) chartPanel.getChart().getPlot();
		this.dataset = (DefaultCategoryDataset) plot.getDataset();
		chartPanel.setPreferredSize(new Dimension(400, 270));
		
		
		return chart;
	}

	public BufferedImage getBufferedImage(List<RadarChartInfo> nodes ,int l,int w) {
		JFreeChart chart = getJFreeChart(nodes);
		return chart.createBufferedImage(l, w);
	}

//	public static void main(String[] args) {
//		RadarChart demo = new RadarChart("Chart");
//	
//		demo.pack();
//		RefineryUtilities.centerFrameOnScreen(demo);
//		demo.setVisible(true);
//	}
}
