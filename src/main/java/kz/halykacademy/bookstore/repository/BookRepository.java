package kz.halykacademy.bookstore.repository;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

   @Query(
    value = "SELECT b FROM Book b WHERE LOWER(b.title) LIKE %:name%")
    List<Book> findByTitleLikeIgnoreCase(@Param("name") String name);

   @Query(value = "SELECT * FROM book", nativeQuery = true)
    List<Book> getAllBooks();

   @Query(value = "SELECT * FROM book WHERE title = ?1 AND publisher_id = ?2 AND number_of_pages = ?3 AND release_year = ?4 ",
           nativeQuery = true)
   Book checkExistedBook(String title,
                         Long publisherId,
                         int pages,
                         int releaseYear);



    //List<Book> findByGenreList(@Param("genreList") List<String> genreList);
}
