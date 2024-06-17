package ru.nicshal.advanced.outofmemory.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.nicshal.advanced.outofmemory.data.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}