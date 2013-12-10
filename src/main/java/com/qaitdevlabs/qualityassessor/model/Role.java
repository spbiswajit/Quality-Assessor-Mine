package com.qaitdevlabs.qualityassessor.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Role implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private Long roleId;
	private String roleName;
	private String roleDescription;
	private Set<Group> groups = new HashSet<Group>(0);
	private Set<UrlRule> rules = new HashSet<UrlRule>(0);
	private Set<UrlRule> componentrules = new HashSet<UrlRule>(0);

	public Set<UrlRule> getComponentrules() {
		return componentrules;
	}

	public void setComponentrules(Set<UrlRule> componentrules) {
		this.componentrules = componentrules;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setRules(Set<UrlRule> rules) {
		this.rules = rules;
	}

	public Set<UrlRule> getRules() {
		return rules;
	}

	@Override
	public String getAuthority() {
		return getRoleName();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Role) {
			Role roleObj = (Role) obj;
			if (roleObj.roleName.equals(roleName)) {
				return true;
			}
		}
		return false;
	}

}
