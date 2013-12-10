package com.qaitdevlabs.qualityassessor.product.service;

import java.util.List;

import com.qaitdevlabs.qualityassessor.dto.ProductDTO;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.User;

public interface ProductService {

	/**
	 *Get list of products created by user 
	 * @param user user who creates the product
	 * @return list of products
	 */
	public List<Product> getListOfProductsByUser(User user);

	public void saveOrUpdateProduct(Product product);

	public Product getProductById(Long id);

	public void deleteProduct(Product product);

	public List<ProductDTO> getMatchingProducts(String term);

	
}
