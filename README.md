# Multirole Learning Portal — Backend (Spring Boot Microservices)

## Overview
Multirole Learning Portal is a microservices-based Learning Management System (LMS) backend built using **Java + Spring Boot**.  
It provides REST APIs for **authentication, users, courses, quizzes, questions, and results**, designed with a modular and scalable architecture.

The system supports role-based workflows for:
- Admin  
- Instructor  
- Student  

---

## Architecture
The backend consists of **6 domain microservices**, plus infrastructure components.

### Domain Services
- **Auth Service** – User registration/login, JWT generation & validation  
- **User Service** – User profile management  
- **Course Service** – Course creation, listing, and enrollment  
- **Quiz Service** – Quiz creation and attempt workflows  
- **Question Service** – Question management and quiz question delivery  
- **Result Service** – Quiz evaluation and result persistence  

### Infrastructure
- **API Gateway** – Centralized routing through a single entry point  
- **Service Registry (Eureka)** – Service discovery for microservices  

---

## Key Features
- JWT-based authentication and secure login/register flow  
- Role-based access control (**Admin / Instructor / Student**)  
- RESTful APIs across services (**38 endpoints**)  
- Inter-service communication using **Eureka + OpenFeign**  
- API Gateway routing for all requests  
- MySQL persistence using **JPA/Hibernate** (**6 entities**)  

---

## Tech Stack
- Java  
- Spring Boot  
- Spring Security (JWT)  
- Spring Cloud (Eureka, OpenFeign, API Gateway)  
- MySQL  
- Maven  
- Git & GitHub  
- Postman (API testing)

---

## Project Scale (From Codebase)
- **6 Controllers**
- **38 REST Endpoints**
- **6 JPA Entities**
- **6 Domain Microservices + API Gateway + Eureka** (**8 components total**)

---

## How to Run Locally (Basic)

### Prerequisites
- Java 17+ (or your configured version)
- Maven
- MySQL

### Start Services (Recommended Order)

#### 1) Service Registry
```bash
cd service-registry
mvn spring-boot:run
2) API Gateway
cd api-gateway
mvn spring-boot:run
3) Domain Microservices
Run each service in a separate terminal:

cd auth-service && mvn spring-boot:run
cd user-service && mvn spring-boot:run
cd course-service && mvn spring-boot:run
cd quiz-service && mvn spring-boot:run
cd question-service && mvn spring-boot:run
cd result-service && mvn spring-boot:run
Notes
Services have been tested locally during development.

Frontend integration is planned using React + Tailwind CSS.

This project is currently under active development.

Author
Anmol Jha
GitHub: https://github.com/anmoljha-21
