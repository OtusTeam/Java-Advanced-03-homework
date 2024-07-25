package com.homework.otus.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;


@Component
public class BackgroundExceptionsProcessor {

    @PostConstruct
    public void init() {
        new Thread(() -> {
            try {
                while (true) {
                    System.out.println("Exception Throw run");
                    Thread.sleep(5000);
                    throw new NoSuchElementException();
                }
            } catch (Exception e) {
                System.out.println("Ops! Exception.");
                init();
            }
        }).start();
    }


}