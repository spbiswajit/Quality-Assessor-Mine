package com.qaitdevlabs.qualityassessor.service;

import java.util.List;

import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;

/**
 * 
 * @author anujchhabra
 * 
 */
public interface UserService {

	public User getUser(Long userId);

	public User saveUser(User user);

	public List<User> getAllUsers();

	public List<SocialNetwork> getSocialNetworks(User user);

	public List<WorkExperience> getWorkExperiences(User user);

	public SocialNetwork saveSocialNetwork(SocialNetwork socialNetwork);

	public WorkExperience saveWorkExperience(WorkExperience workExperience);

	public User updateUser(User user);

	public User findUserWithProperty(String property, String value);
	
}
