package ru.otus.core.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.core.model.UserEntity;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
