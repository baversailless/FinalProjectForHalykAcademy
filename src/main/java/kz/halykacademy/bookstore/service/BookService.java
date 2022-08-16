package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;

import java.util.List;

public interface BookService {
    List<BookDTO> getBookListToShow();
    BookDTO getBookById(Long id);
    void createBook(BookDTO bookDTO) throws CreateDataFailException;
    BookDTO updateBook(BookDTO bookDTO) throws UpdateDataFailException;
    void deleteBook(Long id);
    List<BookDTO> findByTitle(String name);
}
