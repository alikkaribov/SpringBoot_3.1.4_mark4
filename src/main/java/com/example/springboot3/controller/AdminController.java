package com.example.springboot3.controller;

import com.example.springboot3.entity.User;
import com.example.springboot3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/admin/users")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/admin/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/users")
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        userService.removeUserById(user.getId());
        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/autority")
    public ResponseEntity<User> getAutority() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
