# Lottoland Back-end Java Challenge

##Frameworks
For this challenge I have used [Springboot-MVC](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) and [Thymeleaf](https://www.thymeleaf.org/) templates for the views.


## Usage

The application is coded in Java, using Maven to manage the project.

## Prerequisites

The project has been tested on the following Java versions:
* JDK 8

All other dependencies are handled through Maven, and noted in the included POM file.

## Running

The project is prepared for running as a Spring Boot app:

```
mvn spring-boot:run
```

It will be accessible at [http://localhost:9001/](http://localhost:9001/).

## Running the tests

```
mvn test
```

##Assumptions and Clarifications

I have assumed that the second part (statistics) was not necessary to be updated on live, for that it will update asynchronously, so the user experience won't be penalized.

The second view (statistics) refreshes itself every 10 seconds.

The application runs on port 9001 to avoid conflicts with the typically ones.