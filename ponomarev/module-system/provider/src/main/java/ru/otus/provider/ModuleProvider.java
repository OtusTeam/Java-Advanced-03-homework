package ru.otus.provider;

import java.util.UUID;

/**
 * @author Anton Ponomarev on 28.05.2024
 * @project Default (Template) Project
 */
public class ModuleProvider {
    public ModuleData createData() {
        System.out.println("Creating data... ");
        return ModuleData.builder()
                .data("Some data - " + Math.random())
                .uuid(UUID.randomUUID())
                .build();
    }
}