package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.constants.ErrorCode;
import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.DeleteDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.mapper.AuthorMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;
    private BookRepository bookRepository;
    private GenreRepository genreRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository,
                             AuthorMapper authorMapper,
                             BookRepository bookRepository,
                             GenreRepository genreRepository){
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public List<AuthorDTO> findByFIO(String name) {
        List<AuthorDTO> listDTO;
        try {
            List<Author> authorList = authorRepository.findByFIO(name.toLowerCase());
            listDTO = authorMapper.toAuthorDTOList(authorList);
        } catch (Exception e){
            throw new DataNotFoundException("Something went wrong with findByFIO");
        }
        return listDTO;
    }

    @Override
    public List<AuthorDTO> getAuthors() {
        List<AuthorDTO> listDTO;
        try {
            List<Author> author = authorRepository.findAll();
            listDTO = authorMapper.toAuthorDTOList(author);

        } catch (Exception e) {
            throw new DataNotFoundException("Something went wrong with getsAuthors");
        }
        return listDTO;
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        AuthorDTO dto;
        try {
        Author author = authorRepository.findById(id).orElse(null);
            if (author == null) {
                throw new DataNotFoundException("Author not found");
            } else {
                dto = authorMapper.toDTO(author);
            }
        } catch (Exception e){
            throw new DataNotFoundException("Something went wrong");
        }
        return dto;
    }

    @Override
    public void createAuthor(AuthorDTO authorDTO) throws CreateDataFailException {
        try {
            Author author = authorMapper.toEntity(authorDTO);
            Author temp = authorRepository.checkExistedAuthor(author.getFirstName(), author.getLastName());
            if (temp != null){
                throw new CreateDataFailException("AUTHOR ALREADY EXISTS");
            }
            authorRepository.saveAndFlush(author);
        } catch (Exception e) {
            throw new CreateDataFailException("Something went wrong during createAuthor");
        }
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO) throws UpdateDataFailException {
        try {
            Author author = authorMapper.toEntity(authorDTO);
            /*author.setBookList(bookRepository.findAllById(authorDTO.getBookList()));
            for (Book i : author.getBookList()) {
                for (Genre j : i.getGenreList()) {
                    author.getGenreList().add(j);
                }
            }*/
            authorRepository.saveAndFlush(author);
        } catch (Exception e){
            throw new UpdateDataFailException("Something went wrong with updateAuthor");
        }
        return authorDTO;
    }

    @Override
    public void deleteAuthor(Long id) throws DeleteDataFailException {
        Author author = authorRepository.findById(id).orElse(null);
        if(author == null){
            throw new DataNotFoundException("There is no author by this id");
        }
        bookRepository.deleteById(id);
    }



   @Override
   public List<Author> findByGenreList(List<String> genreList) {
        return authorRepository.findByGenreList(genreList);
    }
}
