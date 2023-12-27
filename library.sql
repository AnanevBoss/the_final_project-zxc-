create database bookk;
use bookk;

create TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
INSERT INTO users (user_id, login, password, role) VALUES ('12', 'mamapapa123', 'mamapapa123!', 'admin');
select * from users;
update users set role = 'employee' where user_id = 13;
CREATE TABLE IF NOT EXISTS authors (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS genres (
    genre_id INT AUTO_INCREMENT PRIMARY KEY,
    genre_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id INT,
    genre_id INT,
    FOREIGN KEY (author_id) REFERENCES authors(author_id),
    FOREIGN KEY (genre_id) REFERENCES genres(genre_id)
);

CREATE TABLE IF NOT EXISTS copies (
    copy_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    is_available BOOLEAN NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

CREATE TABLE IF NOT EXISTS loans (
    loan_id INT AUTO_INCREMENT PRIMARY KEY,
    copy_id INT,
    user_id INT,
    loan_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (copy_id) REFERENCES copies(copy_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS reviews (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    user_id INT,
    review_text TEXT,
    rating INT,
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS rayting_user (
    rayting_user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    rayting varchar(50),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);