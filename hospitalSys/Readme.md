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

## How to Run

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

**4. You can build the project and run the tests by running**

```bash
mvn clean package
```
**5. Once successfully built, you can run the service**

```bash
mvn spring-boot:run
```

**6. Check the stdout or boot_example.log file to make sure no exceptions are thrown**

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

Json Sample:
```bash
{
  "id" : "",
  "status" : "true",
  "phoneNumber" : "1234567890",
  "firstName" : "John",
  "lastName": "Doe",
  "createdAt" : "",
  "dateOfBirth" : "01/01/99",
  "updatedAt" : "",
  "dependents" : [
      {
        "id" : "",
        "dateOfBirth": "02/02/99",
        "firstName": "Jane",
        "lastName": "Doe"
      }
  ]
}

```

**To get all patient data**

    GET /patient/getAllData
    
**To add patient data** 

    POST /patient/addPatient
    
**To get patient data using patient id**

    GET /patient/getPatientById/{id}
    
**To update patient data using patient id**

    PUT /patient/updatePatient/{id}
    
**To delete patient data using patient id**
**NOTE: If you delete any patient all dependents for that patient will also be deleted**

    DELETE /patient/deletePatient/{id}  

**To add Dependent data**
**NOTE: Here {id} is patient id**

    POST /patient/addDependent/{id}

**To add patient data**
**NOTE: Here {id} is dependent id**
    GET /patient/getDependentById/{id} 
    
**To add patient data**
**NOTE: Here {id} is dependent id**

    PUT /patient/updateDependent/{id} 
    
**To add patient data**
**NOTE: Here {id} is dependent id**

    DELETE /patient/deleteDependent/{id}

You can test them using postman or any other rest client.




