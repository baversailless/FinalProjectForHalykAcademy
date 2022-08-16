package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.dto.BookDTO;
import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.entity.Book;
import kz.halykacademy.bookstore.entity.Role;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.exception.CreateDataFailException;
import kz.halykacademy.bookstore.exception.DataNotFoundException;
import kz.halykacademy.bookstore.exception.UpdateDataFailException;
import kz.halykacademy.bookstore.mapper.UserMapper;
import kz.halykacademy.bookstore.repository.UserRepository;
import kz.halykacademy.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> list;
        try {
            List<User> userList = userRepository.findAll();
            list = userMapper.toUserDTOList(userList);
        } catch (Exception e){
            throw new DataNotFoundException("Users not found");
        }
        return list;
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserDTO dto;
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new DataNotFoundException("There is no user by this id");
        } else {
            dto = userMapper.toDTO(user);
        }
        return dto;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        try {
            User user = userMapper.toEntity(userDTO);
            user.setPassword(encoder.encode(userDTO.getPassword()));
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            throw new CreateDataFailException("Something went wrong during createUser");
        }
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        try {
            User user = userMapper.toEntity(userDTO);
            userRepository.saveAndFlush(user);
        } catch (Exception e) {
            throw new UpdateDataFailException("Something went wrong with updateUser");
        }
        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new DataNotFoundException("There is no user by this id");
        }
        userRepository.deleteById(id);
    }
}
