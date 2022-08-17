package kz.halykacademy.bookstore.controller;
import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.ResponseDTO;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.DeleteDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.mapper.BookMapper;
import kz.halykacademy.bookstore.service.AuthorService;
import kz.halykacademy.bookstore.service.BookService;
import kz.halykacademy.bookstore.service.GenreService;
import kz.halykacademy.bookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }


    @GetMapping(value = "/findByTitle/{title}")
    public List<BookDTO> findByTitle(@PathVariable("title") String name) {
        return bookService.findByTitle(name);
    }

    @GetMapping(value = "/all")
    public List<BookDTO> getAllBook() {
        return bookService.getBookListToShow();
    }

    @GetMapping("/book/{bookId}")
    public BookDTO getBook(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping(value = "/book/create", consumes = "application/json")
    public void createNewBook(@RequestBody BookDTO bookDTO) {
        bookService.createBook(bookDTO);
    }

    @PutMapping(value = "/book/update", consumes = "application/json")
    public BookDTO updateBook(@RequestBody BookDTO bookDTO){
        return bookService.updateBook(bookDTO);
    }

    @DeleteMapping("/book/delete/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
    }



}
