package com.qaitdevlabs.qualityassessor.product.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qaitdevlabs.qualityassessor.dto.ProductDTO;
import com.qaitdevlabs.qualityassessor.model.Product;
import com.qaitdevlabs.qualityassessor.model.User;
import com.qaitdevlabs.qualityassessor.product.dao.ProductDao;
import com.qaitdevlabs.qualityassessor.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductDao productDao;
	
	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public List<Product> getListOfProductsByUser(User user) {
		return productDao.getListOfProductsByUser(user);
	}

	@Override
	public void saveOrUpdateProduct(Product product) {
		System.out.println("serv");
		productDao.saveOrUpdateProduct(product);
	}

	@Override
	public Product getProductById(Long id) {
		return productDao.get(id);
	}

	@Override
	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
		
	}

	@Override
	public  List<ProductDTO> getMatchingProducts(String name) {
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		List<Product> productList =  productDao.getMatchingProducts(name);
		Iterator<Product> it = productList.iterator();
		while(it.hasNext()){
			Product product = it.next();
			ProductDTO dto = new ProductDTO();
			dto.setId(product.getProductId().toString());
			dto.setProductName(product.getProductName());
			productDTOList.add(dto);
		}
		return productDTOList;
	}

	

	

}
