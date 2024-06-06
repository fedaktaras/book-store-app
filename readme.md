

![Book Store App.jpeg](readmeImg%2FBook%20Store%20App.jpeg)
# Book Store App
## Project Overview

This project is a Book Store application built using Java and Spring Boot. It is designed to manage a catalog of books, provide user authentication and authorization, and ensure secure access to various operations. The project supports CRUD operations for book management and includes a user-friendly interface for accessing the API documentation through Swagger UI.

* * *

## Technologies and Tools

- **Spring Boot**: Core framework for building the application.
- **Spring Data JPA**: Manages database interactions.
- **Spring Security**: Handles authentication and authorization.
- **Liquibase**: Manages database migrations.
- **Maven**: Project management and dependency resolution.
- **H2 Database**: In-memory database for testing (CI).
- **MySQL**: Main database for the application.
- **Swagger**: API documentation and testing tool.
- **JUnit & Mockito**: For unit testing and mocking.
- **GitHub Actions**: Continuous Integration (CI) tool.
- **Checkstyle**: Ensures code style consistency.

* * *

## Features

- User Registration and Authentication
- Book Browsing and Searching
- Shopping Cart Management
- Order Processing
- User Profile Management
- Admin Dashboard for Managing Books and Orders
- Secure RESTful API Endpoints

* * *

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/fedaktaras/book-store-app
    cd online-bookstore
    ```
2. Configure the database: Create a new MySQL database:
    ```sql
    CREATE DATABASE online_book_store;
    ```
   Update the `application.properties` file with your MySQL database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/online_book_store
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```
3. Install dependencies and build the project:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```
   The server will start on `http://localhost:8080`.

### Using Docker

1. Build the Docker image:
    ```bash
    docker-compose up
    ```

* * *

## Usage

- The app will be installed with a set of data.
- Use Postman or Swagger UI to interact with the API endpoints.
- Refer to the valid JSON files in the `try-me.txt` file (just copy/paste text to the proper endpoint).

## API Documentation

Explore the detailed API documentation generated by Swagger to understand the various endpoints and their functionalities. You can access it at `http://localhost:8080/api/swagger-ui/index.html`.

## Check out demonstration video
In this video you can explore general overview and functionality
`https://www.youtube.com/`

* * *

## Functionalities Overview

### User Endpoints

- Register a new user: `POST /api/auth/register`
- Authenticate a user: `POST /api/auth/login`

### Book Endpoints

- Retrieve book catalog: `GET /api/books`
- Retrieve book details: `GET /api/books/{id}`
- Create a new book: `POST /api/books`
- Update a specific book: `PUT /api/books/{id}`
- Delete a specific book: `DELETE /api/books/{id}`

### Category Endpoints

- Create a new category: `POST /api/categories`
- Retrieve all categories: `GET /api/categories`
- Retrieve a specific category by its ID: `GET /api/categories/{id}`
- Update a specific category: `PUT /api/categories/{id}`
- Delete a specific category: `DELETE /api/categories/{id}`
- Retrieve books by a specific category: `GET /api/categories/{id}/books`

### Shopping Cart Endpoints

- Retrieve user's shopping cart: `GET /api/cart`
- Add a book to the shopping cart: `POST /api/cart`
- Update the quantity of a book in the shopping cart: `PUT /api/cart/cart-items/{cartItemId}`
- Remove a book from the shopping cart: `DELETE /api/cart/cart-items/{cartItemId}`

### Order Endpoints

- Place an order: `POST /api/orders`
- Retrieve user's order history: `GET /api/orders`
- Retrieve all OrderItems for a specific order: `GET /api/orders/{orderId}/items`
- Retrieve a specific OrderItem within an order: `GET /api/orders/{orderId}/items/{itemId}`
- Update order status: `PATCH /api/orders/{id}`

* * *

## CI/CD Configuration

### Checkstyle Configuration

Checkstyle is used in this project. Please check `checkstyle.xml` in the root directory.

### GitHub Actions

GitHub Actions is used in this project. Please check the `.github/workflows/ci.yml` file:

* * *

## Contributors

- Taras Fedak

* * *
