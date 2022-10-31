package com.example.springboot3.util;

import com.example.springboot3.entity.Role;
import com.example.springboot3.entity.User;
import com.example.springboot3.service.RoleServiceImpl;
import com.example.springboot3.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {
    private final UserServiceImpl userServiceImpl;
    private final RoleServiceImpl roleServiceImpl;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public DBInit(UserServiceImpl userServiceImpl, RoleServiceImpl roleServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostConstruct
    private void postConstruct() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        roleServiceImpl.addRole(role1);
        roleServiceImpl.addRole(role2);
        Set<Role> roles_admin = new HashSet<>();
        roles_admin.add(roleServiceImpl.getRoleByName("ROLE_ADMIN"));
        User admin = new User("admin", "admin", "admin@admin.ru", "1234", roles_admin);
        userServiceImpl.addUser(admin);
        Set<Role> roles_user = new HashSet<>();
        roles_user.add(roleServiceImpl.getRoleByName("ROLE_USER"));
        User user = new User("user", "user",
                "user@user.ru", "1234",  roles_user);
        userServiceImpl.addUser(user);
    }
}
