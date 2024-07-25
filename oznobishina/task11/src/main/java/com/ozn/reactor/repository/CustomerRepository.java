package com.ozn.reactor.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.ozn.reactor.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {

    @Query("select c from Customer c where c.login=:login and c.password=:password")
    Mono<Customer> findOne(@Param("login") String login, @Param("password") String password);

    @Query("select c.email from Customer c")
    Flux<String> findAllEmails();
}
