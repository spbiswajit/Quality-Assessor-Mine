package com.qaitdevlabs.qualityassessor.dao;

import java.util.List;

import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;

public interface WorkExperienceDao extends GenericDao<WorkExperience, Long>{

	public WorkExperience saveWorkExperience(WorkExperience workExperience);

	WorkExperience getWorkExperience(Long workExperienceId);

	List<WorkExperience> getWorkExperiencesByUser(User user);

}
