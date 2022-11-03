package com.example.springboot3.controller;

import com.example.springboot3.service.RoleServiceImpl;
import com.example.springboot3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.springboot3.entity.Role;
import com.example.springboot3.entity.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;
    @Autowired
    public UserController(RoleServiceImpl roleServiceImpl, UserServiceImpl userServiceImpl) {
        this.roleServiceImpl = roleServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userInfo(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "userpage";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("allUsers", userServiceImpl.getAllUsers());
        return "all-user";
    }

    @RequestMapping(value = "/admin/new", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "info";
    }

    @RequestMapping(value = "/admin/add-user", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        roleServiceImpl.checkBoxRole(checkBoxRoles);
        user.setRoles(roleSet);
        userServiceImpl.addUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        model.addAttribute("roles", roleServiceImpl.getAllRoles());
        return "edit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public String editUser(@ModelAttribute User user, @RequestParam(value = "checkBoxRoles") String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        roleServiceImpl.checkBoxRole(checkBoxRoles);
        user.setRoles(roleSet);
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public String removeUser(@PathVariable("id") long id) {
        userServiceImpl.removeUserById(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
}
