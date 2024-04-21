package ru.skilanov.service;

import org.springframework.stereotype.Service;

@Service
public class IOServiceImpl implements IOService {

    @Override
    public void printMessage(String systemMessage) {
        System.out.print(systemMessage);
    }
}
