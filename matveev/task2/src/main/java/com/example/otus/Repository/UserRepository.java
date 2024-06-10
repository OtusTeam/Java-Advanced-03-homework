package com.example.otus.Repository;

import com.example.otus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("UserRepository")
public interface UserRepository extends JpaRepository<User,Long> {

}
