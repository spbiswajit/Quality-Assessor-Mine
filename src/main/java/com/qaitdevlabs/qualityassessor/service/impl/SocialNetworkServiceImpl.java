package com.qaitdevlabs.qualityassessor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaitdevlabs.qualityassessor.dao.SocialNetworkDao;
import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.service.SocialNetworkService;

@Service
public class SocialNetworkServiceImpl implements SocialNetworkService {

	@Autowired
	private SocialNetworkDao socialNetworkDao;
	
	public void setSocialNetworkDao(SocialNetworkDao socialNetworkDao){
		this.socialNetworkDao= socialNetworkDao;
	}
	
	@Override
	public List<SocialNetwork> getSocialNetworksByUser(User user) {
		return socialNetworkDao.getSocialNetworksByUser(user);
	}

	@Override
	public SocialNetwork getSocialNetwork(Long socialNetworkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SocialNetwork saveSocialNetwork(SocialNetwork socialNetwork) {
		// TODO Auto-generated method stub
		return null;
	}

}
