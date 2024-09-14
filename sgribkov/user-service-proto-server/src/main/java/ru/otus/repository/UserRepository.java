package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
