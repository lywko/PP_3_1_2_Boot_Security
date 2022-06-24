package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDAO;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDao;

    public RoleServiceImpl(RoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    @Transactional
    public void saveAll(Collection<Role> roles) {
        roleDao.saveAll(roles);
    }
}
