package ru.otus.java.advanced.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import ru.otus.java.advanced.entity.User;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {

}
