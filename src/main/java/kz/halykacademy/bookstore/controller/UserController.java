package kz.halykacademy.bookstore.controller;

import kz.halykacademy.bookstore.dto.UserDTO;
import kz.halykacademy.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public List<UserDTO>  getAllUsers(){
        return userService.getUsers();
    }

    @GetMapping(value = "/user/{userId}")
    public UserDTO getUserById(@PathVariable("userId") Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping(value = "/user/create")
    public void createNewUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
    }

    @PutMapping(value = "/user/update")
    public void updateUser(@RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
    }

    @DeleteMapping(value = "/user/delete/{userId}")
    public void deleteUserById(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }

}
