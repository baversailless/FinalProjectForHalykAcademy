package kz.halykacademy.bookstore.dto;

import ch.qos.logback.core.status.Status;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.entity.OrderStatus;
import kz.halykacademy.bookstore.entity.User;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderCreateDTO {
    private Long id;
    private Long userId;
    private List<Long> orderedBooksIds;

    public Order convertOrderCreateDtoToEntity(List<Book> bbb, User uuu) {
        Order order = new Order();
        order.setId(this.getId());
        order.setCreationDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.CREATED);
        order.setUser(uuu);
        order.setBookList(bbb);
        return order;
    }
}
