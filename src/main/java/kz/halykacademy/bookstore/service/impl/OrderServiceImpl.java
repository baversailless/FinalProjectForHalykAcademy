package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.OrderCreateDTO;
import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.mapper.OrderMapper;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderMapper orderMapper,
                            BookRepository bookRepository,
                            UserRepository userRepository){
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<OrderDTO> getOrderList() {
        List<OrderDTO> list;
        try {
            List<Order> orderList = orderRepository.findAll();
            list = orderMapper.toOrderDTOList(orderList);
        } catch (Exception e){
            throw new DataNotFoundException("Orders not found");
        }
        return list;

    }

    @Override
    public OrderDTO getOrderById(Long id) {
        OrderDTO dto;
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new DataNotFoundException("There is no order by this id");
        } else {
            dto = orderMapper.toDTO(order);
        }
        return dto;
    }

   /* @Override
    public void createOrder(OrderDTO orderDTO) throws CreateDataFailException {
        try {
            Order order = orderMapper.toEntity(orderDTO);
            orderRepository.saveAndFlush(order);
        } catch (Exception e) {
            throw new CreateDataFailException("Something went wrong during createOrder");
        }
    }*/

    public void createOrder(OrderCreateDTO orderDTO) {
        List<Book> books = bookRepository.findAllById(orderDTO.getOrderedBooksIds());
        User user = userRepository.findById(orderDTO.getUserId()).orElse(null);
        Order order = orderDTO.convertOrderCreateDtoToEntity(books, user);
        orderRepository.save(order);

    }


  /*  @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) throws UpdateDataFailException {
        try {
            Order order = orderMapper.toEntity(orderDTO);
            order.setCreationDate(LocalDateTime.now());
            order.getOrderStatus()
            orderRepository.saveAndFlush(order);
        } catch (Exception e) {
            throw new UpdateDataFailException("Something went wrong with updateOrder");
        }
        return orderDTO;
    }*/

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) throws UpdateDataFailException {
        try {
            Order order = new Order();
            order.setId(order.getId());
            order.setBookList(bookRepository.findAllById(orderDTO.getBookIds()));
            order.setOrderStatus(orderDTO.getOrderStatus());
            Optional<User> user = userRepository.findById(orderDTO.getId());
            if (user.isPresent()) {
                order.setUser(user.get());
            }
            orderRepository.saveAndFlush(order);
            return orderDTO;

        } catch (Exception e) {
            throw new UpdateDataFailException("Something went wrong with updateOrder");
        }
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if(order == null){
            throw new DataNotFoundException("There is no order by this id");
        }
        orderRepository.deleteById(id);
    }

}
