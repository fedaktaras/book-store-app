INSERT INTO users (email, password, first_name, last_name, shipping_address, is_deleted) VALUES
('oleg@example.com', '$2a$10$XMhpFU0GAz9y1ys43uEHc.xApDOEcD0gYyBSWBliM6wwfPxHzn7kW', 'Bob', 'Johnson', '5678 Fourth Ave, Next City, Country', FALSE);

INSERT INTO users_roles (user_id, roles_id)
VALUES ((SELECT id FROM users WHERE email = 'oleg@example.com'), 2);




-- Insert new categories
INSERT INTO categories (name, description, is_deleted)
VALUES
    ('Fantasy', 'Fantasy books', FALSE),
    ('Science Fiction', 'Sci-Fi books', FALSE),
    ('Educational Literature', 'Books for educational purposes', FALSE),
    ('Poetry', 'Poetry books', FALSE),
    ('Horror', 'Horror books', FALSE);

-- Insert new books
INSERT INTO books (title, author, isbn, price, cover_image, description, is_deleted)
VALUES
    -- Java books
    ('Effective Java', 'Joshua Bloch', '978-0134685991', 45.00, 'https://example.com/effective-java.jpg',
     'Best practices for Java programming', FALSE),
    ('Java: The Complete Reference', 'Herbert Schildt', '978-1260440232', 60.00, 'https://example.com/complete-reference.jpg',
     'Comprehensive guide to Java programming', FALSE),
    ('Head First Java', 'Kathy Sierra', '978-0596009205', 35.00, 'https://example.com/head-first-java.jpg',
     'Introduction to Java programming', FALSE),

    -- Poetry books
    ('The Complete Works of Oscar Wilde', 'Oscar Wilde', '978-0007144365', 25.00, 'https://example.com/oscar-wilde.jpg',
     'Collected works of Oscar Wilde', FALSE),
    ('The Raven and Other Poems', 'Edgar Allan Poe', '978-0486266855', 10.00, 'https://example.com/edgar-poe.jpg',
     'Collection of Edgar Allan Poe poems', FALSE),
    ('Leaves of Grass', 'Walt Whitman', '978-0486456768', 15.00, 'https://example.com/walt-whitman.jpg',
     'Collection of Walt Whitman poems', FALSE),

    -- Horror books
    ('The Shining', 'Stephen King', '978-0307743657', 20.00, 'https://example.com/the-shining.jpg',
     'Horror novel by Stephen King', FALSE),
    ('It', 'Stephen King', '978-1501142970', 25.00, 'https://example.com/it.jpg',
     'Horror novel about a malevolent entity', FALSE),
    ('Misery', 'Stephen King', '978-1501143106', 18.00, 'https://example.com/misery.jpg',
     'Thriller novel by Stephen King', FALSE),

    -- Fantasy and Sci-Fi books
    ('The Hobbit', 'J.R.R. Tolkien', '978-0547928227', 15.00, 'https://example.com/the-hobbit.jpg',
     'Fantasy novel by J.R.R. Tolkien', FALSE),
    ('Dune', 'Frank Herbert', '978-0441013593', 10.00, 'https://example.com/dune.jpg',
     'Sci-Fi novel by Frank Herbert', FALSE),
    ('Ender\'s Game', 'Orson Scott Card', '978-0812550702', 12.00, 'https://example.com/enders-game.jpg',
     'Sci-Fi novel by Orson Scott Card', FALSE),

    -- Books with multiple categories (Fantasy + Horror)
    ('The Dark Tower', 'Stephen King', '978-1501161806', 22.00, 'https://example.com/dark-tower.jpg',
     'Fantasy and horror novel by Stephen King', FALSE),
    ('American Gods', 'Neil Gaiman', '978-0062572233', 18.00, 'https://example.com/american-gods.jpg',
     'Fantasy and horror novel by Neil Gaiman', FALSE);

-- Insert books categories relations for Java books
INSERT INTO books_categories (book_id, category_id) VALUES
((SELECT id FROM books WHERE title = 'Effective Java'),
(SELECT id FROM categories WHERE name = 'Educational Literature')),
((SELECT id FROM books WHERE title = 'Java: The Complete Reference'),
(SELECT id FROM categories WHERE name = 'Educational Literature')),
((SELECT id FROM books WHERE title = 'Head First Java'),
(SELECT id FROM categories WHERE name = 'Educational Literature'));

-- Insert books categories relations for Poetry books
INSERT INTO books_categories (book_id, category_id) VALUES
((SELECT id FROM books WHERE title = 'The Complete Works of Oscar Wilde'),
(SELECT id FROM categories WHERE name = 'Poetry')),
((SELECT id FROM books WHERE title = 'The Raven and Other Poems'),
(SELECT id FROM categories WHERE name = 'Poetry')),
((SELECT id FROM books WHERE title = 'Leaves of Grass'),
(SELECT id FROM categories WHERE name = 'Poetry'));

-- Insert books categories relations for Horror books
INSERT INTO books_categories (book_id, category_id) VALUES
((SELECT id FROM books WHERE title = 'The Shining'),
(SELECT id FROM categories WHERE name = 'Horror')),
((SELECT id FROM books WHERE title = 'It'),
(SELECT id FROM categories WHERE name = 'Horror')),
((SELECT id FROM books WHERE title = 'Misery'),
(SELECT id FROM categories WHERE name = 'Horror'));

-- Insert books categories relations for Fantasy books
INSERT INTO books_categories (book_id, category_id) VALUES
    ((SELECT id FROM books WHERE title = 'The Hobbit'),
     (SELECT id FROM categories WHERE name = 'Fantasy'));

-- Insert books categories relations for Sci-Fi books
INSERT INTO books_categories (book_id, category_id) VALUES
((SELECT id FROM books WHERE title = 'Dune'),
(SELECT id FROM categories WHERE name = 'Science Fiction')),
((SELECT id FROM books WHERE title = 'Ender\'s Game'),
(SELECT id FROM categories WHERE name = 'Science Fiction'));

-- Insert books categories relations for Fantasy + Horror books
INSERT INTO books_categories (book_id, category_id) VALUES
((SELECT id FROM books WHERE title = 'The Dark Tower'),
(SELECT id FROM categories WHERE name = 'Fantasy')),
((SELECT id FROM books WHERE title = 'The Dark Tower'),
(SELECT id FROM categories WHERE name = 'Horror')),
((SELECT id FROM books WHERE title = 'American Gods'),
(SELECT id FROM categories WHERE name = 'Fantasy')),
((SELECT id FROM books WHERE title = 'American Gods'),
(SELECT id FROM categories WHERE name = 'Horror'));
