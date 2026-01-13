# Order Service

Order Service is a Spring Boot microservice responsible for placing orders
and coordinating with User Service and Product Service.

This project demonstrates synchronous inter-service communication using
**RestTemplate** along with centralized exception handling.

---

## Tech Stack
- Java
- Spring Boot 2.7.x
- Spring Data JPA
- MySQL
- RestTemplate
- Eureka Client

---

## Responsibilities
- Place new orders
- Validate user existence via User Service
- Validate product availability via Product Service
- Calculate total order amount with discount
- Persist order details in database

---

## Inter-Service Communication
- Uses **RestTemplate** for HTTP calls between microservices
- Communicates with:
  - **User Service**
  - **Product Service**
- Handles service unavailability and validation errors gracefully

---

## Exception Handling
- Centralized global exception handling using `@RestControllerAdvice`
- Custom exceptions implemented:
  - UserNotFoundException
  - ProductNotFoundException
  - UserServiceDownException
  - ProductServiceDownException
- Returns consistent error response structure

---

## Order Flow
1. Order request received
2. User Service is called to validate user
3. Product Service is called to validate product and quantity
4. Total amount is calculated
5. Order is saved to the database

---

## How to Run
1. Start Eureka Server
2. Start User Service
3. Start Product Service
4. Start Order Service

---

## Future Improvements
- Replace hardcoded service URLs with Eureka service names
- Add timeouts and retries to RestTemplate
- Migrate to Feign Client for declarative REST calls
