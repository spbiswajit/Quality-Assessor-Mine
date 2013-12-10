package com.qaitdevlabs.qualityassessor.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qaitdevlabs.qualityassessor.assessmentinvitation.service.AssessmentInvitationService;
import com.qaitdevlabs.qualityassessor.dto.AssessmentRequestDTO;
import com.qaitdevlabs.qualityassessor.model.AssessmentInvitation;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.service.UserService;

@Controller
public class AssessmentRequestsController {

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

	@RequestMapping(value = "/assessmentRequests", method = RequestMethod.GET)
	public String getAssessmentRequests(HttpServletRequest request, @RequestParam(defaultValue = "false") String ignoreInvitation) {
		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User assessor = userService.getUser(userId);
		boolean ignore = Boolean.valueOf(ignoreInvitation);
		List<AssessmentRequestDTO> assessmentList = assessmentInvitationService
				.getAssessmentInvitations(assessor,ignore);
		request.setAttribute("assessmentRequestList", assessmentList);
		return "assessmentRequests";
	}

	@RequestMapping(value = "/ignoreInvitation", method = RequestMethod.GET)
	public String ignoreRequest(HttpServletRequest request,
			@RequestParam Long invitationId) {
		AssessmentInvitation assessmentInvitation = assessmentInvitationService
				.getAssessmentInvitation(invitationId);
		if (assessmentInvitation != null) {
			assessmentInvitation.setIgnoreInvitation(true);
			assessmentInvitationService
					.saveOrUpdateInvitation(assessmentInvitation);
		}
		return "redirect:/assessmentRequests";

	}
}
