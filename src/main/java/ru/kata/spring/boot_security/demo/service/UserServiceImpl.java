package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDao;
    private final RoleService roleService;

    public UserServiceImpl(UserDAO userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User getId(Long id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userDao.findAll();
    }

    public User findUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public User findUserById(Long id) {
        return userDao.getById(id);

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
