package com.qaitdevlabs.qualityassessor.assessmentinvitation.dao;

import java.io.Serializable;
import java.util.List;

import com.qaitdevlabs.qualityassessor.dao.GenericDao;
import com.qaitdevlabs.qualityassessor.model.Assessment;
import com.qaitdevlabs.qualityassessor.model.AssessmentInvitation;
import com.qaitdevlabs.qualityassessor.model.User;

public interface AssessmentInvitationDao extends GenericDao<AssessmentInvitation, Serializable>{
	
	public AssessmentInvitation saveOrUpdateAssessmentInvitation(
			AssessmentInvitation assessmentInvitation);

	public List<AssessmentInvitation> getAssessmentInvitations(User assessor,
			boolean isIgnore);

}
