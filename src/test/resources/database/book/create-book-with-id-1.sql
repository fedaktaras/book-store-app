INSERT INTO categories (id, name, description)
VALUES (1, 'Educational', 'Educational Literature');

INSERT INTO books
    (id, title, author, isbn, price, cover_image, description, is_deleted)
VALUES
    (1, 'Effective Java', 'Joshua Bloch', '978-0134685991', 45.00,
     'https://example.com/effective-java.jpg', 'Best practices for Java programming', false);

INSERT INTO books_categories (book_id, category_id)
VALUES (1, 1);