package kz.halykacademy.bookstore.dto;

import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String lastName;
    private String firstName;
    private String patronymicName;
    private String dateOfBirth;
    private List<Long> bookList;
    private Set<Long> genreList;




}
