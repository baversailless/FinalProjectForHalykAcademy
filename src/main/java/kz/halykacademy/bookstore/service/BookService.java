package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.repository.BookRepository;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookById(int id);
    void createBook(Book book);
    void updateBook(int id, Book book);
    void deleteBook(int id);
}
