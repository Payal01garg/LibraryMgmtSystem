create database lms;
show databases;
use lms;
CREATE TABLE users (
    id INT PRIMARY KEY ,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password) VALUES
('admin', 'admin123'),
('user', 'user123');
select * from users;

CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    available BOOLEAN NOT NULL
);

INSERT INTO books (title, author, available) VALUES
('One Indian Girl', 'Chetan Bhagat', true),
('A Million Mutinies Now ', 'V.S. Naipaul', true),
('A Passage to England ', 'Nirad C. Chaudhuri', false);

delete from books where id='7';
delete from books where id='8';
delete from books where id='9';
select * from books;

