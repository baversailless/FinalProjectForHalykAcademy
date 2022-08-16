package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.mapper.GenreMapper;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;
    private GenreMapper genreMapper;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper){
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreDTO> getGenres() {
        List<Genre> genreList = genreRepository.findAll();
        return genreMapper.toGenreDTOList(genreList);
    }

    @Override
    public GenreDTO getGenreById(Long id) {
        GenreDTO genreDTO = genreMapper.toDTO(genreRepository.findById(id).orElse(null));
        return genreDTO;
    }

    @Override
    public void createGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.toEntity(genreDTO);
        Genre temp = genreRepository.checkExistedGenre(genreDTO.getName());
        if (temp != null){
            throw new CreateDataFailException("GENRE ALREADY EXISTS");
        }
        genreRepository.saveAndFlush(genre);

    }

    @Override
    public GenreDTO updateGenre(GenreDTO genreDTO) {
        Genre genre = genreMapper.toEntity(genreDTO);
        genreRepository.saveAndFlush(genre);
        return genreDTO;
    }

    @Override
    public void deleteGenre(Long id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre == null){
            throw new DataNotFoundException("There is no genre by this id");
        }
        genreRepository.deleteById(id);
    }
}
