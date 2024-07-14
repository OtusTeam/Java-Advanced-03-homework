package com.homework.otus.repository;

import com.homework.otus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("UserRepository")
public interface UserRepository extends JpaRepository<User,Long> {

}
