package com.qaitdevlabs.qualityassessor.dao;

import java.util.List;

import com.qaitdevlabs.qualityassessor.model.Group;

public interface GroupDao extends GenericDao<Group, Long>{
	/**
	 * This method return list of groups corresponds to given groupName
	 * 
	 * @param groupName
	 *          
	 * @return list of groupss corresponds to given groupName
	 */
	public Group getGroupByGroupName(String groupName);
}
