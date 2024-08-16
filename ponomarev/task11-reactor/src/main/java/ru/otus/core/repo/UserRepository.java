package ru.otus.core.repo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.core.model.UserEntity;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
}
