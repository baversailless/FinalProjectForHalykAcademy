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
    public ResponseEntity<ResponseDTO> findByTitle(@PathVariable("title") String name){
        ResponseDTO response = new ResponseDTO();
        try {
            List<BookDTO> listDTO = bookService.findByTitle(name);
            response.setData(listDTO);
            response.setSuccessCode("GET ALL BOOKS SUCCESS");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new DataNotFoundException("BOOKS NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<ResponseDTO> getAllBook() {
        ResponseDTO response = new ResponseDTO();
        try {
             List<BookDTO> listDTO = bookService.getBookListToShow();
             response.setData(listDTO);
             response.setSuccessCode("GET ALL BOOKS SUCCESS");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new DataNotFoundException("BOOKS NOT FOUND");
        }
        return ResponseEntity.ok().body(response);

    }

    @PostMapping(value = "/book/create", consumes = "application/json")
    public ResponseEntity<ResponseDTO> createNewBook(@RequestBody BookDTO bookDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            bookService.createBook(bookDTO);
            response.setSuccessCode("Success");
        } catch (Exception e) {
            response.setErrorCode("Fail");
            throw new CreateDataFailException("SOMETHING WENT WRONG DURING createBook");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<ResponseDTO> getBook(@PathVariable("bookId") Long bookId) {
        ResponseDTO response = new ResponseDTO();
        try {
            BookDTO dto = bookService.getBookById(bookId);
            response.setData(dto);
            response.setSuccessCode("Success");
        } catch (Exception e) {
            throw new DataNotFoundException("BOOK NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }


    @PutMapping(value = "/book/update", consumes = "application/json")
    public ResponseEntity<ResponseDTO> updateBook(@RequestBody BookDTO bookDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            BookDTO dto =  bookService.updateBook(bookDTO);
            response.setData(dto);
            response.setSuccessCode("Success");
        } catch (Exception e) {
            response.setErrorCode("Fail");
            throw new UpdateDataFailException("Something went wrong with updateBook");
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/book/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable("bookId") Long bookId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            bookService.deleteBook(bookId);
            response.setSuccessCode("Success");
        } catch (Exception e) {
            response.setErrorCode("Fail");
            throw new DeleteDataFailException("Something went wrong with deleteBook");
        }
        return ResponseEntity.ok().body(response);
    }



}
