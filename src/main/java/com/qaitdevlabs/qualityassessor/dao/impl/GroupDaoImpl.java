package com.qaitdevlabs.qualityassessor.dao.impl;

import java.util.List;

import com.qaitdevlabs.qualityassessor.dao.impl.GenericDaoImpl;
import com.qaitdevlabs.qualityassessor.model.Group;
import com.qaitdevlabs.qualityassessor.model.Role;
import com.qaitdevlabs.qualityassessor.dao.GroupDao;
import com.qaitdevlabs.qualityassessor.dao.RoleDao;

public class GroupDaoImpl extends GenericDaoImpl<Group, Long> implements GroupDao {

	
	public GroupDaoImpl() {
		super(Group.class);
	}

	@SuppressWarnings("unchecked")
	@Override
    public Group getGroupByGroupName(String groupName) {
        List list = getHibernateTemplate()
                .find("SELECT g from Group g where g.groupName=?",
                        groupName);
        if (list == null)
            return null;
        else
            return (Group) list.get(0);
    }

	
}
