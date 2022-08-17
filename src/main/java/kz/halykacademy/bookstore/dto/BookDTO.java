package kz.halykacademy.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private int price;
    private String title;
    private int numberOfPages;
    private int releaseYear;
    private List<String> authorNames;
    private List<String> GenreNames;
    private Long publisherId;
    private List<Long> authorsId;
    private List<Long> genresId;





}
