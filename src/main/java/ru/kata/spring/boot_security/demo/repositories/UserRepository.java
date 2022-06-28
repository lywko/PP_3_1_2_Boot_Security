
package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User getById(Long id);

    User getUserByName(String username);
}
