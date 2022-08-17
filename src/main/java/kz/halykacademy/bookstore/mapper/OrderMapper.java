package kz.halykacademy.bookstore.mapper;
import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    private UserRepository userRepository;
    private BookRepository bookRepository;


    @Autowired
    public OrderMapper(UserRepository userRepository,
                       BookRepository bookRepository){
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public OrderDTO toDTO(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setBookIds(order.getBookList().stream().map(Book::getId).collect(Collectors.toList()));
        dto.setCreationDate(order.getCreationDate());
        return dto;
    }


    public List<OrderDTO> toOrderDTOList(List<Order> orders){
        List<OrderDTO> dtoList = orders.stream().map(order -> toDTO(order)).collect(Collectors.toList());
        return dtoList;
    }
}
