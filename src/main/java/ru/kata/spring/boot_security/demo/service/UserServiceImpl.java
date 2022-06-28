package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getId(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    public User findUserByName(String name) {
        return userRepository.getUserByName(name);
    }

    @Transactional
    @Override
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.saveAll(Set.of(adminRole, userRole));
        save(new User("Ivan", "Ivanov", "$2a$12$BlYrFcrklcJy7LD1VjfCfOfea97XuCrmWwFyniFPPauVz4JkxQEIO", Set.of(adminRole)));
        save(new User("Petr", "Petrov", "$2a$12$R88y1JUunUSDWnidGRFci.YB0zwi5NBKyJAHFeYKVFhbH1rnpfJJC", Set.of(userRole)));
        save(new User("Alexander", "Alexandrov", "$2a$12$2gqB6ncjWpgRmpzyA9FCruLurjiAMqEy3DVU/6AvUk61OJcLOGN6C", Set.of(userRole)));
    }
}
