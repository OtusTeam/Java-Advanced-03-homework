package com.ex.memorydump.repository;

import com.ex.memorydump.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select c from Customer c where c.login=:login and c.password = :password")
    Customer findOne(@Param("login") String login, @Param("password") String password);
}
