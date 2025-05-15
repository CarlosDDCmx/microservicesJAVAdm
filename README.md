# E-Commerce Microservices Application

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.5-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A scalable e-commerce platform built with Spring Boot microservices architecture, featuring user registration, authentication, and payment processing.

## Features

- **Core Services**
  - **Product Service**
    - Product catalog management
    - CRUD operations for products
    - Product search and filtering
    - Inventory tracking (basic)

  - **User Service**
    - User registration and authentication
    - JWT-based security with refresh tokens
    - Role-based access control (RBAC)
    - OAuth2 social login integration

  - **Order Service**
    - Order creation and management
    - Order status tracking
    - Order history

  - **Payment Service**
    - Mock payment processing
    - Event-driven architecture with RabbitMQ
    - Transaction history tracking

## Technologies

- **Backend**
  - Spring Boot 3.4.5+
  - Spring Security 6+
  - Spring Data JPA
  - H2 Database (embedded)
  - RabbitMQ
  - Eureka Server
  - OpenFeign

- **Security**
  - JWT Authentication
  - OAuth2 Client
  - BCrypt password encoding
  - CSRF protection

- **Tools**
  - Maven
  - Postman (for API testing)

## Architecture Overview

```plaintext
                            +-----------------+
                            |   API Gateway   |
                            +--------+--------+
                                     |
         +---------------------------+---------------------------+
         |                           |                           |
+--------v---------+        +--------v---------+        +--------v---------+
|  Product Service |        |   User Service   |        |  Order Service   |
+------------------+        +------------------+        +------------------+
| - Product Catalog|        | - Authentication |        | - Order Management
| - Inventory      |        | - User Management|        | - Order Tracking
| - Search         |        | - JWT Generation |        +------------------+
+------------------+        +------------------+
         |                           |
         |                           |
+--------v---------+        +--------v---------+
| Payment Service  |        |   RabbitMQ      |
+------------------+        +------------------+
| - Payment Processing
| - Transaction Log
+------------------+
```

## Getting Started

### Prerequisites

- Java 21
- Maven 3.9+
- RabbitMQ 5+
- Eureka Server running on port 8761
- Services recommended run starting on port 8081 onwards

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/<path>/ecommerce-microservices.git
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Start services in order**
   ```bash
   # Start Eureka Server first
   mvn spring-boot:run -pl discovery-server
  
   # Then start other services
   mvn spring-boot:run -pl api-gateway
   mvn spring-boot:run -pl user-service
   mvn spring-boot:run -pl payment-service
   ```

### Configuration

**Database Configuration**

**Product Service H2 Console**
- URL: `http://localhost:8082/h2-console`
- JDBC URL: `jdbc:h2:mem:productdb`
- User: `sa`
- Password: (empty)

**Common application.yml settings**
```yaml
# For H2 Database console
spring:
  h2:
    console:
      enabled: true
      path: /h2-console

# Eureka Client configuration
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

**JWT Configuration (user-service)**
```yaml
jwt:
  secret: your-secure-secret-key
  expiration: 86400000 # 24 hours
```


## API Documentation

### User Service Endpoints

| Method | Endpoint               | Description                     |
|--------|------------------------|---------------------------------|
| POST   | /api/users/register    | Register new user               |
| POST   | /api/users/login       | Authenticate user               |
| POST   | /api/users/refresh     | Refresh access token            |
| POST   | /api/users/logout      | Invalidate current token        |

**Example Registration Request**
```bash
curl -X POST http://localhost:8081/api/users/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user@example.com",
    "password": "SecurePass123!",
    "name": "John Doe"
  }'
```

### Product Service Endpoints

| Method | Endpoint               | Description                     |
|--------|------------------------|---------------------------------|
| GET    | /api/products          | Get all products                |
| GET    | /api/products/{id}     | Get product by ID               |
| POST   | /api/products          | Create new product (Admin)      |
| PUT    | /api/products/{id}     | Update product (Admin)          |
| DELETE | /api/products/{id}     | Delete product (Admin)          |

**Example Product Creation**
```bash
curl -X POST http://localhost:8081/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <ADMIN_TOKEN>" \
  -d '{
    "name": "Smartphone",
    "description": "Flagship device",
    "price": 799.99,
    "stock": 100
  }'
```

### Payment Service Endpoints

| Method | Endpoint               | Description                     |
|--------|------------------------|---------------------------------|
| POST   | /api/payments          | Process payment                 |
| GET    | /api/payments/{orderId}| Get payment status              |

**Example Payment Request**
```bash
curl -X POST http://localhost:8081/api/payments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -d '{
    "orderId": "ORD-12345",
    "amount": 99.99
  }'
```

## Database Access

**User Service H2 Console**
- URL: `http://localhost:8081/h2-console`
- JDBC URL: `jdbc:h2:mem:userdb`
- User: `sa`
- Password: (empty)

**Payment Service H2 Console**
- URL: `http://localhost:8083/h2-console`
- JDBC URL: `jdbc:h2:mem:paymentdb`
- User: `sa`
- Password: (empty)

### Service Relationships

```plaintext
[Product Service] <--> [API Gateway] <--> [Client]
       ^
       |
[Order Service] --> Creates orders using product data
       |
       v
[Payment Service] processes payments for orders
```

## Security

- **JWT Authentication**: Required for all endpoints except registration/login
- **Roles**:
    - `ROLE_USER`: Basic access
    - `ROLE_ADMIN`: Full access
- **OAuth2 Providers**: Google, GitHub (configure client IDs in application.yml)

---

**Note**: Replace placeholder values (JWT secret, OAuth2 client IDs) with actual credentials in production environments.

## License 

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
