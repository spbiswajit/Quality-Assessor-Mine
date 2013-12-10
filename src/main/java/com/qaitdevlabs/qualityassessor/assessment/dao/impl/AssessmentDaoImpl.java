package com.qaitdevlabs.qualityassessor.assessment.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qaitdevlabs.qualityassessor.assessment.dao.AssessmentDao;
import com.qaitdevlabs.qualityassessor.dao.impl.GenericDaoImpl;
import com.qaitdevlabs.qualityassessor.model.Assessment;
import com.qaitdevlabs.qualityassessor.model.AssessmentInvitation;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.User;

@Repository
public class AssessmentDaoImpl extends GenericDaoImpl<Assessment, Long>
		implements AssessmentDao {

	@SuppressWarnings("unchecked")
	@Override
	public Assessment getAssessment(User assessor, Product product, Domain domain, AssessmentInvitation invitation) {
		Session session = null;
		List<Assessment> assessments = null;
		System.out.println(product.getProductId() + " " + assessor.getUserId());
		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Assessment.class);
			criteria.add(Restrictions.eq("assessor", assessor));
			criteria.add(Restrictions.eq("product", product));
			criteria.add(Restrictions.eq("domain", domain));
			if( invitation != null ){
				criteria.add(Restrictions.eq("invitation", invitation));
			}
			else{
				criteria.add(Restrictions.isNull("invitation"));
			}
			
			assessments = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (assessments.size() < 1) {
			return null;
		}
		return assessments.get(0);

	}

	@Override
	public List<Assessment> getAssessment(User assessor) {
		Session session = null;
		List<Assessment> assessments = null;
		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Assessment.class);
			criteria.add(Restrictions.eq("assessor", assessor));
			criteria.add(Restrictions.ne("user", assessor));
			assessments = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (assessments.size() < 1) {
			return null;
		}
		return assessments;
	}

	@Override
	public Double getAverageAssessment(Product product, User assessor, Domain domain) {
		Session session = null;
		Double score = new Double(0);
		try {
			session = getSessionFactory().openSession();
			String SQL_QUERY = "select avg(assessment.score) from Assessment assessment where assessment.product=:product and assessment.domain=:domain and assessment.assessor<>:assessor";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter("product", product);
			query.setParameter("domain", domain);
			query.setParameter("assessor", assessor);
			List results = query.list();
			Iterator resultIterator = results.iterator();
			if (resultIterator.hasNext()) {
			Double obj = (Double) resultIterator.next();
//            for (int i = 0; i < obj.length; i++) {
                   System.out.print("************************"+obj);
                   if(null != obj)
                    score= obj;
//            }
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return score;
	}

}
