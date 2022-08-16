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
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> list = userService.getUsers();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId){
        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping(value = "/user/create")
    public ResponseEntity createNewUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/user/update")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/user/delete/{userId}")
    public ResponseEntity deleteUserById(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
