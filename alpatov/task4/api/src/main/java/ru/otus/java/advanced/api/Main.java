package ru.otus.java.advanced.api;

import ru.otus.java.advanced.provider.Provider;
import ru.otus.java.advanced.service.RegistrationService;

public class Main {
    public static void main(String[] args) {
        Provider provider = new Provider();
        RegistrationService registrationService = new RegistrationService();
        System.out.println(registrationService.registerUser(provider.generate()));
    }
}