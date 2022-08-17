package kz.halykacademy.bookstore.controller;
import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.dto.ResponseDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.DeleteDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<AuthorDTO> findByTitle(@PathVariable("name") String name){
        return authorService.findByFIO(name);
    }

    public List<Author> findByGenreList(List<String> genreList){
        return authorService.findByGenreList(genreList);
    }

    @GetMapping("/all")
    public List<AuthorDTO> getAllAuthor() {
        return authorService.getAuthors();
    }

    @GetMapping("/author/{authorId}")
    public AuthorDTO getAuthor(@PathVariable("authorId") Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @PostMapping(value = "/author/create", consumes = "application/json")
    public void createNewAuthor(@RequestBody AuthorDTO authorDTO) throws CreateDataFailException {
        authorService.createAuthor(authorDTO);
    }

    @PutMapping(value = "/author/update", consumes = "application/json")
    public AuthorDTO updateAuthor(@RequestBody AuthorDTO authorDTO) throws UpdateDataFailException {
        return authorService.updateAuthor(authorDTO);
    }

    @DeleteMapping("/author/delete/{authorId}")
    public void deleteAuthor(@PathVariable("authorId") Long authorId) throws DeleteDataFailException {
            authorService.deleteAuthor(authorId);
    }
}
