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

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> list;
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()){
            throw new DataNotFoundException("THERE ARE NO USERS");
        }
        list = userMapper.toUserDTOList(userList);
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
        User user = userMapper.toEntity(userDTO);
        userRepository.saveAndFlush(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        if(userDTO.getId() == null){
            throw new UpdateDataFailException("YOU NEED TE SPECIFY USER ID");
        }

        User found = userRepository.findById(userDTO.getId()).orElse(null);
        if(found == null){
            throw new UpdateDataFailException("THERE IS NO USER WITH THIS ID");
        }
        User user = userMapper.toEntity(userDTO);
        userRepository.saveAndFlush(user);
        return userDTO;
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            throw new DataNotFoundException("There is no user WITH this id");
        }
        userRepository.deleteById(id);
    }
}
