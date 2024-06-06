
# BookStore: A Comprehensive Online Bookstore

## Introduction
BookStore is a fully-featured Spring Boot application tailored for managing online bookstore operations efficiently. This project provides a platform for users to explore, manage their purchases, and handle book listings through an intuitive API.

## Core Features
- **User Management:** Facilities for user registration and authentication.
- **Book Operations:** Admin capabilities to manage book entries.
- **Category Handling:** Organize books into categories for easier navigation.
- **Shopping Cart System:** Enables users to collect and manage purchases before checkout.
- **Order Processing:** Secure order placement and tracking.

## Technology Stack
- **Spring Boot**: Framework for building Java-based applications easily.
- **Spring Security**: Securing endpoints and managing authentication.
- **JWT (JSON Web Tokens)**: For secure transmission of information.
- **Spring Data JPA**: To handle database operations using Java Persistence API.
- **Swagger**: API documentation for visualizing RESTful services.

## Installation Guide

### Requirements
- Java 17
- Maven 3.2+

### How to Setup Locally
1. **Clone the repository:**
   ```bash
   git clone https://github.com/fedaktaras/book-store-app/tree/master/src/main
   cd bookstoreapp
   ```

2. **Build the application:**
   ```bash
   mvn package
   ```

3. **Run the application:**
   ```bash
   docker-compose up
   ```

4. **Access the API:**
    - Visit [Swagger UI](http://localhost:8080/api/swagger-ui/index.html#/) to view and interact with the API.

## API Documentation
The application provides detailed API documentation available through Swagger UI. This allows for easy testing and interaction with the various endpoints:

- **Auth**: `/auth`
    - Register
    - Login
- **Books**: `/books`
    - Add, Remove, Update, and View books
- **Categories**: `/categories`
    - Add, Remove, Update, and View categories
- **Orders**: `/orders`
    - Place and view orders
- **Cart**: `/cart`
    - Manage shopping cart items


## Demonstration
### Create Horror Category
```json
{
  "name": "Horror",
  "description": "Books that evoke feelings of fear, dread, or terror through elements such as supernatural beings, psychological suspense, and gruesome imagery."
}
```
### Create Fantasy Category
```json
{
  "name": "Fantasy",
  "description": "Books that involve magical or supernatural elements, often set in imaginary worlds with their own rules and mythology."
}
```
### Create Drama Category
```json
{
  "name": "Drama",
  "description": "Books that focus on interpersonal conflicts, emotional experiences, and character development, often exploring themes of love, tragedy, and human relationships."
}
```
### Create Book
```json
{
  "id": 2,
  "title": "Under the Dome",
  "price": 14.99,
  "author": "Stephen King",
  "isbn": "978-1-4391-4888-8",
  "description": "A a small town becomes mysteriously encapsulated under an impenetrable dome, leading to chaos and paranoia among its inhabitants.",
  "coverImage": "https://example.com/under_the_dome.jpg",
  "categoryIds": [1, 2]
}
```
### Create Book
```json
{
  "id": 1,
  "title": "The Stand",
  "price": 15.99,
  "author": "Stephen King",
  "isbn": "978-0-385-12168-2",
  "description": "A post-apocalyptic novel depicting a world ravaged by a deadly pandemic and the survivors' struggle between good and evil.",
  "coverImage": "https://example.com/the_stand.jpg",
  "categoryIds": [1, 2]
}
```
### Create Book
```json
{
  "title": "The Alchemist",
  "price": 11.99,
  "author": "Paulo Coelho",
  "isbn": "978-0-06-231500-7",
  "description": "A philosophical novel that follows the journey of Santiago, a young Andalusian shepherd, as he embarks on a quest to find his personal legend and fulfill his dreams.",
  "coverImage": "https://example.com/the_alchemist.jpg",
  "categoryIds": [1, 3]
}
```

## Contributing
Contributions are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License
Distributed under the MIT License. 