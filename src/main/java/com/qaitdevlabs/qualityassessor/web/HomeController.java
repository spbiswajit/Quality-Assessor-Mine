package com.qaitdevlabs.qualityassessor.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qaitdevlabs.qualityassessor.dao.RoleDao;
import com.qaitdevlabs.qualityassessor.domain.service.DomainService;
import com.qaitdevlabs.qualityassessor.dto.TreeNodeDTO;
import com.qaitdevlabs.qualityassessor.model.Role;
import com.qaitdevlabs.qualityassessor.model.User;

/**
 * This is a home controller class
 * 
 * @author anujchhabra
 * 
 */
@Controller
public class HomeController {

	private DomainService domainService;

	@Autowired
	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}

	private RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	/**
	 * This method redirects home url to home page of application
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(ModelMap model, HttpServletRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		System.out.println(auth.getAuthorities());
		User user = (User) auth.getPrincipal();
		Long userId = user.getUserId();
		HttpSession session = request.getSession();
		session.setAttribute("USER_ID", userId);
		session.setAttribute("userCompleteName", user.getFirstName()+" "+user.getLastName());
		request.setAttribute("message",session.getAttribute("message"));
		session.setAttribute("message",null);
		Role adminRole = roleDao.getRoleByName("ROLE_ADMIN");
		// Role userRole = roleDao.getRoleByName("ROLE_USER");
		System.out.println("landing page");
		if (auth.getAuthorities().contains(adminRole)) {
			System.out.println("landing admin page");
			session.setAttribute("group", "AdminGroup");
			
		} else {
			System.out.println("landing user page");
			session.setAttribute("group", "UserGroup");
			
		}
		return "home";

	}

	
}
