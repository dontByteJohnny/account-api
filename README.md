# agileengine-api

# LOCALLY:
For local launch:
- SWAGGER: http://localhost:8080/swagger-ui.html#/
- UI list: http://localhost:8080/balance/list

- Step 1: download challenge-0.0.1-SNAPSHOT.jar
- Step 2: open cmd
- Step 3: navegate where you download the jar
- Step 4: type -> java -jar challenge-0.0.1-SNAPSHOT.jar
- OPC. Step 5: for testing you can add movements to the account like this: ex.: 
      curl --location --request POST 'http://localhost:8080/account' \
--header 'Content-Type: application/json' \
--data-raw '{
	"amount" : 990.11,
	"type" : "debit"
}'

where type = credit|CREDIT|debit|DEBIT


# WEB USAGE:
REST API is available in heroku:
- SWAGGER: https://agileenginellc.herokuapp.com
- UI list: https://agileenginellc.herokuapp.com/balance/list

The app is deployed in HEROKU with a swagger for a more friendly usage. Feel free of clone the project and deploy it locally. Its an Spring Boot project with JAVA 1.8 and more:
- Spring > WEB, MVC
- JPA
- Lombok
- H2 DB
- Exception Handler
- Thymeleaf + Bootstrap
- Swagger
- etc...
