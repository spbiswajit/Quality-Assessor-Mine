package com.qaitdevlabs.qualityassessor.dao;

import java.util.List;

import com.qaitdevlabs.qualityassessor.model.Role;

public interface RoleDao extends GenericDao<Role, Long>{
	/**
	 * This method return list of roles corresponds to given username
	 * 
	 * @param userName
	 *            login username of user
	 * @return list of roles corresponds to given username
	 */
	public List<String> getRolesByUserName(String userName);
	public Role getRoleByName(String roleName);
}
