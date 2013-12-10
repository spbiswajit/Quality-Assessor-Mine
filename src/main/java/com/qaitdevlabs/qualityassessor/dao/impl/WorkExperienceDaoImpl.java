package com.qaitdevlabs.qualityassessor.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.qaitdevlabs.qualityassessor.dao.WorkExperienceDao;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;

@Repository
public class WorkExperienceDaoImpl extends GenericDaoImpl<WorkExperience, Long>
		implements WorkExperienceDao {

	public WorkExperienceDaoImpl() {
		super(WorkExperience.class);
	}

	
	@Override
	public WorkExperience saveWorkExperience(WorkExperience workExperience) {
		
		Session session = null;
		Transaction transaction = null;
		try {
			 session = getSessionFactory().openSession();
			 transaction = session.beginTransaction();
			 session.saveOrUpdate(workExperience);
			 transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return workExperience;
	}
	
	
	@Override
	public WorkExperience getWorkExperience(Long workExperienceId) {

		Session session = null;
		WorkExperience workExperience = null;
		try {
			session = getSessionFactory().openSession();
			workExperience = (WorkExperience) session.get(WorkExperience.class,
					workExperienceId);
			// if(!lazyLoad) {
			// Hibernate.initialize(savedUser.getAddress());
			// }
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return workExperience;
	}

	
	@Override
	public List<WorkExperience> getWorkExperiencesByUser(User user) {
		Session session = null;
		List<WorkExperience> workExperiences = null;

		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(WorkExperience.class);
			criteria.add(Restrictions.eq("user", user));
			workExperiences = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return workExperiences;
	}	
	
}
