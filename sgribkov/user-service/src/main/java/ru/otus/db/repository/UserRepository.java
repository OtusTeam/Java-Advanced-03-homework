package ru.otus.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> { }
