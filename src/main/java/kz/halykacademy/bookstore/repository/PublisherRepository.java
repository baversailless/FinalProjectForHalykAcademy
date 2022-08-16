package kz.halykacademy.bookstore.repository;

import kz.halykacademy.bookstore.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    @Query(value = "SELECT p FROM Publisher p WHERE LOWER(p.name) LIKE %:name%")
    List<Publisher> findByName(@Param("name") String name);


    @Query(nativeQuery = true,
    value = "SELECT * FROM publisher WHERE name = ?1")
    Publisher checkExistedPublisher(String name);


}
