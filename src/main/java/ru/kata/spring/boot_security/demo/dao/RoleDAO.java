package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

@Repository
public interface RoleDAO extends CrudRepository<Role,Long> {
    Role getRoleByName(String name);
}
