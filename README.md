# Apache Camel demo in a Spring Boot application

## This project was based on the 'in28minutes' Apache Camel tutorial on Udemy
Github repo with example files:
https://github.com/in28minutes/camel

## Apache Camel best practices
- add logging after each step
- enable tracing (getContext().setTracing(true);) for easier debugging
- to ensure no messages are lost you can configure a Dead Letter Queue (errorHandler(deadLetterChannel("activemq:dead-letter-queue"));)
- use 'wireTap()' to provide intermediate endpoints
- add security by encrypting messages, see details in the repo above