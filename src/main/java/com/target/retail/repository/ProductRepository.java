package com.target.retail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.target.retail.model.Product;

public interface ProductRepository extends MongoRepository<Product,String> {

	/**
	 * @param productId
	 * @returns product details for a given Product ID.
	 */
	public Product getProductDetailsById(String id);
	
}