package ru.otus.java.advanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.java.advanced.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    List<User> findAll();

}
