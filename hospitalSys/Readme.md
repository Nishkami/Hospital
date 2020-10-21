# Spring Boot, MySQL, JPA, Hibernate Rest API 

# The challenge

Using Spring Boot or Go, and your database of choice (PostgreSQL, MySQL, MongoDB -- any you'd like), develop a microservice for tracking the status of enrollees in a health care program.
- Enrollees must have an id, name, and activation status (true or false), and a birth date
- Enrollees may have a phone number (although they do not have to supply this)
- Enrollees may have zero or more dependents
- Each of an enrollee's dependents must have an id, name, and birth date

The application we will be building will need to be able to do these things:
- Add a new enrollee
- Modify an existing enrollee
- Remove an enrollee entirely
- Add dependents to an enrollee
- Remove dependents from an enrollee
- Modify existing dependents


## Requirements

1. Java - 1.8.x

2. Maven - 3.x.x

3. Mysql - 5.x.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Nishkami/Hospital.git
```

**2. Create Mysql database**
```bash
CREATE DATABASE `hospital` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn clean install
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /patient/getAllData
    
    POST /patient/addPatient
    
    GET /patient/getPatientById/{id}
    
    PUT /patient/updatePatient/{id}
    
    DELETE /patient/deletePatient/{id} //will delete dep as well, 

    POST /patient/addDependent/{id} //patient id
    
    GET /patient/getDependentById/{id} //depe
    
    PUT /patient/updateDependent/{id} // depe
    
    DELETE /patient/deleteDependent/{id} //depe

You can test them using postman or any other rest client.




