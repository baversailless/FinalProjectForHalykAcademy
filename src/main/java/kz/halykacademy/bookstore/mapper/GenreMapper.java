package kz.halykacademy.bookstore.mapper;
import kz.halykacademy.bookstore.dto.GenreDTO;
import kz.halykacademy.bookstore.entity.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {

    public GenreDTO toDTO(Genre genre){
        GenreDTO dto = new GenreDTO();
        dto.setId(genre.getId());
        dto.setName(genre.getName());
        return dto;
    }

    public Genre toEntity(GenreDTO genreDTO){
        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        return genre;
    }

    public List<GenreDTO> toGenreDTOList(List<Genre> genreList){
        List<GenreDTO> dtoList = genreList.stream().map(genre -> toDTO(genre)).collect(Collectors.toList());
        return dtoList;
    }
}
