package com.qaitdevlabs.qualityassessor.radarchart.service.impl;

import java.awt.image.BufferedImage;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaitdevlabs.qualityassessor.dto.RadarChartInfo;
import com.qaitdevlabs.qualityassessor.dto.TreeNodeDTO;
import com.qaitdevlabs.qualityassessor.radarchart.service.RadarChartService;
import com.qaitdevlabs.qualityassessor.util.RadarChart;

@Service
public class RadarChartServiceImpl implements RadarChartService {

	private RadarChart radarChart;

	@Autowired
	public void setRadarChart(RadarChart radarChart) {
		this.radarChart = radarChart;
	}

	@Override
	public BufferedImage getBufferedImage(List<RadarChartInfo> nodes) {
		return radarChart.getBufferedImage(nodes, 800, 500);
	}

}
