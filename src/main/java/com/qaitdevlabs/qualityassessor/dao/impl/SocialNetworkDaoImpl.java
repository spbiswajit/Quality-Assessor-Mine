package com.qaitdevlabs.qualityassessor.dao.impl;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qaitdevlabs.qualityassessor.dao.SocialNetworkDao;
import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.User;

@Repository
public class SocialNetworkDaoImpl extends GenericDaoImpl<SocialNetwork, Long> implements SocialNetworkDao {

	public SocialNetworkDaoImpl() {
		super(SocialNetwork.class);
	}

	@Override
	public SocialNetwork saveSocialNetwork(SocialNetwork socialNetwork) {
		
		Session session = null;
		Transaction transaction = null;
		try {
			 session = getSessionFactory().openSession();
			 transaction = session.beginTransaction();
			 session.saveOrUpdate(socialNetwork);
			 transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return socialNetwork;
	}
	
	
	@Override
	public SocialNetwork getSocialNetwork(Long socialNetworkId) {

		Session session = null;
		SocialNetwork SocialNetwork = null;
		try {
			session = getSessionFactory().openSession();
			SocialNetwork = (SocialNetwork) session.get(SocialNetwork.class,
					socialNetworkId);
			// if(!lazyLoad) {
			// Hibernate.initialize(savedUser.getAddress());
			// }
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return SocialNetwork;
	}

	
	@Override
	public List<SocialNetwork> getSocialNetworksByUser(User user) {
		Session session = null;
		List<SocialNetwork> SocialNetworks = null;

		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(SocialNetwork.class);
			criteria.add(Restrictions.eq("user", user));
			SocialNetworks = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return SocialNetworks;
	}	
}
