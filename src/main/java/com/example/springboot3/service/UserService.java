package com.example.springboot3.service;


import com.example.springboot3.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.springboot3.entity.User;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(User user) {
        if (user.getPassword().equals(getUserById(user.getId()).getPassword())) {
            user.setPassword(getUserById(user.getId()).getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public void removeUserById(long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(long id) { return userRepository.findById(id).get(); }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUserByName(String username) {
        return userRepository.findByusername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = getUserByName(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found " + s);
        }
        return user;
    }
}
