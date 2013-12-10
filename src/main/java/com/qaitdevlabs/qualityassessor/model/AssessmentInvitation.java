package com.qaitdevlabs.qualityassessor.model;

import java.util.Date;

public class AssessmentInvitation {
	private Long assessmentInvitationId;
	private User assessor;
	private ProductTemplateMap productTemplateMap;
	private Date invitationDate;
	private Boolean ignoreInvitation = false;

	

	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}

	public Long getAssessmentInvitationId() {
		return assessmentInvitationId;
	}

	public void setAssessmentInvitationId(Long assessmentInvitationId) {
		this.assessmentInvitationId = assessmentInvitationId;
	}

	public Date getInvitationDate() {
		return invitationDate;
	}

	public void setInvitationDate(Date invitationDate) {
		this.invitationDate = invitationDate;
	}

	public Boolean isIgnoreInvitation() {
		return ignoreInvitation;
	}

	public void setIgnoreInvitation(Boolean ignoreInvitation) {
		this.ignoreInvitation = ignoreInvitation;
	}

	public ProductTemplateMap getProductTemplateMap() {
		return productTemplateMap;
	}

	public void setProductTemplateMap(ProductTemplateMap productTemplateMap) {
		this.productTemplateMap = productTemplateMap;
	}

}
