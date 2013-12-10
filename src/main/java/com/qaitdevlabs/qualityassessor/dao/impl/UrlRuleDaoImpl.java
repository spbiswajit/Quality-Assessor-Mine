package com.qaitdevlabs.qualityassessor.dao.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.qaitdevlabs.qualityassessor.dao.UrlRuleDao;
import com.qaitdevlabs.qualityassessor.model.UrlRule;

public class UrlRuleDaoImpl extends GenericDaoImpl<UrlRule, Long> implements
		UrlRuleDao {

	public UrlRuleDaoImpl() {
		super(UrlRule.class);

	}

	/**
	 * @see com.qainfotech.net.security.domain.dao.UrlAuthorityDAO#getAuthorityList
	 *      (java.lang.String, java.lang.String)
	 */
	@Cacheable("Authorities")
	public List<String> getUrlAuthorityList(String url, String method) {
		url = "/"+url.split("/")[1];
		System.out.print("authorites  "+url + " " + method);
		Object params[] = { url, method };
	
		@SuppressWarnings("unchecked")
		List<String> list = getHibernateTemplate()
				.find("select r.roleName from Role r join r.rules ru where ru.url =? and ru.method=?",
						params);
		System.out.println("list of roles"+list);
		return list;
	}
	
	public List<UrlRule> getUrlAuthorityList() {
		@SuppressWarnings("unchecked")
		List<UrlRule> list = getHibernateTemplate()
				.find("from UrlRule ur join fetch ur.roles");
		//System.out.println("list of roles"+list);
		return list;
	}
}
