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

@Controller
@RequestMapping(value = "/")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin/users")
    public String showAll(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getUserByName(name));
        return "all-user";
    }

    @GetMapping(value = "user")
    public String showUserById(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userService.getUserByName(name));
        return "userPage";
    }

    @GetMapping(value = "/default")
    public String redirectToUserID() {
        return "redirect:/user";
    }


    @GetMapping(value = "/login")
    public String loginPage() {
        return "loginPage";
    }
}
