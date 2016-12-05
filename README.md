# Restdemo
This project is simply a demonstration of a RESTful server using Spring Boot, Hibernate, and an embedded H2 database.  jUnits were excluded due to a lack of time, although given sufficient time TDD should have been used.  

# Running the demo
Nothing more than the following should be needed to start the server:

1. mvn clean install
2. java -jar target\restdemo-0.0.1-SNAPSHOT.jar

# Interacting with the server
At this time no client is provided.  Use a RESTful debugging client of your choice.  One such client which has been verified to work with the project is the Chrome extension DHC REST Client.

## Get a list of Customers
GET localhost:8080/customer

## Get Customer by id 1
GET localhost:8080/customer/1 

## Add a customer
POST localhost:8080/customer

With a payload like the following:
{
  "name": "Dude",
  "address1": "112233 Some Rd",
  "address2": "Apt 9",
  "city": "Kansas City",
  "state": "Kansas",
  "zip": "66106"
}

## Update a customer
PUT localhost:8080/customer

With a payload like the following:
{
  "id": 1,
  "name": "Dude",
  "address1": "112233 Some Rd",
  "address2": "Apt 900",
  "city": "Kansas City",
  "state": "Kansas",
  "zip": "66106"
}

## Delete a customer
DELETE localhost:8080/customer/1
