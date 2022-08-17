package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.OrderDTO;
import kz.halykacademy.bookstore.entity.*;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.mapper.OrderMapper;
import kz.halykacademy.bookstore.repository.BookRepository;
import kz.halykacademy.bookstore.repository.OrderRepository;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.security.UserDetailsImpl;
import kz.halykacademy.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public boolean checkPriceLimit(List<Book> books){
        int totalPrice = 0;
        for (Book i : books) {
            totalPrice = i.getPrice() + totalPrice;
        }
        return (totalPrice < 10000);

    }

    public void checkPresence(OrderDTO orderDTO){
        List<Long> bookIds = orderDTO.getBookIds();
        for(Long i:bookIds) {
            Book book = bookRepository.findById(i).orElse(null);
            if (book == null) {
                throw new CreateDataFailException("THERE IS NO BOOK BY THIS ID: " + i);
            }
        }
    }

    public void createOrder(OrderDTO orderDTO) throws CreateDataFailException {
        UserDetailsImpl userDetails= (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow();
        /*if (!username.equals(user.getUsername())) {
            throw new CreateDataFailException("YOU CAN CREATE ORDER ONLY FOR YOU");
        }*/
        if (!userDetails.isAccountNonLocked()) throw new CreateDataFailException("BLOCKED USER CANNOT CREATE AN ORDER");
        List<Book> books = bookRepository.findAllById(orderDTO.getBookIds());
        checkPresence(orderDTO);
        if (!checkPriceLimit(books)) throw new CreateDataFailException("PRICE CANNOT EXCEED 10000");
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUser(user);
        order.setBookList(books);
        order.setCreationDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.CREATED);
        orderRepository.save(order);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) throws UpdateDataFailException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow();

        if (orderDTO.getId() == null) {
            throw new UpdateDataFailException("YOU NEED THE SPECIFY ID OF YOUR ORDER");
        }

        Order order = orderRepository.findById(orderDTO.getId()).orElse(null);
        if (user.getRole().equals(Role.USER)){
            if (user != order.getUser()){
                throw new UpdateDataFailException("YOU CAN UPDATE ONLY YOUR ORDERS");
            }
        }

        //if (!userDetails.isAccountNonLocked()) throw new CreateDataFailException("BLOCKED USER CANNOT CREATE AN ORDER");

        List<Book> books = bookRepository.findAllById(orderDTO.getBookIds());
        checkPresence(orderDTO);
        if (!checkPriceLimit(books)) {
            throw new CreateDataFailException("PRICE CANNOT EXCEED 10000");
        }
        order.setUser(user);

        if (user.getRole().equals(Role.USER)){
            if (!order.getOrderStatus().equals(orderDTO.getOrderStatus())){
                throw new UpdateDataFailException("USERS CANNOT UPDATE ORDER STATUS");
            }
        }

        if (user.getRole().equals(Role.ADMIN)){
            if (!order.getBookList().equals(books)){
                throw new UpdateDataFailException("ADMIN CANNOT UPDATE USERS BOOK LIST");
            }
        }

        order.setBookList(books);
        order.setOrderStatus(orderDTO.getOrderStatus());
        orderRepository.save(order);
        return orderMapper.toDTO(order);

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
