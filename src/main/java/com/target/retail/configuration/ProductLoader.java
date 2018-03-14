package com.target.retail.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.target.retail.model.CurrentPrice;
import com.target.retail.model.Product;
import com.target.retail.repository.ProductRepository;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ProductRepository productRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    		logger.info("Method -->onApplicationEvent ");
    		
    		//Delete any Previous Data from DataStore.
    		productRepository.deleteAll();
    		
			List<Product> productsList = new ArrayList<Product>();

			Product product1 = new Product();
			product1.setId("13860428");
			CurrentPrice product1CurrentPriceObj1 = new CurrentPrice();
			product1CurrentPriceObj1.setCurrencycode("USD");
			product1CurrentPriceObj1.setValue(13.49);
			product1.setCurrentPrice(product1CurrentPriceObj1);
			
			Product product2 = new Product();
			product2.setId("16483589");
			CurrentPrice product2CurrentPriceObj2 = new CurrentPrice();
			product2CurrentPriceObj2.setCurrencycode("USD");
			product2CurrentPriceObj2.setValue(14.49);
			product2.setCurrentPrice(product2CurrentPriceObj2);

			Product product3 = new Product();
			product3.setId("16696652");
			CurrentPrice product3CurrentPriceObj3 = new CurrentPrice();
			product3CurrentPriceObj3.setCurrencycode("USD");
			product3CurrentPriceObj3.setValue(15.49);
			product3.setCurrentPrice(product3CurrentPriceObj3);

			Product product4 = new Product();
			product4.setId("16752456");
			CurrentPrice product4CurrentPriceObj4 = new CurrentPrice();
			product4CurrentPriceObj4.setCurrencycode("USD");
			product4CurrentPriceObj4.setValue(16.49);
			product4.setCurrentPrice(product4CurrentPriceObj4);
			
			Product product5 = new Product();
			product5.setId("15643793");
			CurrentPrice product5CurrentPriceObj5 = new CurrentPrice();
			product5CurrentPriceObj5.setCurrencycode("USD");
			product5CurrentPriceObj5.setValue(17.49);
			product5.setCurrentPrice(product5CurrentPriceObj5);
			
			Product product6 = new Product();
			product6.setId("15117729");
			CurrentPrice product6CurrentPriceObj6 = new CurrentPrice();
			product6CurrentPriceObj6.setCurrencycode("USD");
			product6CurrentPriceObj6.setValue(18.49);
			product6.setCurrentPrice(product6CurrentPriceObj6);

			productsList.add(product1);
			productsList.add(product2);
			productsList.add(product3);
			productsList.add(product4);
			productsList.add(product5);
			productsList.add(product6);
			
			productRepository.save(productsList);
			logger.info("Method -->onApplicationEvent : Saved Data to MongoDB DataStore");
	}
}