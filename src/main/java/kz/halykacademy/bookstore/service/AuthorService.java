package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DeleteDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAuthors();
    AuthorDTO getAuthorById(Long id);
    void createAuthor(AuthorDTO authorDTO) throws CreateDataFailException;
    AuthorDTO updateAuthor(AuthorDTO authorDTO);
    void deleteAuthor(Long id) throws DeleteDataFailException;
    List<AuthorDTO> findByFIO(String name);
    List<Author> findByGenreList(List<String> genreList);
}
