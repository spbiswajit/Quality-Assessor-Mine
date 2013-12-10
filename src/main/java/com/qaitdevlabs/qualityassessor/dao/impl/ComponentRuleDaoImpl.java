package com.qaitdevlabs.qualityassessor.dao.impl;

import java.util.List;

import com.qaitdevlabs.qualityassessor.dao.ComponentRuleDao;
import com.qaitdevlabs.qualityassessor.model.ComponentRule;

public class ComponentRuleDaoImpl extends GenericDaoImpl<ComponentRule, Long>
		implements ComponentRuleDao {

	public ComponentRuleDaoImpl() {
		super(ComponentRule.class);
	}

	/**
	 * @see com.qainfotech.net.security.domain.dao.UrlAuthorityDAO#getAuthorityList
	 *      (java.lang.String, java.lang.String)
	 */
	@Override
	public List<String> getComponentAuthorityList(String componentType,
			String componentName) {
		System.out.print(componentType + " " + componentName);
		Object params[] = { componentType, componentName };
		@SuppressWarnings("unchecked")
		List<String> list = getHibernateTemplate()
				.find("select r.roleName from Role r join r.componentrules ru where ru.componentType=? and ru.componentName=?",
						params);
		System.out.println(list);
		return list;
	}
}
