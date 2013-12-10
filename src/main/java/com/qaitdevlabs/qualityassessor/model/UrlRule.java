package com.qaitdevlabs.qualityassessor.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UrlRule  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long urlRuleId;
	private String url;
	private String method;
	private Set<Role> roles = new HashSet<Role>(0);

	public Long getUrlRuleId() {
		return urlRuleId;
	}

	public void setUrlRuleId(Long urlRuleId) {
		this.urlRuleId = urlRuleId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Role> getRoles() {
		return roles;
	}

}
