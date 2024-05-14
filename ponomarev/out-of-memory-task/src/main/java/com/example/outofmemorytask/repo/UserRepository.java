package com.example.outofmemorytask.repo;

import com.example.outofmemorytask.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Anton Ponomarev on 13.05.2024
 * @project Java-Advanced-homework
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUserNameAndUserData(String userName, String userData);

    UserEntity findByUserName(String userName);
}
