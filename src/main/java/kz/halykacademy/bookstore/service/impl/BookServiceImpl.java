package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void createBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(int id, Book updatedBook) {
        Book book = bookRepository.findById(id).orElse(null);
        book.setAuthorList(updatedBook.getAuthorList());
        book.setPrice(updatedBook.getPrice());
        book.setPublisher(updatedBook.getPublisher());
        book.setTitle(updatedBook.getTitle());
        book.setNumberOfPages(updatedBook.getNumberOfPages());
        book.setReleaseYear(updatedBook.getReleaseYear());
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}
