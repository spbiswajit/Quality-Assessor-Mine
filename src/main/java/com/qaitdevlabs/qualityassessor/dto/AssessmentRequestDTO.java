package com.qaitdevlabs.qualityassessor.dto;

public class AssessmentRequestDTO {
	
	private String productName;
	private String domainName;
	private String invitationDate;
	private String userCompleteName;
	private Long productTemplateMapId;
	private Long invitationId;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	private Long userId;
	
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getInvitationDate() {
		return invitationDate;
	}
	public void setInvitationDate(String invitationDate) {
		this.invitationDate = invitationDate;
	}
	public String getUserCompleteName() {
		return userCompleteName;
	}
	public void setUserCompleteName(String userCompleteName) {
		this.userCompleteName = userCompleteName;
	}
	public Long getProductTemplateMapId() {
		return productTemplateMapId;
	}
	public void setProductTemplateMapId(Long productTemplateMapId) {
		this.productTemplateMapId = productTemplateMapId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getInvitationId() {
		return invitationId;
	}
	public void setInvitationId(Long invitationId) {
		this.invitationId = invitationId;
	}
	
}
