package com.qaitdevlabs.qualityassessor.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaitdevlabs.qualityassessor.dao.WorkExperienceDao;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;
import com.qaitdevlabs.qualityassessor.service.WorkExperienceService;

@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {

	private WorkExperienceDao workExperienceDao;

    @Autowired
    public void setWorkExperienceDao(WorkExperienceDao workExperienceDao) {
        this.workExperienceDao = workExperienceDao;
    }
	
	@Override
	public WorkExperience saveWorkExperience(WorkExperience workExperience) {
		return workExperienceDao.saveWorkExperience(workExperience);
		
	}

	@Override
	public WorkExperience getWorkExperience(Long workExperienceId) {
		return workExperienceDao.getWorkExperience(workExperienceId);
	}

	@Override
	public List<WorkExperience> getWorkExperiencesByUser(User user) {
		return workExperienceDao.getWorkExperiencesByUser(user);
	}

}
