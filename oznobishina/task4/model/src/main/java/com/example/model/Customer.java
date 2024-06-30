package com.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private long id;
    private String name;
    private String email;
}
