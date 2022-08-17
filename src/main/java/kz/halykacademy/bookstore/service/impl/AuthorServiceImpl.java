package kz.halykacademy.bookstore.service.impl;
import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
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
        List<Author> authorList = authorRepository.findByFIO(name.toLowerCase());
        if(authorList.isEmpty()){
            throw new DataNotFoundException("NOT FOUND");
        }
        listDTO = authorMapper.toAuthorDTOList(authorList);
        return listDTO;
    }

    @Override
    public List<AuthorDTO> getAuthors() {
        List<AuthorDTO> listDTO;
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()){
            throw new DataNotFoundException("THERE ARE NO AUTHORS");
        }
        listDTO = authorMapper.toAuthorDTOList(authors);
        return listDTO;
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        AuthorDTO dto;
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null){
            throw new DataNotFoundException("THERE IS NO AUTHOR BY THIS ID");
        }
        dto = authorMapper.toDTO(author);
        return dto;
    }

    @Override
    public void createAuthor(AuthorDTO authorDTO) throws CreateDataFailException {
        Author author = authorMapper.toEntity(authorDTO);
        Author temp = authorRepository.checkExistedAuthor(author.getFirstName(), author.getLastName());
        if (temp != null){
            throw new CreateDataFailException("AUTHOR ALREADY EXISTS");
        }
        authorRepository.saveAndFlush(author);
    }

    @Override
    public AuthorDTO updateAuthor(AuthorDTO authorDTO) throws UpdateDataFailException {
        if(authorDTO.getId() == null){
            throw new UpdateDataFailException("YOU NEED TE SPECIFY AUTHOR ID");
        }
        Author found = authorRepository.findById(authorDTO.getId()).orElse(null);
        if (found == null){
            throw new UpdateDataFailException("THERE IS NO AUTHOR BY THIS ID");
        }
        Author author = authorMapper.toEntity(authorDTO);
        authorRepository.saveAndFlush(author);
        return authorDTO;
    }

    @Override
    public void deleteAuthor(Long id) throws DeleteDataFailException {
        Author author = authorRepository.findById(id).orElse(null);
        if(author == null){
            throw new DeleteDataFailException("THERE IS NO AUTHOR BY THIS ID");
        }
        authorRepository.deleteById(id);
    }



   @Override
   public List<Author> findByGenreList(List<String> genreList) {
        return authorRepository.findByGenreList(genreList);
    }
}
