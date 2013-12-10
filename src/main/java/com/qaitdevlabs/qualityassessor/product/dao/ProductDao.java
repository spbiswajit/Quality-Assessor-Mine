package com.qaitdevlabs.qualityassessor.product.dao;

import java.io.Serializable;
import java.util.List;

import com.qaitdevlabs.qualityassessor.dao.GenericDao;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.User;

public interface ProductDao extends GenericDao<Product, Serializable> {

	/**
	 *Get list of products created by user 
	 * @param user user who creates the product
	 * @return list of products
	 */
	public List<Product> getListOfProductsByUser(User user);

	public void saveOrUpdateProduct(Product product);

	public void deleteProduct(Product product);

	public List<Product> getMatchingProducts(String name);
}
