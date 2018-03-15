package com.target.retail.configuration;

import static com.target.retail.util.Constants.MONGO_DATASTORE_HOST_URL;
import static com.target.retail.util.Constants.MONGO_DATASTORE_NAME;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;


public class SpringMongoConfig 
{
    public @Bean MongoTemplate mongoTemplate() throws Exception 
    {
    	MongoTemplate mongoTemplate = 
    			new MongoTemplate(new MongoClient(MONGO_DATASTORE_HOST_URL),MONGO_DATASTORE_NAME);
		return mongoTemplate;
	}
}
