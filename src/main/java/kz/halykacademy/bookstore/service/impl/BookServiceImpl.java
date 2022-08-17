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
        List<Book> bookList = bookRepository.findByTitleLikeIgnoreCase(name.toLowerCase());
        if (bookList.isEmpty()){
            throw new DataNotFoundException("NOT FOUND");
        }
        listDTO = bookMapper.toBookDTOList(bookList);
        return listDTO;
    }

   @Override
    public List<BookDTO> getBookListToShow() {
       List<BookDTO> listDTO;
       List<Book> books = bookRepository.getAllBooks();
       if (books.isEmpty()){
           throw new DataNotFoundException("THERE ARE NO BOOKS");
       }
       listDTO = bookMapper.toBookDTOList(books);
       return listDTO;
    }

    @Override
    public BookDTO getBookById(Long id) {
        BookDTO dto;
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null){
            throw new DataNotFoundException("THERE IS NO BOOK BY THIS ID");
        }
        dto = bookMapper.toDTO(book);
        return dto;
    }

    @Override
    public void createBook(BookDTO bookDTO) throws CreateDataFailException {
        Book book = bookMapper.toEntity(bookDTO);
        Book temp = bookRepository.checkExistedBook(book.getTitle(), book.getPublisher().getId(), book.getNumberOfPages(), book.getReleaseYear());
        if (temp != null) {
            throw new CreateDataFailException("BOOK ALREADY EXISTS");
        }
        bookRepository.saveAndFlush(book);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO) throws UpdateDataFailException {
            if (bookDTO.getId() == null) {
                throw new UpdateDataFailException("YOU NEED TE SPECIFY BOOK ID");
            }
            Book found = bookRepository.findById(bookDTO.getId()).orElse(null);
            if (found == null){
                throw new UpdateDataFailException("THERE IS NO BOOK BY THIS ID");
            }
            Book book = bookMapper.toEntity(bookDTO);
            bookRepository.saveAndFlush(book);
        return bookDTO;
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new DataNotFoundException("THERE IS NO BOOK BY THIS ID");
        }
        bookRepository.deleteById(id);
    }


}
