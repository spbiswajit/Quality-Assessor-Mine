package com.qaitdevlabs.qualityassessor.security.customsecurityfilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Component;

import com.qaitdevlabs.qualityassessor.dao.UrlRuleDao;

/**
 * 
 * @author anujchhabra
 * 
 */
@Component
public class CustomFilterSecurityMetadataSource extends
		DefaultFilterInvocationSecurityMetadataSource {

	public CustomFilterSecurityMetadataSource() {
		super(null);
	}

	public CustomFilterSecurityMetadataSource(
			LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		super(requestMap);
	}

	private UrlRuleDao urlRuleDao;

	public UrlRuleDao getUrlRuleDao() {
		return urlRuleDao;
	}

	public void setUrlRuleDao(UrlRuleDao urlRuleDao) {
		this.urlRuleDao = urlRuleDao;
	}

	/**
	 * @see org.springframework.security.web.access.intercept.
	 *      DefaultFilterInvocationSecurityMetadataSource
	 *      #getAttributes(java.lang.Object)
	 */
	public List<ConfigAttribute> getAttributes(Object object) {
		System.out.println("getattributes");
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getRequestUrl();
		System.out.println(url);
		String method = fi.getHttpRequest().getMethod();
		List<ConfigAttribute> attributes = null;
		attributes = getAttributesByURL(url, method);
		return attributes;
	}

	/**
	 * @see org.springframework.security.web.access.intercept.
	 *      DefaultFilterInvocationSecurityMetadataSource#getAllConfigAttributes()
	 */
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		System.out.println("getAllConfig");
		return null;
	}

	/**
	 * @see org.springframework.security.web.access.intercept.
	 *      DefaultFilterInvocationSecurityMetadataSource#supports(java.lang.Class)
	 */
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	/**
	 * This method return list of authorities which have permission to access
	 * given url and given request method
	 * 
	 * @param inputUrl
	 *            requested url by user
	 * @param method
	 *            http method for eg GET,POST etc.
	 * @return list of authorities which have permission to access given url and
	 *         method type
	 */
	public List<ConfigAttribute> getAttributesByURL(String inputUrl,
			String method) {
		List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
		List<String> list = urlRuleDao.getUrlAuthorityList(inputUrl, method);
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			attributes.add(new SecurityConfig((String) it.next()));
		}
		return attributes;
	}
}