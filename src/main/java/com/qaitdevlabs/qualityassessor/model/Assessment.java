package com.qaitdevlabs.qualityassessor.model;

import java.io.Serializable;
import java.util.Date;

public class Assessment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long assessmentId;
	private Product product;
	private User assessor;
	private AssessmentInvitation invitation;
	private Domain domain;
	private Date assessmentDate;
	private Integer score = 0;
	
	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}
	

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public User getAssessor() {
		return assessor;
	}

	public void setAssessor(User assessor) {
		this.assessor = assessor;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public AssessmentInvitation getInvitation() {
		return invitation;
	}

	public void setInvitation(AssessmentInvitation invitation) {
		this.invitation = invitation;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
}
