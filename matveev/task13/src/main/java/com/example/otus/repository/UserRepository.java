package com.example.otus.repository;

import com.example.otus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("UserRepository")
public interface UserRepository extends JpaRepository<User,Long> {

}
