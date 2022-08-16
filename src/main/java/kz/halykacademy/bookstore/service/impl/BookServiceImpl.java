package kz.halykacademy.bookstore.service.impl;
import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.mapper.BookMapper;
import kz.halykacademy.bookstore.repository.AuthorRepository;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.GenreRepository;
import kz.halykacademy.bookstore.repository.PublisherRepository;
import kz.halykacademy.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;
    private PublisherRepository publisherRepository;
    private BookMapper bookMapper;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           PublisherRepository publisherRepository,
                           BookMapper bookMapper){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.publisherRepository = publisherRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDTO> findByTitle(String name) {
        List<BookDTO> listDTO;
        try {
            List<Book> bookList = bookRepository.findByTitleLikeIgnoreCase(name.toLowerCase());
            listDTO = bookMapper.toBookDTOList(bookList);
        } catch (Exception e){
            throw new DataNotFoundException("Something went wrong with findByTitle");
        }
        return listDTO;
    }

   @Override
    public List<BookDTO> getBookListToShow() {
       List<BookDTO> listDTO;
        try {
            List<Book> book = bookRepository.getAllBooks();
            listDTO = bookMapper.toBookDTOList(book);
        } catch (Exception e) {
            throw new DataNotFoundException("Something went wrong with Book List");
        }
        return listDTO;

    }

    @Override
    public BookDTO getBookById(Long id) {
        BookDTO dto;
        try {
            Book book = bookRepository.findById(id).orElse(null);
            if (book == null) {
                throw new DataNotFoundException("There is no book by this id");
            } else {
                dto = bookMapper.toDTO(book);
            }
        } catch (Exception e){
            throw new DataNotFoundException("Something went wrong with Book");
        }
        return dto;
    }

    @Override
    public void createBook(BookDTO bookDTO) throws CreateDataFailException {
            try {
                Book book = bookMapper.toEntity(bookDTO);
                Book temp = bookRepository.checkExistedBook(book.getTitle(), book.getPublisher().getId(), book.getNumberOfPages(), book.getReleaseYear());
                if (temp != null){
                    throw new CreateDataFailException("BOOK ALREADY EXISTS");
                }
                bookRepository.saveAndFlush(book);
            } catch (Exception e) {
                throw new CreateDataFailException("Something went wrong during createBook");
            }
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) throws UpdateDataFailException {
        try {
            Book book = bookMapper.toEntity(bookDTO);
            bookRepository.saveAndFlush(book);
        } catch (Exception e) {
            throw new UpdateDataFailException("Something went wrong with updateBook");
        }
        return bookDTO;
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new DataNotFoundException("There is no book by this id");
        }
        bookRepository.deleteById(id);
    }


}
