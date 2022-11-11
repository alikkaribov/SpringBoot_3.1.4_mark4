package com.example.springboot3.service;

import com.example.springboot3.dao.RoleDao;
import com.example.springboot3.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public void RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void checkBoxRole(String[] checkBoxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : checkBoxRoles) {
            roleSet.add(getRoleByName(role));
        }
    }

    @Transactional
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Transactional
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Transactional
    public void removeRoleById(long id) {
        roleDao.removeRoleById(id);
    }

    @Transactional
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Transactional
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
