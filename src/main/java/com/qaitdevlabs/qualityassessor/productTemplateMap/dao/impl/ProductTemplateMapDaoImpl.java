package com.qaitdevlabs.qualityassessor.productTemplateMap.dao.impl;
import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.qaitdevlabs.qualityassessor.dao.impl.GenericDaoImpl;
import com.qaitdevlabs.qualityassessor.model.Domain;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.ProductTemplateMap;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.productTemplateMap.dao.ProductTemplateMapDao;

@Repository
public class ProductTemplateMapDaoImpl extends GenericDaoImpl<ProductTemplateMap, Serializable> implements ProductTemplateMapDao {

	public ProductTemplateMapDaoImpl(){
		super(ProductTemplateMap.class);
	}
	
	@Override
	public void saveOrUpdateProductTemplateMap(ProductTemplateMap productTemplateMap) {
		Session session = null;
		Transaction transaction = null;
		try {
			System.out.println("dao");
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(productTemplateMap);
			transaction.commit();
			System.out.println("commit");
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		
	}

	@Override
	public List<ProductTemplateMap> getProductsToBeReviewed(User user) {
		Session session = null;
		List<ProductTemplateMap> domains = null;

		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(ProductTemplateMap.class ,"productTemplateMap");
			criteria.createAlias("productTemplateMap.product", "product", Criteria.LEFT_JOIN);
			criteria.add(Restrictions.eq("product.user", user));
			domains = criteria.list();
			System.out.println("DAO"+domains);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return domains;
	}

	@Override
	public List<ProductTemplateMap> getListOfProductTemplateMapByProduct(Product product) {
		Session session = null;
		List<ProductTemplateMap> productTemplateMaps = null;

		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(ProductTemplateMap.class);
			criteria.add(Restrictions.eq("product", product));
			productTemplateMaps = criteria.list();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return productTemplateMaps;
	}

	@Override
	public boolean isProductTemplateMapAlreadyExist(Product product,
			Domain domain) {

		Session session = null;
		List<ProductTemplateMap> productTemplateMaps = null;

		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session
					.createCriteria(ProductTemplateMap.class);
			criteria.add(Restrictions.eq("product", product));
			criteria.add(Restrictions.eq("domain", domain));
			productTemplateMaps = criteria.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		if (productTemplateMaps != null && productTemplateMaps.size() > 0) {
			return true;
		}
		return false;
	}


	
}
