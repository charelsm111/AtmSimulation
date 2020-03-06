# ATM SIMULATION v.3.0

This application is about people who have bank account which can withdraw and transfer the money to others.


 ## Requirements
 
 For building and running the application you need:
 
 - [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 - [Maven 3](https://maven.apache.org)
 
 ## Installation
 1. Clone the repository.
 2. Configure `database` in `application.properties`.
 
 ## Running the application locally
1. Open terminal and locate the project directory.
2. Then run the application using [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html):
 ```shell
 mvn spring-boot:run
 ```
Your application will run at at port 8080 by default. Access http://localhost:8080 to see the application

 ## Running the Test
1. Open terminal and locate the project directory.
2. Then run `mvn test`
 ```shell
 mvn test
 ```