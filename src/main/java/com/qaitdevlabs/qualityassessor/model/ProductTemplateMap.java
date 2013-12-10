package com.qaitdevlabs.qualityassessor.model;

import java.io.Serializable;

public class ProductTemplateMap implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long productTemplateMapId;
	private Product product;
	private Domain domain;
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Long getProductTemplateMapId() {
		return productTemplateMapId;
	}

	public void setProductTemplateMapId(Long productTemplateMapId) {
		this.productTemplateMapId = productTemplateMapId;
	}

}
