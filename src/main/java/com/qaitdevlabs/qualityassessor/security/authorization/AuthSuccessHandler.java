package com.qaitdevlabs.qualityassessor.security.authorization;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.qaitdevlabs.qualityassessor.dao.RoleDao;
import com.qaitdevlabs.qualityassessor.model.Role;
import com.qaitdevlabs.qualityassessor.model.User;

public class AuthSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	private String userUrl;
	private String adminUrl;
	private RoleDao roleDao;

	public RoleDao getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public void setAdminUrl(String adminUrl) {
		this.adminUrl = adminUrl;
	}

	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		System.out.println(auth.getAuthorities());
		User user = (User) auth.getPrincipal();
		Long userId = user.getUserId();
		HttpSession session =request.getSession();
		session.setAttribute("USER_ID", userId);
		Role adminRole = roleDao.getRoleByName("ROLE_ADMIN");
		Role userRole = roleDao.getRoleByName("ROLE_USER");
		System.out.println("landing page");
		if (auth.getAuthorities().contains(adminRole)) {
			System.out.println("landing admin page");
			session.setAttribute("group", "AdminGroup");
			return adminUrl;
		} else if (auth.getAuthorities().contains(userRole)) {
			System.out.println("landing user page");
			session.setAttribute("group", "UserGroup");
			return userUrl;
		} else {
			System.out.println("landing default page");
			return super.determineTargetUrl(request, response);
		}
	}

}
