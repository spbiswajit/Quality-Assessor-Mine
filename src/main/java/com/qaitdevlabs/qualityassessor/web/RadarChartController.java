package com.qaitdevlabs.qualityassessor.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qaitdevlabs.qualityassessor.common.exception.GenericException;
import com.qaitdevlabs.qualityassessor.domain.service.DomainService;
import com.qaitdevlabs.qualityassessor.dto.RadarChartInfo;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.ProductTemplateMap;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.product.service.ProductService;
import com.qaitdevlabs.qualityassessor.productTemplateMap.service.ProductTemplateMapService;
import com.qaitdevlabs.qualityassessor.radarchart.service.RadarChartService;
import com.qaitdevlabs.qualityassessor.service.UserService;


@Controller
public class RadarChartController {

	private DomainService domainService;

	@Autowired
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private RadarChartService radarChartService;

	@Autowired
	public void setRadarChartService(RadarChartService radarChartService) {
		this.radarChartService = radarChartService;
	}
	
	private ProductTemplateMapService productTemplateMapService;

	@Autowired
	public void setProductTemplateMapService(
			ProductTemplateMapService productTemplateMapService) {
		this.productTemplateMapService = productTemplateMapService;
	}
	

	@RequestMapping(value = "assessments/{productTemplateKey}/chart", method = RequestMethod.GET)
	public String showRadarChart(ModelMap model , @PathVariable String productTemplateKey ,HttpServletRequest request) {
		Long productTemplateMapId = Long.valueOf(productTemplateKey);
		ProductTemplateMap productTemplateMap = productTemplateMapService.getProductTemplateMapById(productTemplateMapId);
	
		if(productTemplateMap == null){
			throw new GenericException("Requested domain doesn't exist!!!");
		}
		
		request.setAttribute("productName",productTemplateMap.getProduct().getProductName());
		request.setAttribute("domainName", productTemplateMap.getDomain().getDomainName());
		return "radarChart";
	}

	@RequestMapping(value = "/radarChart/{productTemplateKey}", method = RequestMethod.GET)
	public String showRadarChart(@PathVariable String productTemplateKey, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		Long productTemplateMapId = Long.valueOf(productTemplateKey);
		ProductTemplateMap productTemplateMap = productTemplateMapService.getProductTemplateMapById(productTemplateMapId);
		Product product = productTemplateMap.getProduct();
		Domain domain = productTemplateMap.getDomain();
		Long domainId = Long.valueOf(domain.getDomainId());
		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User assessor = userService.getUser(userId);
		List<RadarChartInfo> extremeChilds = new ArrayList<RadarChartInfo>();
	
		domainService.getExtremeChildDomains(domainId, product, assessor, extremeChilds);

		BufferedImage bufferImage = radarChartService
				.getBufferedImage(extremeChilds);
		response.setContentType("image/png");
		ServletOutputStream os;
		try {
			os = response.getOutputStream();
			ImageIO.write(bufferImage, "png", os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
