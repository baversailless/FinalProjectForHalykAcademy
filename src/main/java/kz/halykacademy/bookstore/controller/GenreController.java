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

    @GetMapping(value = "/genre/all")
    public ResponseEntity<ResponseDTO> getGenres() {
        ResponseDTO response = new ResponseDTO();
        try {
            List<GenreDTO> listDTO = genreService.getGenres();
            response.setData(listDTO);
            response.setSuccessCode("GET ALL GENRES SUCCESS");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new DataNotFoundException("GENRES NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("genre/{genreId}")
    public ResponseEntity<ResponseDTO> getGenre(@PathVariable("genreId") Long genreId) {
        ResponseDTO response = new ResponseDTO();
        try {
            GenreDTO dto = genreService.getGenreById(genreId);
            response.setData(dto);
            response.setSuccessCode("GET GENRE SUCCESSFULLY");
        } catch (Exception e) {
            throw new DataNotFoundException("GENRE NOT FOUND");
        }
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/genre/create", consumes = "application/json")
    public ResponseEntity<ResponseDTO> createNewGenre(@RequestBody GenreDTO genreDTO) throws CreateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            genreService.createGenre(genreDTO);
            response.setSuccessCode("CREATE GENRE SUCCESSFULLY");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new CreateDataFailException("SOMETHING WENT WRONG DURING createGenre");
        }
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(value = "/genre/update", consumes = "application/json")
    public ResponseEntity<ResponseDTO> updateGenre(@RequestBody GenreDTO genreDTO) throws UpdateDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            GenreDTO dto = genreService.updateGenre(genreDTO);
            response.setData(dto);
            response.setSuccessCode("UPDATE GENRE SUCCESSFULLY");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new UpdateDataFailException("Something went wrong with updateGenre");
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/genre/delete/{genreId}")
    public ResponseEntity<ResponseDTO> deleteGenre(@PathVariable("genreId") Long genreId) throws DeleteDataFailException {
        ResponseDTO response = new ResponseDTO();
        try {
            genreService.deleteGenre(genreId);
            response.setSuccessCode("DELETE GENRE SUCCESSFULLY");
        } catch (Exception e) {
            response.setErrorCode("FAIL");
            throw new DeleteDataFailException("Something went wrong with deleteGenrre");
        }
        return ResponseEntity.ok().body(response);
    }

}
