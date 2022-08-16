INSERT INTO author (last_name, first_name, patronymic_name, date_of_birth)
VALUES ('Pushkin', 'Aleksandr', 'Sergeyevich', '1799-06-06'),
       ('Tolstoy', 'Lev', 'Nikolayevich', '1828-09-09'),
       ('Dostoyevsky', 'Fyodor', 'Mikhaylovich', '1821-11-11'),
       ('Gogol', 'Nikolay', 'Vasilyevich','1809-03-20'),
       ('Turgenev', 'Ivan', 'Sergeyevich', '1818-11-09'),
       ('Lermontov', 'Yuryevich', 'Mikhail', '1814-10-15'),
       ('Chekhov', 'Anton', 'Pavlovich', '1860-01-29'),
       ('Peshkov', 'Alexei', 'Maximovich', '1868-03-16'),
       ('Christie', 'Agatha', 'Dame', '1890-09-15'),
       ('Shelley', 'Mary', 'Wollstonecraft', '1797-08-30'),
       ('Austen', 'Jane', DEFAULT, '1775-12-16'),
       ('King', 'Stephen', 'Edwin', '1947-09-21');


INSERT INTO publisher (name)
VALUES ('The Russian Messenger'),
       ('ACT'),
       ('Azbuka'),
       ('HarperCollins'),
       ('Lackington'),
       ('Simon & Schuster'),
       ('Doubleday');

INSERT INTO book (price, title, number_of_pages, release_year, publisher_id)
VALUES (1770, 'Crime and Punishment', 492, 1866, 1),
       (2120, 'The Brothers Karamazov',  824,  1880, 1),
       (1850, 'White nights', 64, 1848, 1),
       (2000, 'War and Peace',1225, 1867,  1),
       (3500, 'The Captain''s Daughter', 320, 1836, 3),
       (7000, 'Dead Souls', 432 ,1842, 3),
       (5300, 'Fathers and Sons', 226, 1862, 1),
       (4800, 'A Hero of Our Time', 156, 1840, 2),
       (5100, 'Chameleon', 416, 1884, 2),
       (6100,'Childhood', 178, 1913, 2),
       (2450,'Murder on the Orient Express', 256, 1934, 4),
       (6200, 'Death on the Nile', 288, 1937, 4),
       (4700, ' Frankenstein', 280, 1818, 5),
       (5000, 'It', 1138, 1986, 6),
       (4000, 'The Shining', 447, 1977, 7);

INSERT INTO genre (name)
VALUES ('Literary fiction'),
       ('Romance'),
       ('Crime'),
       ('Philosophical novel'),
       ('Historical fiction'),
       ('Satire'),
       ('Gothic literature'),
       ('Horror'),
       ('Supernatural fiction'),
       ('Detective');

INSERT INTO book_genre (book_id, genre_id)
VALUES (1, 1),
       (1, 4),
       (2, 1),
       (2, 2),
       (2, 4),
       (3, 1),
       (4, 2),
       (4, 4),
       (4, 5),
       (5, 2),
       (6, 2),
       (6, 6),
       (6, 1),
       (7, 2),
       (7, 4),
       (8, 2),
       (8, 4),
       (9, 4),
       (10, 5),
       (11, 3),
       (11, 10),
       (12, 3),
       (13, 7),
       (13, 8),
       (14, 8),
       (14, 9),
       (15, 8);


INSERT INTO book_author (book_id, author_id)
VALUES (1, 3),
       (2, 3),
       (3, 3),
       (4, 2),
       (5, 1),
       (6, 4),
       (7, 5),
       (8, 6),
       (9, 7),
       (10, 2),
       (11, 9),
       (12, 9),
       (13, 10),
       (14, 12),
       (15, 12);





