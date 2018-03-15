package com.target.retail.client.rest.template;

import static com.target.retail.util.Constants.PRODUCT;
import static com.target.retail.util.Constants.PRODUCT_DESCRIPTION;
import static com.target.retail.util.Constants.PRODUCT_ITEM;
import static com.target.retail.util.Constants.PRODUCT_TITLE;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.target.retail.exception.DataNotFoundException;
import com.target.retail.exception.InternalServerException;

@Component
public class ClientRestTemplate {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${product-name-endpoint}")
	private String productNameEndPoint;

	public ClientRestTemplate() {
	}
	
	public String getProductNameFromProductEndPoint(String productId) throws InternalServerException {
		logger.info("Method -->getProductNameFromProductEndPoint :  " + productId);
		String productName = null;
		UriComponentsBuilder builder = null;
		String response = null;
		JSONObject jsonObject = null;
		JSONObject productDesc = null;
		try 
		{
			builder = UriComponentsBuilder.fromUriString(productNameEndPoint + productId);
			builder.queryParam("excludes","taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");
			logger.info("Method -->getProductNameFromProductEndPoint : URI " + builder.build().encode().toUri());
			response = restTemplate.getForObject(builder.build().encode().toUri(), String.class);
			if (null != response) 
			{
				logger.info("response :" + response);
				jsonObject = new JSONObject(response);
				if (null != jsonObject.getJSONObject(PRODUCT)
						&& null != jsonObject.getJSONObject(PRODUCT).getJSONObject(PRODUCT_ITEM) 
								&& null != jsonObject.getJSONObject(PRODUCT).getJSONObject(PRODUCT_ITEM).getJSONObject(PRODUCT_DESCRIPTION)) 
				{
									productDesc = jsonObject.getJSONObject(PRODUCT).getJSONObject(PRODUCT_ITEM).getJSONObject(PRODUCT_DESCRIPTION);
									productName = productDesc.getString(PRODUCT_TITLE);
										if(null == productName)
										{
											throw new DataNotFoundException(HttpStatus.NOT_FOUND.value(),"productname not found for productId = "+ productId);
										}
				} 
				else 
				{
					throw new DataNotFoundException(HttpStatus.NOT_FOUND.value(),
							"product or product item or product description not found for ProductID = " + productId
									+ "based on constructed URL = " + builder.build().encode().toUri());
				}
			}
		} 
		catch (JSONException je) 
		{
			throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "cannot parse json response for productId= "+productId);
		} 
		catch (RestClientException rce)
		{
			throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "error while accessing endpoint " + builder.build().encode().toUri() + " for productId = "+productId);
		} 
		catch (Exception ex) 
		{
			throw new InternalServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
		}
		return productName;
	}
}