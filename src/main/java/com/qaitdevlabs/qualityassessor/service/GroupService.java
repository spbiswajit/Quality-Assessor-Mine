package com.qaitdevlabs.qualityassessor.service;

import com.qaitdevlabs.qualityassessor.dao.GroupDao;
import com.qaitdevlabs.qualityassessor.model.Group;


/**
 * Created with IntelliJ IDEA.
 * User: anujchhabra
 * Date: 28/8/12
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GroupService {

    public Group getGroupByGroupName(String groupName);

}
