package kz.halykacademy.bookstore.service;

import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrderList();
    OrderDTO getOrderById(Long id);
    void createOrder(OrderDTO orderDTO) throws CreateDataFailException;
    OrderDTO updateOrder(OrderDTO orderDTO) throws UpdateDataFailException;
    void deleteOrder(Long id);
}
