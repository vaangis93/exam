### Exam - 10-04-2024

# Building a backend system for an e-commerce platform offering trip planning services. 
# Tasks include managing trips and guides


### HTTP requsts - Task 3 - 3.3.2

# POST http://localhost:7070/api/v1/trips - create trip

HTTP/1.1 201 Created
Date: Mon, 04 Nov 2024 10:56:38 GMT
Content-Type: application/json
Content-Length: 183

{
"id": 2,
"name": "Adventure in the Mountains",
"startTime": [
2024,
8,
1,
9,
0
],
"endTime": [
2024,
8,
10,
17,
0
],
"startPosition": "Mountain Base Camp",
"price": 499.99,
"category": "FOREST",
"guide": null
}

# GET http://localhost:7070/api/v1/trips/1  - get by id

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:29:08 GMT
Content-Type: application/json
Content-Length: 183

{
"id": 1,
"name": "Adventure in the Mountains",
"startTime": [
2024,
8,
1,
9,
0
],
"endTime": [
2024,
8,
10,
17,
0
],
"startPosition": "Mountain Base Camp",
"price": 499.99,
"category": "FOREST",
"guide": null
}

# GET http://localhost:7070/api/v1/trips - get all 

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 11:30:02 GMT
Content-Type: application/json
Content-Length: 185

[
{
"id": 1,
"name": "Adventure in the Mountains",
"startTime": [
2024,
8,
1,
9,
0
],
"endTime": [
2024,
8,
10,
17,
0
],
"startPosition": "Mountain Base Camp",
"price": 499.99,
"category": "FOREST",
"guide": null
}
]


# Some Forced error
GET http://localhost:7070/api/v1/trips

HTTP/1.1 200 OK
Date: Mon, 04 Nov 2024 10:13:22 GMT
Content-Type: application/json
Content-Length: 200

{
"error": "Cannot invoke \"jakarta.persistence.EntityManagerFactory.createEntityManager()\" because \"dat.daos.impl.TripDAO.emf\" is null",
"status": "200 OK",
"timestamp": "2024-11-04T11:13:22.220076600"
}

## TASK 3 - 3.3.5 
# PUT is typically used to update an existing resource. When you add a guide to a trip, you are updating that trip's information. where as POST is used to create a new resource. In this case, you are creating a new trip.


