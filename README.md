# RetailServiceWeb
myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 

The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 

Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.

Build an application that performs the following actions: 

      1. Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 
            Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 
            
      2. Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}
      
      3. Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 
      
      4. Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics
      
      5. Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 
      
      6. BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 
      

Case Study: myRetail RESTful service

TECHNOLOGY STACK:

      Maven       -   https://maven.apache.org/

      Spring Boot -   https://projects.spring.io/spring-boot/

      MongoDB 	-   https://www.mongodb.com/

      Swagger 	-   http://swagger.io/

      Mokito      -   http://site.mockito.org/

SETUP/DEPLOYMENT INSTRUCTIONS:

      Java 1.8

      Maven Install  - https://maven.apache.org/install.html

      Eclipse oxygen - https://www.eclipse.org/oxygen/

      Clone code from git repository - <URL>
  
      Launch Eclipse and Import project - File->import->Existing Maven Project, point to pom.xml file location

      Execute Clean Project

      Execute Maven command - Run as -> maven clean

      Execute Maven command - Run as -> maven install

      Goto SpringBoot Main Application -> run as Java Application

      Launch Browser pointing to Swagger UI URL - http://localhost:8080/swagger-ui.html
