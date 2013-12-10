package com.qaitdevlabs.qualityassessor.service;

import java.util.List;
import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.User;

public interface SocialNetworkService {

	public List<SocialNetwork> getSocialNetworksByUser(User user);

	public SocialNetwork getSocialNetwork(Long socialNetworkId);

	public SocialNetwork saveSocialNetwork(SocialNetwork socialNetwork);

	
}
