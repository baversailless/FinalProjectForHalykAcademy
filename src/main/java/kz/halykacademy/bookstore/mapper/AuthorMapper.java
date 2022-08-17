package kz.halykacademy.bookstore.mapper;
import kz.halykacademy.bookstore.dto.AuthorDTO;
import kz.halykacademy.bookstore.entity.Author;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Genre;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMapper {

    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private BookMapper bookMapper;

    @Autowired
    public AuthorMapper(BookRepository bookRepository,
                        GenreRepository genreRepository,
                        BookMapper bookMapper){
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.bookMapper = bookMapper;
    }

        public AuthorDTO toDTO(Author author){
        AuthorDTO dto = new AuthorDTO();
        for (Book i : author.getBookList()) {
            for (Genre j : i.getGenreList()) {
                author.getGenreList().add(j);
            }
        }
        dto.setId(author.getId());
        dto.setDateOfBirth(author.getDateOfBirth());
        dto.setFirstName(author.getFirstName());
        dto.setLastName(author.getLastName());
        dto.setPatronymicName(author.getPatronymicName());
        dto.setBookNames(author.getBookList().stream().map(Book::getTitle).collect(Collectors.toList()));
        dto.setGenreNames(author.getGenreList().stream().map(Genre::getName).collect(Collectors.toSet()));
        dto.setBookList(author.getBookList().stream().map(Book::getId).collect(Collectors.toList()));
        dto.setGenreList(author.getGenreList().stream().map(Genre::getId).collect(Collectors.toSet()));
        return dto;
    }

    public Author toEntity(AuthorDTO authorDTO){
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setDateOfBirth(authorDTO.getDateOfBirth());
        author.setFirstName(authorDTO.getFirstName());
        author.setLastName(authorDTO.getLastName());
        author.setPatronymicName(authorDTO.getPatronymicName());
        author.setBookList(bookRepository.findAllById(authorDTO.getBookList()));
        author.setGenreList(genreRepository.findAllById(authorDTO.getGenreList()));
        return author;
    }

    public List<AuthorDTO> toAuthorDTOList(List<Author> authorList) {
        List<AuthorDTO> dtoList = authorList.stream().map(author -> toDTO(author)).collect(Collectors.toList());
        return dtoList;
    }

}
