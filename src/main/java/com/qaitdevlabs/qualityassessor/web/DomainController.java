package com.qaitdevlabs.qualityassessor.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qaitdevlabs.qualityassessor.dto.AjaxResponse;
import com.qaitdevlabs.qualityassessor.dto.DomainDTO;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaitdevlabs.qualityassessor.domain.service.DomainService;
import com.qaitdevlabs.qualityassessor.dto.TreeNodeDTO;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.service.UserService;

/**
 * This controller class is used to create,update,delete domains
 * 
 * @author anujchhabra
 * 
 */

@Controller
public class DomainController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private DomainService domainService;

	@Autowired
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	/**
	 * This method handles update operation on domains
	 * 
	 * @param model
	 *            ModelMap
	 * @param key
	 *            key of domain
	 * @param parentKey
	 *            parent key of domain
	 * @param title
	 *            domain name
	 * @param weightage
	 *            weightage of domain
	 * @return true if no exception occurs
	 */

	@RequestMapping(value = "/saveOrUpdateDomain", method = RequestMethod.POST)
	public @ResponseBody
	String saveOrUpdateDomain(ModelMap model, @RequestParam String key,
			@RequestParam String parentKey, @RequestParam String title,
			@RequestParam String weightage, @RequestParam String type, HttpServletRequest request) {

		System.out.println("Contoller" + key + " " + parentKey + " " + title
				+ " " + weightage);

		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User user = userService.getUser(userId);
		Date modificationDate = new Date();
		TreeNodeDTO dto = new TreeNodeDTO();
		dto.setKey(key);
		dto.setParentKey(parentKey);
		dto.setTitle(title);
		dto.setWeightage(weightage);
		dto.setModificationDate(modificationDate);
		dto.setCreationDate(modificationDate);
		dto.setType(type);
		Long domainId = null;
		domainId = domainService.updateDomain(dto, user);
		if (domainId == null) {
			domainId = (long) 0;
		}
		return domainId.toString();
	}

	/**
	 * This method handles delete operation on domains
	 * 
	 * @param model
	 *            ModelMap
	 * @param key
	 *            key of domain
	 * @param parentKey
	 *            parent key of domain
	 * @return true if delete operation happened successfulley otherwise return
	 *         false
	 */
	@RequestMapping(value = "/deleteDomain", method = RequestMethod.POST)
	public @ResponseBody
	String deleteDomainMapping(ModelMap model, @RequestParam String key,
			@RequestParam String parentKey, HttpServletRequest request,
			HttpServletResponse response) {

		String message = "Deletion Successful";

		boolean success = domainService.deleteDomain(parentKey, key);
		if (!success) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			message = "Deletion UnSuccessful";
		}

		return String.valueOf(message);
	}

	@RequestMapping(value = "/hasUpdateOrDeletePermission", method = RequestMethod.POST)
	public @ResponseBody
	AjaxResponse hasUpdateOrDeletePermission(ModelMap model, @RequestParam String key,
			HttpServletRequest request,	HttpServletResponse response) {

		AjaxResponse ajaxResponse = new AjaxResponse();
		ajaxResponse.setSuccess(false);
		ajaxResponse.setMessage("You are not the owner of this domain hierarchy");
		
		Long userId = (Long) request.getSession().getAttribute("USER_ID");

		boolean hasUpdateOrDeletePermission = domainService.hasUpdateOrDeletePermission(key,
				userId);
		System.out.println(hasUpdateOrDeletePermission);
		if (hasUpdateOrDeletePermission) {
			ajaxResponse.setSuccess(true);
			ajaxResponse.setMessage("You are the owner of this domain hierarchy");
		}
		return ajaxResponse;
	}

	/**
	 * This method is used to get list of child domains corresponding to parent
	 * key
	 * 
	 * @param key
	 *            parent key
	 * @return list of child domains
	 */
	@RequestMapping(value = "/domains")
	public String getDomainList(@RequestParam(defaultValue = "0") String key ,@RequestParam(required = false) String domainType, ModelMap model, HttpServletRequest request) {
		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User user = userService.getUser(userId);
		List<TreeNodeDTO> list = domainService.getDomainList(key, user, domainType);
		System.out.println("controller"+list);
		model.addAttribute("domainType", domainType);
		model.addAttribute("rootDomainList", list);
		return "commonDomainsView";
	}

	
	@RequestMapping(value = "/myTemplates")
	public String getMyDomainList(ModelMap model, HttpServletRequest request) {
		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User user = userService.getUser(userId);
		List<TreeNodeDTO> list = domainService.getDomainList("0", user, null);
		System.out.println("controller"+list);
		model.addAttribute("rootDomainList", list);
		return "myTemplates";
	}
	
	@RequestMapping(value = "/getExistingDomainHierachy", method = RequestMethod.GET)
	public @ResponseBody
	List<TreeNodeDTO> getExistingDomains(@RequestParam String name, @RequestParam String domainType) {
		List<TreeNodeDTO> listOfTreeNodeDTO = domainService.getExistingDomainHierarchy(name, domainType);
		return listOfTreeNodeDTO;
	}
	
	
	@RequestMapping(value = "/getMatchingRootDomains", method = RequestMethod.GET)
	public @ResponseBody
	List<TreeNodeDTO> getMatchingDomains(@RequestParam String name) {
		List<TreeNodeDTO> listOfDomain = domainService.getMatchingRootDomains(name);
		System.out.println(listOfDomain.size());
		return listOfDomain;
	}
	
	
//	@RequestMapping(value = "/getMatchingDomains", method = RequestMethod.GET)
//	public @ResponseBody
//	List<TreeNodeDTO> getMatchingDomains(@RequestParam String name, @RequestParam String domainType) {
//		List<TreeNodeDTO> listOfDomain = domainService.getMatchingDomain(name, domainType);
//		System.out.println(listOfDomain.size());
//		return listOfDomain;
//	}
	
	// @RequestMapping(value = "/domainSettings", method = RequestMethod.GET)
	// public String domainSettingsPage(ModelMap model) {
	//
	// return "domainSettings";
	// }

	// /**
	// * This method handles save operation on domains
	// *
	// * @param model
	// * ModelMap
	// * @param parentKey
	// * parent key of domain
	// * @param title
	// * domain name
	// * @param weightage
	// * weightage of domain
	// * @return data transfer object of saved domain
	// */
	// @RequestMapping(value = "/saveDomain", method = RequestMethod.POST)
	// public @ResponseBody
	// TreeNodeDTO saveDomain(ModelMap model, @RequestParam String parentKey,
	// @RequestParam String title, @RequestParam(required=false) String
	// weightage,HttpServletRequest request) {
	//
	// System.out.println("Contoller" + parentKey + " " + title + " "
	// + weightage);
	// Long userId = (Long) request.getSession().getAttribute("USER_ID");
	// User user = userService.getUser(userId);
	// Date creationDate = new Date();
	// TreeNodeDTO dto = new TreeNodeDTO();
	// dto.setParentKey(parentKey);
	// dto.setTitle(title);
	// dto.setWeightage(weightage);
	// dto.setCreationDate(creationDate);
	//
	// dto = domainService.saveDomain(dto,user);
	// System.out.println(dto);
	// return dto;
	// }

	// @RequestMapping(value = "/getSubDomains", method = RequestMethod.GET)
	// public @ResponseBody List<DomainDTO>getSubDomains(ModelMap model,
	// @RequestParam String key) {
	// List<DomainDTO> subDomains = domainService.getSubDomains(key);
	// return subDomains;
	// }

	

}
