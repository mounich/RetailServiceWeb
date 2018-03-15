package com.target.retail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.target.retail.client.rest.template.ClientRestTemplate;
import com.target.retail.exception.DataNotFoundException;
import com.target.retail.exception.InternalServerException;
import com.target.retail.model.Product;
import com.target.retail.repository.ProductRepository;

@Service
public class ProductService {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ClientRestTemplate clientRestTemplate;
	
	@Autowired
	ProductRepository productRepository;
	
	public ProductService() {}
	
	//where exactly is the method implementation and where is it getting product from
	public Product getProductDetailsById(String productId) throws InternalServerException, DataNotFoundException {
		Product product = null;
		product = productRepository.getProductDetailsById(productId);
		if(null == product) {
			throw new DataNotFoundException(HttpStatus.NOT_FOUND.value(),"Product Data Not Found in DataStore for Product ID " + productId);
		}
		product.setName(clientRestTemplate.getProductNameFromProductEndPoint(productId));
		return product;
	}

	public void updateProductDetailsById(Product product) throws InternalServerException{
		productRepository.save(product);
	}
	
}