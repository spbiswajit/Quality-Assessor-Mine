package com.qaitdevlabs.qualityassessor.dao;

import java.util.List;

import com.qaitdevlabs.qualityassessor.model.ComponentRule;

public interface ComponentRuleDao extends GenericDao<ComponentRule, Long> {

	public List<String> getComponentAuthorityList(String componentType,
			String componentName);
}
