package com.qaitdevlabs.qualityassessor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ComponentRule implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long componentRuleId;
	private String componentName;
	private String componentType;

	public Long getComponentRuleId() {
		return componentRuleId;
	}

	public void setComponentRuleId(Long componentRuleId) {
		this.componentRuleId = componentRuleId;
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	private Set<Role> roles = new HashSet<Role>(0);
}
