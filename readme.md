

![Book Store App.jpeg](readmeImg%2FBook%20Store%20App.jpeg)
# Book Store App
## 🔬 Project Overview

This project is a Book Store application built using Java and Spring Boot. It is designed to manage a catalog of books, provide user authentication and authorization, and ensure secure access to various operations. The project supports CRUD operations for book management and includes a user-friendly interface for accessing the API documentation through Swagger UI.

* * *

## 🛠️ Technologies and Tools

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

## 💡Features

- User Registration and Authentication
- Book Browsing and Searching
- Shopping Cart Management
- Order Processing
- User Profile Management
- Admin Dashboard for Managing Books and Orders
- Secure RESTful API Endpoints

* * *

## 🏗️️ Installation

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
4. Rename .env.sample to .env and customize data if needed (keep the secret untouched to have access to preinstalled data). Add it to environment variables.
5. Run the application:
    ```bash
    mvn spring-boot:run
    ```
   [The server will start on](http://localhost:8080).

### Using Docker

1. Rename .env.sample to .env and customize data if needed (keep the secret untouched to have access to preinstalled data)
2. Build the Docker image:
    ```bash
    docker-compose up --build
    ```

* * *

## 🚗 Usage

- The app will be installed with a set of data.
- Use Postman or Swagger UI to interact with the API endpoints.
- Use postman collection prepared for exploring functionality of the project.
### API Documentation

Explore the detailed API documentation generated by Swagger to understand the various endpoints and their functionalities. You can access it at [Swagger API Documentation](http://localhost:8080/api/swagger-ui/index.html).


### Check out demonstration video

In this video, you can explore a general overview and functionality:

[Watch the video](https://youtu.be/32OPpWPoPNw)

* * *

## 🚀Functionalities Overview

### User Endpoints
This application is a RESTful Spring Boot app, utilizing Spring Security to enforce robust access control mechanisms. The security model is role-based, differentiating between two primary roles: User and Admin.**
#### User Role
- **View Books**: Browse the available book catalog.
- **Shopping Cart**: Add books to the shopping cart.
- **Create Order**: Place orders for books added to the cart.

#### Admin Role
- **Manage Books**: Add, update, or delete books in the catalog.
- **Manage Categories**: Create, modify, and delete book categories.
- **Manage Orders**: View and update the status of customer orders. 

#### Authentication endpoints
- **Register a new user**
   - Request: `POST /api/auth/register`
   - Response: `201 Created`
- **Login a user**
   - Request: `POST /api/auth/login`
   - Response: `200 OK` with JWT token

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

## 🏭 CI/CD Configuration

### Checkstyle Configuration

Checkstyle is used in this project. Please check `checkstyle.xml` in the root directory.

### GitHub Actions

GitHub Actions is used in this project. Please check the `.github/workflows/ci.yml` file:

* * *
### Challenges in the Bookstore Application Project

- **Complex Role-Based Access Control:**
    - Implementing Spring Security to handle different roles (user and admin) with distinct capabilities was challenging. Ensuring that users and admins have the appropriate access to resources and actions required meticulous configuration and testing. This involved setting up different authentication and authorization rules, as well as protecting sensitive endpoints while providing necessary access to each role.

- **Error Handling and Validation:**
    - Implementing comprehensive error handling and input validation to ensure the application is robust and user inputs are correctly processed added to the development complexity. This included managing edge cases, ensuring meaningful error messages are returned, and maintaining a seamless user experience. Proper error handling mechanisms were essential to prevent unexpected crashes and to provide clear feedback to users when issues occurred.
* * *

## 🧑‍💻 Contributors

- [Taras Fedak](https://www.linkedin.com/in/taras-fedak-a4535b147/)

* * *

