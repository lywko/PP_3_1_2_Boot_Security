package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
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

    public User findUserById(Long id) {
        return userRepository.getById(id);

    }

    @Transactional
    @Override
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.saveAll(Set.of(adminRole, userRole));
        save(new User("Ivan", "Ivanov", "$2a$12$LMOOIaBmkO1sdZaSKi41UOITeouymo6e.AiGIU28Al7r1bTv07NZK", "ivan@mail.ru", 30, Set.of(adminRole)));
        save(new User("Petr", "Petrov", "$2a$12$VtcyCvBgkrxqxCMpQY.O/eeoJoX9O3nPo1VFo50geQk5zUISXycFe", "petr@mail.ru", 24, Set.of(userRole)));
        save(new User("Alexander", "Alexandrov", "$2a$12$G2bAwW0TX/Yh2Fm6deLZkO/HLPGBrl1fcIX6Q6jtfFPyETu.ai7UC","alex@mail.ru" , 41, Set.of(userRole)));
    }
}
