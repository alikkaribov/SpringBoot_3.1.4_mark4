package com.example.springboot3.util;

import com.example.springboot3.entity.Role;
import com.example.springboot3.entity.User;
import com.example.springboot3.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Component
public class DBInit {
    @PersistenceContext
    private EntityManager entityManager;
    private final RoleService roleService;
    final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public DBInit(RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostConstruct
    private void postConstruct() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.addRole(adminRole);
        roleService.addRole(userRole);
        Set<Role> roles_admin = new HashSet<>();
        roles_admin.add(roleService.getRoleByName("ROLE_ADMIN"));
        User admin = new User("admin", "admin", "admin@admin.ru", "1234", roles_admin);
        entityManager.persist(admin);
        Set<Role> roles_user = new HashSet<>();
        roles_user.add(roleService.getRoleByName("ROLE_USER"));
        User user = new User("user", "user",
                "user@user.ru", "1234",  roles_user);
        entityManager.persist(user);
    }
}
