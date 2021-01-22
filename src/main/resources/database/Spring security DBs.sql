  
CREATE DATABASE IF NOT EXISTS db_test;
use db_test;
CREATE TABLE users
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name       VARCHAR(100)                       NOT NULL,
    lastName   VARCHAR(100)                       NOT NULL,
    age        BIGINT                             NOT NULL,
    hobby      VARCHAR(100)                       NOT NULL,
    username   VARCHAR(100)                       NOT NULL,
    password   VARCHAR(100)                       NOT NULL
)
    COLLATE = 'utf8_general_ci';

-- Table: roles
CREATE TABLE roles
(
    id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role VARCHAR(255) NOT NULL
)
    ENGINE = InnoDB;


-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id),

    UNIQUE (user_id, role_id)
)
    ENGINE = InnoDB;


-- Insert data
INSERT INTO users
VALUES (1, 'Beep', 'Hiver', '16', 'Joke', 'Beep', '{noop}test123'),
       (2, 'Drow', 'Nameless', '34', 'Origami', 'Drow', '{noop}test123');

INSERT INTO roles
VALUES (1, 'ROLE_USER');
INSERT INTO roles
VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles
VALUES (1, 2);
INSERT INTO user_roles
VALUES (1, 1);
INSERT INTO user_roles
VALUES (2, 1);