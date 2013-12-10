package com.qaitdevlabs.qualityassessor.service.impl;

import java.util.List;

import com.qaitdevlabs.qualityassessor.dao.UserDao;
import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;
import com.qaitdevlabs.qualityassessor.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This class is a implementation of UserService class
 * 
 * @author anujchhabra
 */
@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private PasswordEncoder passwordEncoder;

	public UserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<SocialNetwork> getSocialNetworks(User user) {
		List<SocialNetwork> socialNetworks = userDao.getSocialNetworks(user);
		return socialNetworks;
	}

	@Override
	public List<WorkExperience> getWorkExperiences(User user) {
		List<WorkExperience> workExperiences = userDao.getWorkExperiences(user);
		return workExperiences;
	}

	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(),
				user.getUsername()));
		userDao.saveUser(user);
		return user;
	}

	public User updateUser(User user) {
		userDao.saveUser(user);
		return user;
	}

	@Override
	public SocialNetwork saveSocialNetwork(SocialNetwork socialNetwork) {
		return userDao.saveSocialNetwork(socialNetwork);
	}

	@Override
	public WorkExperience saveWorkExperience(WorkExperience workExperience) {
		return userDao.saveWorkExperience(workExperience);
	}

	@Override
	public User getUser(Long userId) {
		User user = userDao.getUser(userId);
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public User findUserWithProperty(String property, String value) {
		return userDao.findUserWithProperty(property, value);
	}

}
