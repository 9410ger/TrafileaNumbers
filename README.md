This project is working with Springboot framework, Kotlin as a programming language, PostgreSQL and Docker to run database.

First you have to have Docker in your system to run this service.

1. You have to enter to "devEnv" dir, then you are going to find a docker-compose.yml file, in terminal set the next command -> docker-compose up -d
   this is going to run the database.
2. Once the database is up, you can use Intellij or your preference code editor to run the main class called -> TrafileaNumbersApplication
3. When the service is up, you can make some request to these endpoints using Postman:

Number Addition:
  POST -> http://localhost:8080/api/trafilea/number-trafilea/{number} -> Where {number} is any number 

Number Retrieval:
  GET -> http://localhost:8080/api/trafilea/number-trafilea/{number} -> Where {number} is any number 

Collection Retrieval:
  GET -> http://localhost:8080/api/trafilea/number-trafilea/all
