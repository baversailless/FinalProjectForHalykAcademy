package kz.halykacademy.bookstore.mapper;
import kz.halykacademy.bookstore.dto.PublisherDTO;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Publisher;
import kz.halykacademy.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class PublisherMapper {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public PublisherMapper(BookRepository bookRepository,
                           BookMapper bookMapper){
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;

    }

    public PublisherDTO toDTO(Publisher publisher) {
        PublisherDTO dto = new PublisherDTO();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        dto.setBookIds(publisher.getBookList().stream().map(Book::getId).collect(Collectors.toList()));
        dto.setBookNames(publisher.getBookList().stream().map(Book::getTitle).collect(Collectors.toList()));
        return dto;
    }

    public Publisher toEntity(PublisherDTO publisherDTO){
        Publisher publisher = new Publisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        publisher.setBookList(bookRepository.findAllById(publisherDTO.getBookIds()));
        return publisher;
    }

    public List<PublisherDTO> toPublisherDTOList(List<Publisher> publishers){
        List<PublisherDTO> dtoList = publishers.stream().map(publisher -> toDTO(publisher)).collect(Collectors.toList());
        return dtoList;
    }
}
