package ru.skilanov.core;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final List<String> dataCollection = new ArrayList<>();
    private int idCounter = 1;

    public void save(String data) {
        String idData = idCounter + ": " + data;
        dataCollection.add(idData);
        idCounter++;
        System.out.println("Data saved: " + idData);
    }
}