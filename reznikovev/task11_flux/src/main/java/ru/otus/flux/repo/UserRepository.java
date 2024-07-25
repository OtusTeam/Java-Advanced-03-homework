package ru.otus.flux.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.flux.model.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}