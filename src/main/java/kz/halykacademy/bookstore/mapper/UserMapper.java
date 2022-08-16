package kz.halykacademy.bookstore.mapper;

import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.Order;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private OrderRepository orderRepository;

    @Autowired
    public UserMapper(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setBlocked(user.isBlocked());
        dto.setOrdersId(user.getOrderList().stream().map(Order::getId).collect(Collectors.toList()));
        return dto;
    }

    public User toEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setOrderList(orderRepository.findAllById(userDTO.getOrdersId()));
        return user;
    }

    public List<UserDTO> toUserDTOList(List<User> userList){
        List<UserDTO> dtoList = userList.stream().map(user -> toDTO(user)).collect(Collectors.toList());
        return dtoList;
    }
}
