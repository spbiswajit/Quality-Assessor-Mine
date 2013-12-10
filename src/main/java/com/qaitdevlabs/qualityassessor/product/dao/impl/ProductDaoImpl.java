package com.qaitdevlabs.qualityassessor.product.dao.impl;

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
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.product.dao.ProductDao;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product, Serializable>
		implements ProductDao {
	
	public ProductDaoImpl() {
		super(Product.class);
	}

	@Override
	public List<Product> getListOfProductsByUser(User user) {
		Session session = null;
		List<Product> listOfProducts = null;
		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session
					.createCriteria(Product.class);
			criteria.add(Restrictions.eq("user", user));
			listOfProducts = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (listOfProducts.size() < 1) {
			return null;
		}
		return listOfProducts;
	}

	@Override
	public void saveOrUpdateProduct(Product product) {
		Session session = null;
		Transaction transaction = null;
		try {
			System.out.println("dao");
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(product);
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
	public void deleteProduct(Product product) {
		Session session = null;
		Transaction transaction = null;
		try {
			System.out.println("dao");
			session = getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.delete(product);
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
	public List<Product> getMatchingProducts(String name) {
		Session session = null;
		List<Product> products = null;
		try {
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.add(Restrictions.like("productName", name+"%"));
			products = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return products;
	}

}
