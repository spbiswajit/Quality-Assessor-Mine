package com.qaitdevlabs.qualityassessor.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;

/**
 * 
 * @author anujchhabra
 * 
 */
public interface UserDao extends GenericDao<User, Long> {

	public User saveUser(User user);

	/*
	 * To check user name and password combination
	 */

	public User validateUser(User user);

	public User findUserWithProperty(String property, String value);

	public User getUser(Long userId);

	public SocialNetwork saveSocialNetwork(SocialNetwork socialNetwork);

	public WorkExperience saveWorkExperience(WorkExperience workExperience);

	public List<SocialNetwork> getSocialNetworks(User user);

	public List<WorkExperience> getWorkExperiences(User user);

}
