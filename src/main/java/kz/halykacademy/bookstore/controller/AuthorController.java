package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.constants.ErrorCode;
import kz.halykacademy.bookstore.constants.SuccessCode;
import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.ResponseDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.DeleteDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @GetMapping(value = "/findByFIO/{name}")
    public ResponseEntity<ResponseDTO> findByTitle(@PathVariable("name") String name){
        ResponseDTO response = new ResponseDTO();
        try {
            List<AuthorDTO> listDTO = authorService.findByFIO(name);
            response.setData(listDTO);
            response.setSuccessCode("GET ALL AUTHORS SUCCESS");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new DataNotFoundException("AUTHORS NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }


    public List<Author> findByGenreList(List<String> genreList){
        return authorService.findByGenreList(genreList);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllAuthor() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<AuthorDTO> listDTO = authorService.getAuthors();
            response.setData(listDTO);
            response.setSuccessCode("GET ALL AUTHORS SUCCESS");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new DataNotFoundException("AUTHORS NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<ResponseDTO> getAuthor(@PathVariable("authorId") Long authorId) {
        ResponseDTO response = new ResponseDTO();
        try {
            AuthorDTO dto = authorService.getAuthorById(authorId);
            response.setData(dto);
            response.setSuccessCode("Success");
        } catch (Exception e) {
            throw new DataNotFoundException("AUTHOR NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/author/create", consumes = "application/json")
    public ResponseEntity<ResponseDTO> createNewAuthor(@RequestBody AuthorDTO authorDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            authorService.createAuthor(authorDTO);
            response.setSuccessCode("Success");
        } catch (Exception e) {
            response.setErrorCode("Fail");
            throw new CreateDataFailException("SOMETHING WENT WRONG DURING createAuthor");
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/author/update", consumes = "application/json")
    public ResponseEntity<ResponseDTO> updateAuthor(@RequestBody AuthorDTO authorDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            AuthorDTO dto =  authorService.updateAuthor(authorDTO);
            response.setData(dto);
            response.setSuccessCode("Success");
        } catch (Exception e) {
            response.setErrorCode("Fail");
            throw new UpdateDataFailException("Something went wrong with updateAuthor");
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/author/delete/{authorId}")
    public ResponseEntity<ResponseDTO> deleteAuthor(@PathVariable("authorId") Long authorId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            authorService.deleteAuthor(authorId);
            response.setSuccessCode("Success");
        } catch (Exception e) {
            response.setErrorCode("Fail");
            throw new DeleteDataFailException("Something went wrong with deleteAuthor");
        }
        return ResponseEntity.ok().body(response);
    }



}
