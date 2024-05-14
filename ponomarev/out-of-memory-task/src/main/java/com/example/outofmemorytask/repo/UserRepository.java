package com.example.outofmemorytask.repo;

import com.example.outofmemorytask.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
