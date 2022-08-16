package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;
    private GenreRepository genreRepository;

    @Autowired
    public BookMapper(AuthorRepository authorRepository,
                      PublisherRepository publisherRepository,
                      GenreRepository genreRepository){
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
        this.genreRepository = genreRepository;
    }

    public BookDTO toDTO(Book book){
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setPrice(book.getPrice());
        dto.setAuthorsId(book.getAuthorList().stream().map(Author::getId).collect(Collectors.toList()));
        dto.setGenresId(book.getGenreList().stream().map(Genre::getId).collect(Collectors.toList()));
        dto.setPublisherId(book.getPublisher().getId());
        dto.setTitle(book.getTitle());
        dto.setNumberOfPages(book.getNumberOfPages());
        dto.setReleaseYear(book.getReleaseYear());
        return dto;
    }

    public  Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setPrice(bookDTO.getPrice());
        book.setPublisher(publisherRepository.findById(bookDTO.getPublisherId()).orElse(null));
        book.setAuthorList(authorRepository.findAllById(bookDTO.getAuthorsId()));
        book.setGenreList(genreRepository.findAllById(bookDTO.getGenresId()));
        book.setTitle(bookDTO.getTitle());
        book.setNumberOfPages(bookDTO.getNumberOfPages());
        book.setReleaseYear(bookDTO.getReleaseYear());
        return book;
    }

    public List<BookDTO> toBookDTOList(List<Book> bookList){
        List<BookDTO> dtoList = bookList.stream().map(book -> toDTO(book)).collect(Collectors.toList());
        return dtoList;
    }

    public List<Book> toEntityList(List<BookDTO> bookList) {
        List<Book> books = bookList.stream().map(book -> toEntity(book)).collect(Collectors.toList());
        return books;
    }
}
