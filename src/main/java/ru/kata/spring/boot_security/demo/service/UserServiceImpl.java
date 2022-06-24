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

    @Transactional
    @Override
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        roleService.saveAll(Set.of(adminRole, userRole));
        save(new User("Ivan", "Ivanov", "100", Set.of(adminRole)));
        save(new User("Petr", "Petrov", "200", Set.of(userRole)));
        save(new User("Alexander", "Alexandrov", "300", Set.of(userRole)));
    }
}
