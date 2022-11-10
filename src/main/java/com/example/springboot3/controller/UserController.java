package com.example.springboot3.controller;

import com.example.springboot3.service.RoleService;
import com.example.springboot3.service.RoleServiceImpl;
import com.example.springboot3.service.UserService;
import com.example.springboot3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public UserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String showAll(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getUserByName(name));
        return "all-user";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String showUserById(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.getUserByName(name));
        return "userPage";
    }
    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String redirectToUserID() {
        return "redirect:/user";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "loginPage";
    }
}
