package com.qaitdevlabs.qualityassessor.model;

/**
 * Created with IntelliJ IDEA. User: anujchhabra Date: 28/8/12 Time: 12:45 PM To
 * change this template use File | Settings | File Templates.
 */
public class SocialNetwork {
	private Long socialNetworkId;
	private String facebookId;
	private String twitterId;
	private String googleplusId;
	private String linkedInId;
	private User user;

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public String getGoogleplusId() {
		return googleplusId;
	}

	public void setGoogleplusId(String googleplusId) {
		this.googleplusId = googleplusId;
	}

	

	public Long getSocialNetworkId() {
		return socialNetworkId;
	}

	public void setSocialNetworkId(Long socialNetworkId) {
		this.socialNetworkId = socialNetworkId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLinkedInId() {
		return linkedInId;
	}

	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}

	
}
