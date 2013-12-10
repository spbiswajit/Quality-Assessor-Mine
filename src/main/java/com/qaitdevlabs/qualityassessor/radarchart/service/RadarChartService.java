package com.qaitdevlabs.qualityassessor.radarchart.service;

import java.awt.image.BufferedImage;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qaitdevlabs.qualityassessor.dto.RadarChartInfo;
import com.qaitdevlabs.qualityassessor.dto.TreeNodeDTO;


public interface RadarChartService {

	public BufferedImage getBufferedImage(List<RadarChartInfo> nodes);
}
