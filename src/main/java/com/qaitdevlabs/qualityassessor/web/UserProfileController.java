package com.qaitdevlabs.qualityassessor.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qaitdevlabs.qualityassessor.common.exception.GenericException;
import com.qaitdevlabs.qualityassessor.dto.UserProfileDTO;
import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;
import com.qaitdevlabs.qualityassessor.service.SocialNetworkService;
import com.qaitdevlabs.qualityassessor.service.UserService;
import com.qaitdevlabs.qualityassessor.service.WorkExperienceService;

@Controller
public class UserProfileController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private WorkExperienceService workExperienceService;

	@Autowired
	public void setWorkExperienceService(
			WorkExperienceService workExperienceService) {
		this.workExperienceService = workExperienceService;
	}

	private SocialNetworkService socialNetworkService;

	@Autowired
	public void setSocialNetworkService(
			SocialNetworkService socialNetworkService) {
		this.socialNetworkService = socialNetworkService;
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String getProfilePage(HttpServletRequest request, ModelMap map) {

		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User user = userService.getUser(userId);

		List<WorkExperience> workExperiences = workExperienceService
				.getWorkExperiencesByUser(user);
		UserProfileDTO userProfileDTO = new UserProfileDTO();

		userProfileDTO.setUsername(user.getUsername());
		userProfileDTO.setFirstName(user.getFirstName());
		userProfileDTO.setLastName(user.getLastName());
		userProfileDTO.setCountry(user.getCountry());
		userProfileDTO.setCity(user.getCity());
		userProfileDTO.setState(user.getState());
		userProfileDTO.setZipCode(user.getZipCode());
		userProfileDTO.setAddressLine1(user.getAddressLine1());
		userProfileDTO.setAddressLine2(user.getAddressLine2());
		userProfileDTO.setWorkExperiences(workExperiences);

		String facebookId = "";
		String twitterId = "";
		String googleplusId = "";
		String linkedInId = "";

		List<SocialNetwork> socialNetworks = socialNetworkService
				.getSocialNetworksByUser(user);
		if (socialNetworks!= null && socialNetworks.size() > 0) {
			SocialNetwork socialNetwork = socialNetworks.get(0);
			facebookId = socialNetwork.getFacebookId();
			twitterId = socialNetwork.getTwitterId();
			googleplusId = socialNetwork.getGoogleplusId();
			linkedInId = socialNetwork.getLinkedInId();

		}

		userProfileDTO.setFacebookId(facebookId);
		userProfileDTO.setTwitterId(twitterId);
		userProfileDTO.setLinkedInId(linkedInId);
		userProfileDTO.setGoogleplusId(googleplusId);
		map.addAttribute("userProfileDTO", userProfileDTO);

		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String updateUserProfile(@Valid UserProfileDTO userProfileDTO,
			BindingResult result, ModelMap map, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "profile";
		}

		Long userId = (Long) request.getSession().getAttribute("USER_ID");
		User user = userService.getUser(userId);

		if (!userProfileDTO.getUsername().equals(user.getUsername())) {
			User dbUser = userService.findUserWithProperty("username",
					userProfileDTO.getUsername());
			if (dbUser != null) {
				result.rejectValue("", "errors.existing.user", null,
						"*Another User already exist with same email address");
				return "profile";
			}
		}

		user.setUsername(userProfileDTO.getUsername());
		user.setFirstName(userProfileDTO.getFirstName());
		user.setLastName(userProfileDTO.getLastName());
		user.setCountry(userProfileDTO.getCountry());
		user.setCity(userProfileDTO.getCity());
		user.setState(userProfileDTO.getState());
		user.setZipCode(userProfileDTO.getZipCode());
		user.setAddressLine1(userProfileDTO.getAddressLine1());
		user.setAddressLine2(userProfileDTO.getAddressLine2());
		userService.updateUser(user);

		List<WorkExperience> workExperiences = userProfileDTO
				.getWorkExperiences();
		if (workExperiences != null) {
			Iterator<WorkExperience> it = workExperiences.iterator();
			while (it.hasNext()) {
				WorkExperience workExperience = (WorkExperience) it.next();
				Long workExpId = workExperience.getWorkExperienceId();
				if (workExpId != null) {
					WorkExperience workExp = workExperienceService
							.getWorkExperience(workExpId);
					User actualUser = workExp.getUser();
					
					//check if work experience belongs to login user or other user
					
					if (actualUser== null || !actualUser.equals(user)) {
						throw new GenericException(
								"User have no right to update work experience of other person");
					}
				}
				workExperience.setUser(user);
				workExperienceService.saveWorkExperience(workExperience);
			}
		}

		List<SocialNetwork> socialNetworks = socialNetworkService
				.getSocialNetworksByUser(user);
		SocialNetwork socialNetwork = null;
		if (socialNetworks.size() > 0) {
			socialNetwork = socialNetworks.get(0);
		} else {
			socialNetwork = new SocialNetwork();
		}
		socialNetwork.setUser(user);
		socialNetwork.setFacebookId(userProfileDTO.getFacebookId());
		socialNetwork.setTwitterId(userProfileDTO.getTwitterId());
		socialNetwork.setLinkedInId(userProfileDTO.getLinkedInId());
		socialNetwork.setGoogleplusId(userProfileDTO.getGoogleplusId());
		userService.saveSocialNetwork(socialNetwork);

		request.getSession(false).setAttribute("message",
				"Your Profile has been updated successfully.");
		return "redirect:/home";
	}
}
