package com.qaitdevlabs.qualityassessor.dao;

import java.util.List;

import com.qaitdevlabs.qualityassessor.model.Role;
import com.qaitdevlabs.qualityassessor.model.UrlRule;

public interface UrlRuleDao extends GenericDao<UrlRule, Long>{

	public List<String> getUrlAuthorityList(String url, String method);
	
	public List<UrlRule> getUrlAuthorityList();
}
