package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.OrderCreateDTO;
import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrderList();
    OrderDTO getOrderById(Long id);
    void createOrder(OrderCreateDTO orderDTO) throws CreateDataFailException;
    OrderDTO updateOrder(OrderDTO orderDTO) throws UpdateDataFailException;
    void deleteOrder(Long id);
}
