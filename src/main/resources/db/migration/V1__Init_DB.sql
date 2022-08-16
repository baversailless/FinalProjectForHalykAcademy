CREATE TABLE IF NOT EXISTS author
(
    id BIGSERIAL PRIMARY KEY,
    last_name CHARACTER VARYING(100) NOT NULL,
    first_name CHARACTER VARYING(100) NOT NULL,
    patronymic_name CHARACTER VARYING(100),
    date_of_birth CHARACTER VARYING(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS publisher
(
    id BIGSERIAL PRIMARY KEY,
    name CHARACTER VARYING(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS book
(
    id BIGSERIAL PRIMARY KEY,
    price INTEGER NOT NULL,
    title CHARACTER VARYING(100) NOT NULL,
    number_of_pages INTEGER,
    release_year INTEGER,
    publisher_id BIGINT REFERENCES "publisher" (id)
);


CREATE TABLE IF NOT EXISTS book_author
(
    book_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    CONSTRAINT fk_book_id
    FOREIGN KEY (book_id) REFERENCES book,
    CONSTRAINT fk_author_id
    FOREIGN KEY (author_id) REFERENCES author
);


CREATE TABLE IF NOT EXISTS genre
(
    id BIGSERIAL PRIMARY KEY,
    name CHARACTER VARYING(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS book_genre
(
    book_id BIGINT NOT NULL,
    genre_id BIGINT NOT NULL,
    CONSTRAINT fk_book_id
    FOREIGN KEY (book_id) REFERENCES book,
    CONSTRAINT fk_genre_id
    FOREIGN KEY (genre_id) REFERENCES genre

);


CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY,
    username CHARACTER VARYING(50) UNIQUE NOT NULL,
    password CHARACTER VARYING(100) NOT NULL,
    role CHARACTER VARYING(50) NOT NULL,
    is_blocked BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS orders(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id),
    order_status CHARACTER VARYING(50) NOT NULl DEFAULT 'CREATED',
    creation_date TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
    );


CREATE TABLE IF NOT EXISTS order_book(
    order_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    CONSTRAINT fk_book_id
    FOREIGN KEY (book_id) REFERENCES book,
    CONSTRAINT fk_order_id
    FOREIGN KEY (order_id) REFERENCES orders
    );








