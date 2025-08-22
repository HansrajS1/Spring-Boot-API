# 🚀 Project Setup Guide

This document will guide you through setting up and running the **Spring Boot Product API** project locally.

---

## 📂 Project Structure

```
src/main/java/com/example/demo/
│── configuration/        # Configuration classes (CORS, Swagger, etc.)
│── Controller/           # REST Controllers (ProductController.java)
│── Model/                # Entities (Product.java)
│── repo/                 # Repositories (ProductRepository.java)
│── Service/              # Services (ProductService.java)
│── DemoApplication.java  # Main Spring Boot application entry point
resources/
│── application.properties # Configuration (DB, server port, etc.)
```

---

## ⚙️ Prerequisites

Before you start, make sure you have the following installed:

- **Java 17+** (JDK)
- **Maven 3.8+**
- **Spring Boot 3.x**
- **MySQL (or H2)** database
- **Postman / cURL** for API testing

---

## 📦 Setup Instructions

1. **Clone the Repository**

   ```bash
   git clone https://github.com/HansrajS1/Spring-Boot-API.git
   ```

2. **Configure Database**

   Edit `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/productdb
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   server.port=8080
   ```

   > 💡 You can also switch to **H2 in-memory DB** for quick testing:

   ```properties
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true
   ```

3. **Build the Project**

   ```bash
   mvn clean install
   ```

4. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

   Or run directly from **IntelliJ / Eclipse** by executing `DemoApplication.java`.

5. **Verify Application**

   Open in browser:

   ```
   http://localhost:8080/
   ```

---

## 🛠️ API Endpoints

### Step 1: Get All Products
```http
GET /step1
```

### Step 2: Filter by Release Date
```http
GET /step2?release_date_start=2024-12-01&release_date_end=2024-12-31
```

### Step 3: Filter by Brands + Dates
```http
GET /step3?brands=Quantum,Stellar&release_date_start=2024-12-01
```

### Step 4: Pagination + Filters
```http
GET /step4?page_size=10&page_number=1&brands=Quantum
```

### Step 5: Merge External APIs
```http
GET /step5?page_size=10&page_number=1
```

### Step 6: Create, Update and Delete Products.
```http
POST /step7/create
PUT /step7/update/1
Delete /step7/delete/1
```

---

## 🧪 Testing with Postman

1. Import API endpoints into Postman.
2. Test with different combinations of query parameters.
3. Verify pagination, filtering, and error handling.

---

[Postman Link](https://spring-boot-3011.postman.co/workspace/Spring-Boot-Workspace~d5569fb9-7445-4b5f-8724-82f7fd0b288e/collection/36928172-afcf459d-bd77-4e39-bff8-18149dae94c0?action=share&creator=36928172) 


