package com.qaitdevlabs.qualityassessor.service.impl;

import com.qaitdevlabs.qualityassessor.dao.GroupDao;
import com.qaitdevlabs.qualityassessor.model.Group;
import com.qaitdevlabs.qualityassessor.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: anujchhabra
 * Date: 28/8/12
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    @Autowired
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public Group getGroupByGroupName(String groupName) {
        return groupDao.getGroupByGroupName(groupName);
    }
}

