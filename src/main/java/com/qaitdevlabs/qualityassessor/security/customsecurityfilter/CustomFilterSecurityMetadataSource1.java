package com.qaitdevlabs.qualityassessor.security.customsecurityfilter;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import com.qaitdevlabs.qualityassessor.dao.UrlRuleDao;
import com.qaitdevlabs.qualityassessor.model.Role;
import com.qaitdevlabs.qualityassessor.model.UrlRule;

//@Component("customFilterSecurityMetadataSource1")
public class CustomFilterSecurityMetadataSource1 implements FilterInvocationSecurityMetadataSource {

	private DefaultFilterInvocationSecurityMetadataSource df;
	
	//private ExpressionBasedFilterInvocationSecurityMetadataSource df;
	
	@Autowired
	private UrlRuleDao urlRuleDao;
	
	public UrlRuleDao getUrlRuleDao() {
		return urlRuleDao;
	}

	public void setUrlRuleDao(UrlRuleDao urlRuleDao) {
		this.urlRuleDao = urlRuleDao;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) {
		return this.df.getAttributes(object);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return this.df.getAllConfigAttributes();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return this.df.supports(clazz);
	}
	
	@PostConstruct
	public void prepareAttributeList() {
		List<UrlRule> roleList = urlRuleDao.getUrlAuthorityList();
		Set<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();
		LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		for(UrlRule urlRule : roleList) {
			AntPathRequestMatcher matcher = new AntPathRequestMatcher(urlRule.getUrl());
			for(Role role : urlRule.getRoles()) {
				attributes.add(new SecurityConfig(role.getAuthority()));
			}
			requestMap.put(matcher, attributes);
		}	
		//this.df = new ExpressionBasedFilterInvocationSecurityMetadataSource(requestMap, new DefaultWebSecurityExpressionHandler());
		this.df = new DefaultFilterInvocationSecurityMetadataSource(requestMap);
	}
}
