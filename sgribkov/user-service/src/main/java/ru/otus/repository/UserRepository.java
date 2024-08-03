package ru.otus.repository;

import org.springframework.stereotype.Repository;
import ru.otus.model.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

@Repository
public interface UserRepository extends R2dbcRepository<User, String> { }
