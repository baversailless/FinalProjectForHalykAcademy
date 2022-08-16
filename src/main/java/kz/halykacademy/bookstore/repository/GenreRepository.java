package kz.halykacademy.bookstore.repository;


import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM genre WHERE name = ?1")
    Genre checkExistedGenre(String name);
}
