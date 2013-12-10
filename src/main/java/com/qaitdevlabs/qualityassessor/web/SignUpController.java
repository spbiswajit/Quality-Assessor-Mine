package com.qaitdevlabs.qualityassessor.web;

import java.util.Date;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qaitdevlabs.qualityassessor.Constants;
import com.qaitdevlabs.qualityassessor.dto.UserProfileDTO;
import com.qaitdevlabs.qualityassessor.model.Group;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.service.GroupService;
import com.qaitdevlabs.qualityassessor.service.UserService;

/**
 * Created with IntelliJ IDEA. User: anujchhabra Date: 28/8/12 Time: 2:29 PM To
 * change this template use File | Settings | File Templates.
 */

@Controller
public class SignUpController {

	private UserService userService;
	private GroupService groupService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String showSignUpPage(ModelMap map) {
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		map.addAttribute("userProfileDTO", userProfileDTO);
		return "signUp";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String getUserDetails(@Valid UserProfileDTO userProfileDTO,
			BindingResult result, ModelMap map) {

		if (result.hasErrors()) {
			return "signUp";
		}
		
		User dbUser = userService.findUserWithProperty("username",
				userProfileDTO.getUsername());
		if (dbUser != null) {
			result.rejectValue("", "errors.existing.user", null,
					"*User already exist with same email address");
			return "signUp";
		}
		
		System.out.println("no error.................");
		User user = new User();
		user.setUsername(userProfileDTO.getUsername());
		user.setPassword(userProfileDTO.getPassword());
		user.setFirstName(userProfileDTO.getFirstName());
		user.setLastName(userProfileDTO.getLastName());

		Group group = groupService.getGroupByGroupName(Constants.GROUP_OWNER);
		System.out.println(group);
		Set<Group> groups = user.getGroups();
		groups.add(group);
		user.setGroups(groups);
		Date creationDate = new Date();
		user.setCreationDate(creationDate);

		userService.saveUser(user);

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				user, user.getPassword(), user.getAuthorities());
		auth.setDetails(user);
		SecurityContextHolder.getContext().setAuthentication(auth);

		return "redirect:/home";
	}
}
