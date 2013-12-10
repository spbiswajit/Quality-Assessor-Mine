package com.qaitdevlabs.qualityassessor.assessmentinvitation.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.qaitdevlabs.qualityassessor.assessmentinvitation.dao.AssessmentInvitationDao;
import com.qaitdevlabs.qualityassessor.dao.impl.GenericDaoImpl;
import com.qaitdevlabs.qualityassessor.model.AssessmentInvitation;
import com.qaitdevlabs.qualityassessor.model.User;

@Repository
public class AssessmentInvitationDaoImpl extends
		GenericDaoImpl<AssessmentInvitation, Serializable> implements
		AssessmentInvitationDao {

	public AssessmentInvitationDaoImpl() {
		super(AssessmentInvitation.class);
	}

	
	@Override
	public List<AssessmentInvitation> getAssessmentInvitations(User assessor,boolean isIgnore) {
		Session session = null;
		List<AssessmentInvitation> assessmentInvitation = null;
		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(AssessmentInvitation.class);
			criteria.add(Restrictions.eq("assessor", assessor));
			criteria.add(Restrictions.eq("ignoreInvitation", isIgnore));
			criteria.addOrder(Order.desc("invitationDate"));
			assessmentInvitation = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if ((assessmentInvitation == null) || (assessmentInvitation.size()) < 1) {
			return null;
		}
		return assessmentInvitation;
	}
	
	@Override
	public AssessmentInvitation saveOrUpdateAssessmentInvitation(AssessmentInvitation assessmentInvitation) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(assessmentInvitation);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return assessmentInvitation;

	}


}
