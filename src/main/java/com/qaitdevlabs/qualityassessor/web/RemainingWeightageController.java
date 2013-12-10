package com.qaitdevlabs.qualityassessor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaitdevlabs.qualityassessor.domain.service.DomainService;

/**
 * This class is used to get remaining weightage in parent Domain
 * 
 * @author anujchhabra
 * 
 */
@Controller
public class RemainingWeightageController {

	private DomainService domainService;

	@Autowired
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	/**
	 * This method is used to get remaining weightage in parent Domain
	 * 
	 * @param model
	 *            ModelMap
	 * @param domainKey
	 *            key of parent domain
	 * @return remaining weightage
	 */
	@RequestMapping(value = "/remainingWeightage", method = RequestMethod.GET)
	public @ResponseBody
	int getRemainingWeightageInParentDomain(ModelMap model,
			@RequestParam String domainKey) {
		int weightageRemaining = domainService
				.getRemainingWeightageInDomain(domainKey);
		return weightageRemaining;
	}

}
