package com.ksu.visualvm.repository;

import com.ksu.visualvm.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select c from Customer c where c.login=:login and c.password = :password")
    Customer findOne(@Param("login") String login, @Param("password") String password);

    @Query("select c from Customer c where c.login=:login")
    Customer find(@Param("login") String login);
}