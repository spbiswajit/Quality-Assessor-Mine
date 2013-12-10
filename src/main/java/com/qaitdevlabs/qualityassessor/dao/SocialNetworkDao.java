package com.qaitdevlabs.qualityassessor.dao;

import java.util.List;

import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.User;

public interface SocialNetworkDao extends GenericDao<SocialNetwork, Long>{

	List<SocialNetwork> getSocialNetworksByUser(User user);

	SocialNetwork getSocialNetwork(Long socialNetworkId);

	SocialNetwork saveSocialNetwork(SocialNetwork socialNetwork);

}
