package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();
    Author getAuthorById(int id);
    void createAuthor(Author author);
    void updateAuthor(int id, Author author);
    void deleteAuthor(int id);
}
