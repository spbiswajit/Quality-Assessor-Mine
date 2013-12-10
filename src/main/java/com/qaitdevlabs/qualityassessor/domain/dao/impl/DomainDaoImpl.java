package com.qaitdevlabs.qualityassessor.domain.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qaitdevlabs.qualityassessor.dao.impl.GenericDaoImpl;
import com.qaitdevlabs.qualityassessor.domain.dao.DomainDao;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.DomainMapping;
import com.qaitdevlabs.qualityassessor.model.User;

/**
 * 
 * @author anujchhabra
 * 
 */
@Repository
public class DomainDaoImpl extends GenericDaoImpl<Domain, Long> implements
		DomainDao {

	public DomainDaoImpl() {
		super(Domain.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Domain> getRootDomainList() {
		Session session = null;
		List<Domain> domains = null;

		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Domain.class);
			criteria.add(Restrictions.eq("isParent", true));
			domains = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return domains;
	}

	@Override
	public List<Domain> getRootDomainListOnUserBasis(User user, String domainType) {
		Session session = null;
		List<Domain> domains = null;

		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Domain.class);
			criteria.add(Restrictions.eq("isParent", true));
			criteria.add(Restrictions.eq("creationUser", user));
			if(domainType != null){
				criteria.add(Restrictions.eq("domainType", domainType));
			}
			domains = criteria.list();
			System.out.println("DAO"+domains);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return domains;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DomainMapping> getSubDomainList(Long id) {

		Session session = null;
		List<DomainMapping> domainmappings = null;
		try {
			session = getSessionFactory().openSession();
			String SQL_QUERY = "from DomainMapping domainMapping where domainMapping.domain.id=:id";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter("id", id);
			domainmappings = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return domainmappings;

	}

	@Override
	public DomainMapping getDomainMapping(Long domainId, Long subDomainId) {

		Session session = null;
		DomainMapping domainMapping = null;
		try {
			session = getSessionFactory().openSession();
			String SQL_QUERY = "from DomainMapping domainMapping where domainMapping.domain.id=:domainId and domainMapping.subDomain.id=:subDomainId";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter("domainId", domainId);
			query.setParameter("subDomainId", subDomainId);
			domainMapping = (DomainMapping) query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return domainMapping;

	}

	@Override
	public boolean deleteDomainMapping(Long parentDomainId, Long domainId) {

		Session session = null;
		int count = 0;
		boolean success = false;
		try {
			session = getSessionFactory().openSession();
			String SQL_QUERY = "delete from DomainMapping domainMapping where domainMapping.domain.id=:domainId and domainMapping.subDomain.id=:subDomainId";
			Query query = session.createQuery(SQL_QUERY);
			query.setParameter("domainId", parentDomainId);
			query.setParameter("subDomainId", domainId);
			count = query.executeUpdate();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (count > 0) {
			success = true;
		}
		return success;
	}

	@Override
	public DomainMapping saveOrUpdateDomainMapping(DomainMapping domainMapping) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(domainMapping);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return domainMapping;

	}

	@Override
	public Domain saveOrUpdateDomain(Domain domain) {

		Session session = null;
		Transaction transaction = null;
		try {
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(domain);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return domain;

	}

	@Override
	public List<Domain> findDomainsWithProperty(String property, String value) {
		Session session = null;
		List<Domain> domains = null;
		try {
			session = getSessionFactory().openSession();
			String queryString = "from Domain where " + property + " = :value";
			Query query = session.createQuery(queryString);
			query.setString("value", value);
			domains = (List<Domain>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return domains;

	}


	@Override
	public List<Domain> getDomainByNameAndType(String name ,String domainType) {
		//System.out.println("fdsdsdfsfasf");
		Session session = null;
		List<Domain> domains = null;
		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Domain.class);
			criteria.add(Restrictions.eq("domainName", name));
			criteria.add(Restrictions.eq("domainType", domainType));
			domains = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return domains;
	}



	
	@Override
	public List<Domain> getMatchingRootDomains(String name) {
		System.out.println("fdsdsdfsfasf");
		Session session = null;
		List<Domain> domains = null;
		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Domain.class);
			criteria.add(Restrictions.like("domainName", name+"%"));
			criteria.add(Restrictions.eq("isParent",true));
			domains = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return domains;
	}

}

// if(!lazyLoad) {
// Hibernate.initialize(savedUser.getAddress());
// }