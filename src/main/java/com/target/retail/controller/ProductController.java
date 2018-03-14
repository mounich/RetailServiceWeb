package com.target.retail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.target.retail.exception.BadRequestException;
import com.target.retail.exception.DataNotFoundException;
import com.target.retail.exception.InternalServerException;
import com.target.retail.json.entity.CurrentPriceJSONRequest;
import com.target.retail.json.entity.CurrentPriceJSONResponse;
import com.target.retail.json.entity.ProductJSONRequest;
import com.target.retail.json.entity.ProductJSONResponse;
import com.target.retail.model.CurrentPrice;
import com.target.retail.model.Product;
import com.target.retail.service.ProductService;

@RequestMapping ("/resources/1.0/products")
@RestController
public class ProductController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/{productId}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductJSONResponse> getProductDetailsById(@PathVariable("productId") final String productId)
			throws BadRequestException, InternalServerException, DataNotFoundException {

		if(null == productId) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST.value(),"productId empty in the request");
		}
		
		//Invoke Service to fetch data for given productId.
		Product product= null;
		product = productService.getProductDetailsById(productId);
		
		//if product record not found throw DataNotFoundException else Convert ValueObject Data to JSON Response Object and send back to client.
		ProductJSONResponse productJSONResponse = null;
		CurrentPriceJSONResponse currentPriceJSONResponse = null;
		CurrentPrice currentPrice = null;
		if(null == product){
			throw new DataNotFoundException(HttpStatus.NOT_FOUND.value(),"productdata not found in Datastore for product ID = "  + productId);
		}else {
			productJSONResponse = new ProductJSONResponse();
			productJSONResponse.setProductId(product.getId());
			productJSONResponse.setName(product.getName());
			if(null != product.getCurrentPrice()){
				currentPrice = product.getCurrentPrice();
				currentPriceJSONResponse = new CurrentPriceJSONResponse();
				currentPriceJSONResponse.setValue(currentPrice.getValue());
				currentPriceJSONResponse.setCurrencycode(currentPrice.getCurrencycode());
				productJSONResponse.setCurrentprice(currentPriceJSONResponse);
			}			
		}
		
		logger.info("getProductDetailsById.productJSONResponse " + productJSONResponse);
		return new ResponseEntity<ProductJSONResponse>(productJSONResponse, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT, produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductJSONResponse> updateProductDetailsById(
			@PathVariable("productId") final String productId, @RequestBody final ProductJSONRequest productJSONRequest)
			throws BadRequestException, InternalServerException {
		
		if(null == productId) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST.value(),"productId empty in the request");
		}
		if(null == productJSONRequest) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST.value(),"productdata empty in the body");
		}
        if (!productId.equalsIgnoreCase(productJSONRequest.getProductId())) {
        	throw new BadRequestException(HttpStatus.BAD_REQUEST.value(),"productId in incoming Path URL and request body does not match");
        }
		
		//Convert Incoming JSON Request Object to ValueObject and persist in Data store.
        Product product = null;
		product = new Product();
		product.setId(productJSONRequest.getProductId());
		product.setName(productJSONRequest.getName());
		CurrentPrice currentPrice = null;
		CurrentPriceJSONRequest currentPriceJSONRequest = null;
		if (null != productJSONRequest.getCurrentprice()) {
			currentPriceJSONRequest = productJSONRequest.getCurrentprice();
			currentPrice = new CurrentPrice();
			currentPrice.setValue(currentPriceJSONRequest.getValue());
			currentPrice.setCurrencycode(currentPriceJSONRequest.getCurrencycode());
			product.setCurrentPrice(currentPrice);
		}
		
		 //Save ValueObject to MongoDB Repository
		productService.updateProductDetailsById(product);
		
		//Convert Updated ValueObject to JSON Response Object and send back to client.
		ProductJSONResponse productJSONResponse = null;
		CurrentPriceJSONResponse currentPriceJSONResponse = null;
		productJSONResponse = new ProductJSONResponse();
		currentPriceJSONResponse = new CurrentPriceJSONResponse();
		productJSONResponse.setProductId(product.getId());
		productJSONResponse.setName(product.getName());
		currentPriceJSONResponse.setValue(product.getCurrentPrice().getValue());
		currentPriceJSONResponse.setCurrencycode(product.getCurrentPrice().getCurrencycode());
		productJSONResponse.setCurrentprice(currentPriceJSONResponse);

		logger.info("updateProductDetailsById.productJSONResponse " + productJSONResponse);
		return new ResponseEntity<ProductJSONResponse>(productJSONResponse, HttpStatus.OK);
	}
}