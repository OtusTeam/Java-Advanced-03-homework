package com.ozn.reactor.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("customer")
public class Customer {

    @Id
    private long id;

    @Column("login")
    private String login;

    @Column("password")
    private String password;

    @Column("email")
    private String email;

}
