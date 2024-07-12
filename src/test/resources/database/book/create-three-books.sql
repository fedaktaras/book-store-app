INSERT INTO categories (id, name, description)
VALUES (1, 'Educational', 'Educational Literature');

INSERT INTO books
(id, title, author, isbn, price, cover_image, description, is_deleted)
VALUES
    (1, 'Effective Java', 'Joshua Bloch', '978-0134685991', 45.00, 'https://example.com/effective-java.jpg', 'Best practices for Java programming', false),
    (2, 'Clean Code', 'Robert C. Martin', '978-0132350884', 50, 'image2.jpg', 'A Handbook of Agile Software Craftsmanship', false),
    (3, 'Java Concurrency in Practice', 'Brian Goetz', '978-0321349606', 48, 'image.3', 'Comprehensive guide to concurrency in Java', false);

INSERT INTO books_categories (book_id, category_id)
VALUES (1, 1), (2, 1), (3, 1);