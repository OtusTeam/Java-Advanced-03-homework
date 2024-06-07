package ru.skilanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.skilanov.domain.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
}
