package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;

import java.util.List;

public interface GenreService {
    List<GenreDTO> getGenres();
    GenreDTO getGenreById(Long id);
    void createGenre(GenreDTO genreDTO);
    GenreDTO updateGenre(GenreDTO genreDTO);
    void deleteGenre(Long id);
}
