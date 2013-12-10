package com.qaitdevlabs.qualityassessor.web;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qaitdevlabs.qualityassessor.assessmentinvitation.service.AssessmentInvitationService;
import com.qaitdevlabs.qualityassessor.domain.service.DomainService;
import com.qaitdevlabs.qualityassessor.dto.DomainDTO;
import com.qaitdevlabs.qualityassessor.model.AssessmentInvitation;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.ProductTemplateMap;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.productTemplateMap.service.ProductTemplateMapService;
import com.qaitdevlabs.qualityassessor.service.UserService;


@Controller
public class InvitiationListController {

	private AssessmentInvitationService assessmentInvitationService;

	@Autowired
	public void setAssessmentInvitationService(
			AssessmentInvitationService assessmentInvitationService) {
		this.assessmentInvitationService = assessmentInvitationService;
	}

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
	
	private ProductTemplateMapService productTemplateMapService;

	@Autowired
	public void setProductTemplateMapService(
			ProductTemplateMapService productTemplateMapService) {
		this.productTemplateMapService = productTemplateMapService;
	}
	

	@RequestMapping(value = "assessments/{productTemplateMapKey}/invitation", method = RequestMethod.GET)
	public String getListOfApplicationUsers(@PathVariable Long productTemplateMapKey, ModelMap map,
			HttpServletRequest request) {
		System.out.println(productTemplateMapKey);
		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User user = userService.getUser(Long.valueOf(userId));
		List<User> userList = userService.getAllUsers();
		if (userList.contains(user)) {
			userList.remove(user);
		}
//		List<DomainDTO> domainList = domainService.getListOfRootDomains();
//		System.out.println(domainList.get(0).getName());
		map.addAttribute("userList", userList);
//		map.addAttribute("domainList", domainList);

		return "assessmentInvitation";
	}

/*	@RequestMapping(value = "/invitationlist", method = RequestMethod.POST)
	public @ResponseBody
	String saveSelectedUserAndDomainList(
			@SuppressWarnings("rawtypes") @RequestBody List<LinkedHashMap> data,
			HttpServletRequest request) {
		String successView = "";
		System.out.println(data.get(0).get("userIds"));
		Date invitationDate = new Date();
		@SuppressWarnings("unchecked")
		List<LinkedHashMap<String, String>> userIds = (List<LinkedHashMap<String, String>>) data
				.get(0).get("userIds");
		@SuppressWarnings("unchecked")
		List<LinkedHashMap<String, String>> domainIds = (List<LinkedHashMap<String, String>>) data
				.get(1).get("domainIds");
		for (int i = 0; i < domainIds.size(); i++) {
			LinkedHashMap<String, String> map = domainIds.get(i);
			System.out.println(map.get("id"));
		}

		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User user = userService.getUser(Long.valueOf(userId));
		Iterator<LinkedHashMap<String, String>> domainItr = domainIds
				.iterator();
		while (domainItr.hasNext()) {
			String domainId = domainItr.next().get("id");
			Domain domain = domainService.getDomain(domainId);
			Iterator<LinkedHashMap<String, String>> userItr = userIds
					.iterator();
			User assessor;
			while (userItr.hasNext()) {
				userId = Long.valueOf(userItr.next().get("id"));
				assessor = userService.getUser(userId);
				AssessmentInvitation assessmentInvitation = new AssessmentInvitation();
				assessmentInvitation.setDomain(domain);
				assessmentInvitation.setAssessor(assessor);
				assessmentInvitation.setUser(user);
				assessmentInvitation.setInvitationDate(invitationDate);
				assessmentInvitationService
						.sendInvitation(assessmentInvitation);
			}
		}

		request.getSession(false).setAttribute("message",
				"Invitation has been sent.");
		successView = "home";
		return successView;
	}*/

	@RequestMapping(value = "assessments/{productTemplateMapKey}/invitation", method = RequestMethod.POST)
	public @ResponseBody
	String sendInvitatinToSelectedUsers(
			@PathVariable Long productTemplateMapKey,
			@SuppressWarnings("rawtypes") @RequestBody List<LinkedHashMap> data,
			HttpServletRequest request) {
		String successView = "";
		System.out.println(data.get(0).get("userIds"));
		Date invitationDate = new Date();
		@SuppressWarnings("unchecked")
		List<LinkedHashMap<String, String>> userIds = (List<LinkedHashMap<String, String>>) data
				.get(0).get("userIds");

		ProductTemplateMap productTemplateMap = productTemplateMapService
				.getProductTemplateMapById(productTemplateMapKey);
		Long userId = (Long) request.getSession().getAttribute("USER_ID");
//		User user = userService.getUser(Long.valueOf(userId));

		Iterator<LinkedHashMap<String, String>> userItr = userIds.iterator();
		User assessor = null;
		while (userItr.hasNext()) {
			userId = Long.valueOf(userItr.next().get("id"));
			assessor = userService.getUser(userId);
			AssessmentInvitation assessmentInvitation = new AssessmentInvitation();
			assessmentInvitation.setProductTemplateMap(productTemplateMap);
			assessmentInvitation.setAssessor(assessor);
			assessmentInvitation.setInvitationDate(invitationDate);
			assessmentInvitationService.sendInvitation(assessmentInvitation);
		}
		request.getSession(false).setAttribute("message",
				"Invitation has been sent.");
		successView = "/qualityassessor/home";
		return successView;
	}

}
