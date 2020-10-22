# Secret Gift Exchange Project

## Product Description
This project implements gift exchange services to family members within a family. It assigns each member a random gift recipient(not themselves), and also prevents them from getting a gift from the same person within 3 years.


## Installing Prerequisites 
* Java 8
* Junit
* Spring Boot
* Maven

## REST API in this project(using Spring Boot)
* GET /members -- list the family members
* GET /members/{id} -- get a single family member
* POST /members -- add a family member
* PUT /members/{id} -- updates a family member
* DELETE /members/{id} -- delete a family member
* GET /gift_exchange -- lists members along with the member id they will be gifting to

## How to Run
To build the project, simply run:

```
$ mvn clean install
```

To run JUnit tests:

```
$ mvn test
```

## Notes:

1. When the REST API is hit the first time, it is assumed that this is the gift exchange happening the first year.
2. It is also assumed when the REST API is hit the second and third time, it is the second year and third year. And so on.



