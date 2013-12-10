package com.qaitdevlabs.qualityassessor.assessment.dao;

import java.util.List;

import com.qaitdevlabs.qualityassessor.dao.GenericDao;
import com.qaitdevlabs.qualityassessor.model.Assessment;
import com.qaitdevlabs.qualityassessor.model.AssessmentInvitation;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.User;


public interface AssessmentDao extends GenericDao<Assessment, Long>{

	Assessment getAssessment(User assessor, Product product, Domain domain, AssessmentInvitation invitation);
	
	Double getAverageAssessment(Product product,User assessor, Domain domain);

	List<Assessment> getAssessment(User assessor);

}
