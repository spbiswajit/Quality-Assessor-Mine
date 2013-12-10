package com.qaitdevlabs.qualityassessor.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.qaitdevlabs.qualityassessor.dao.UserDao;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.SocialNetwork;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.model.WorkExperience;

/**
 * 
 * @author anujchhabra
 * 
 */
//@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao ,UserDetailsService {



	protected MessageSourceAccessor messages;

	public UserDaoImpl() {
		super(User.class);
		messages = SpringSecurityMessageSource.getAccessor();

	}


    @Override
	@SuppressWarnings("deprecation")
	public User loadUserByUsername(String userName)
			throws UsernameNotFoundException, DataAccessException {
		System.out.println(userName);
		@SuppressWarnings("unchecked")
		List<User> list = getHibernateTemplate().find(
				"from User c where c.username=?", userName);
		System.out.println(list);
		if (list.size() != 0) {
			return (User) list.get(0);
		} else {
			throw new UsernameNotFoundException(messages.getMessage(
					"JdbcDaoImpl.notFound", new Object[] { userName },
					"Username {0} not found"), userName);
		}

	}

	

	@Override
	public User saveUser(User user) {
		
		Session session = null;
		Transaction transaction = null;
		try {
			 session = getSessionFactory().openSession();
			 transaction = session.beginTransaction();
			 session.saveOrUpdate(user);
			 transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return user;
	}

	@Override
	public User validateUser(User user) {
		Session session = null;
		User savedUser = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from User where username = :username and password =:password";
			 Query query = session.createQuery(queryString);
			 query.setString("username", user.getUsername());
			 query.setString("password", user.getPassword());
			 savedUser = (User) query.uniqueResult();			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return savedUser;		
	}

	@Override
	public User findUserWithProperty(String property, String value) {
		Session session = null;
		User savedUser = null;
		try {
 			 session = getSessionFactory().openSession();
			 String queryString = "from User where "+ property +" = :value";
			 Query query = session.createQuery(queryString);
			 query.setString("value", value);
			 savedUser = (User) query.uniqueResult();			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return savedUser;		
	
	}

	@Override
	public User getUser(Long userId) {
		Session session = null;
		User savedUser = null;
		try {
 			 session = getSessionFactory().openSession();
 			 savedUser = (User)session.get(User.class, userId);
// 			 if(!lazyLoad) {
// 				Hibernate.initialize(savedUser.getAddress());
// 			 }
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return savedUser;				
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
	public List<SocialNetwork> getSocialNetworks(User user) {
		Session session = null;
		List<SocialNetwork> socialNetworks = null;

		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(SocialNetwork.class);
			criteria.add(Restrictions.eq("user", user));
			socialNetworks = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return socialNetworks;				
	}
	
	
	@Override
	public List<WorkExperience> getWorkExperiences(User user) {
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
		//System.out.println(workExperiences.get(0).getTitle());
		return workExperiences;					
	}
}
