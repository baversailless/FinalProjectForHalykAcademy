package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT a FROM Author a WHERE LOWER(a.firstName) LIKE %:name% \n" +
            "                        OR LOWER(a.lastName) LIKE %:name%\n" +
            "                        OR LOWER(a.patronymicName) LIKE %:name%")
    List<Author> findByFIO(@Param("name") String name);


    //List<Genre> findAllByBookList


    @Query(value = "SELECT DISTINCT (author) FROM author\n" +
            "INNER JOIN book_author ba on author.id = ba.author_id\n" +
            "INNER JOIN book_genre bg on ba.book_id = bg.book_id\n" +
            "WHERE genre_id IN (:genreList)\n"
            ,nativeQuery = true)
    List<Author> findByGenreList(@Param("genreList") List<String> genreList);

    @Query(value = "SELECT * FROM author WHERE first_name = ?1 AND last_name = ?2",
            nativeQuery = true)
    Author checkExistedAuthor(String firstName, String lastName);



}
