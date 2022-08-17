package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.dto.ResponseDTO;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.DeleteDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping(value = "/all")
    public List<GenreDTO> getGenres() {
           return genreService.getGenres();
    }

    @GetMapping("genre/{genreId}")
    public  GenreDTO getGenre(@PathVariable("genreId") Long genreId) {
        return genreService.getGenreById(genreId);
    }

    @PostMapping(value = "/genre/create", consumes = "application/json")
    public void createNewGenre(@RequestBody GenreDTO genreDTO){
            genreService.createGenre(genreDTO);
    }

    @PutMapping(value = "/genre/update", consumes = "application/json")
    public GenreDTO updateGenre(@RequestBody GenreDTO genreDTO){
        return genreService.updateGenre(genreDTO);
    }

    @DeleteMapping("/genre/delete/{genreId}")
    public void deleteGenre(@PathVariable("genreId") Long genreId) throws DeleteDataFailException {
        genreService.deleteGenre(genreId);
    }


}
