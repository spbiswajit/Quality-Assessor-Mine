package com.qaitdevlabs.qualityassessor.assessment.service;

import java.util.List;

import com.qaitdevlabs.qualityassessor.model.Assessment;
import com.qaitdevlabs.qualityassessor.model.AssessmentInvitation;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.User;

public interface AssessmentService {
	public Assessment saveAssessment(Assessment assessment);

	public Assessment getAssessment(User assessor, Product product, Domain domain, AssessmentInvitation invitation);
	
	public List<Assessment> getAssessment(User assessor);

}
