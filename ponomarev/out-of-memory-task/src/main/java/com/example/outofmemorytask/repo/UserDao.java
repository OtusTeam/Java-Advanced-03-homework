package com.example.outofmemorytask.repo;

import com.example.outofmemorytask.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Anton Ponomarev on 14.05.2024
 * @project Java-Advanced-homework
 */
@Repository
@RequiredArgsConstructor
public class UserDao {
  private final UserRepository userRepository;

  @Transactional
  public UserEntity save(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  @Transactional
  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }
}
