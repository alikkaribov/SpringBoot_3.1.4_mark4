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
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @RequestMapping(value = "/autority", method = RequestMethod.GET)
    public ResponseEntity<User> getAutority(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/admin/users", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@RequestBody User user){
        userService.removeUserById(user.getId());
        return new ResponseEntity<>(user,HttpStatus.NO_CONTENT);
    }
}
