package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserDAO userdao;

    public UserDetailService(UserDAO userdao) {
        this.userdao = userdao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userdao.getUserByName(username);
        return new User(user.getUsername(), user.getPassword(), user.getRoles());
    }
}
