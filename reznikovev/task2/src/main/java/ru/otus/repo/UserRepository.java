package ru.otus.repo;

import org.springframework.data.repository.CrudRepository;
import ru.otus.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}