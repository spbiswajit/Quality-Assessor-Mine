package com.qaitdevlabs.qualityassessor.service;

import java.util.List;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;

public interface WorkExperienceService {

	public WorkExperience saveWorkExperience(WorkExperience workExperience);

	public WorkExperience getWorkExperience(Long workExperienceId);

	public List<WorkExperience> getWorkExperiencesByUser(User user);
	
}
