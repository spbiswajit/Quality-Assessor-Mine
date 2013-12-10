package com.qaitdevlabs.qualityassessor.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qaitdevlabs.qualityassessor.dao.RoleDao;
import com.qaitdevlabs.qualityassessor.model.Role;


public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao {

	@SuppressWarnings("unchecked")
	public List<String> getRolesByUserName(String userName) {
		return getHibernateTemplate()
				.find("SELECT r.roleName from Role r join r.groups rg join rg.users u where u.username=?",
						userName);

	}

	public Role getRoleByName(String roleName) {
		@SuppressWarnings("unchecked")
		List<Role> role = getHibernateTemplate().find(
				"from Role r where r.roleName=?", roleName);
		if (role.size() < 1) {
			return null;
		}
		return role.get(0);
	}

	/**
	 * Constructor to create a Generics-based version using Role as the entity
	 */
	public RoleDaoImpl() {
		super(Role.class);
	}
}
