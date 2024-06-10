package ru.skilanov.service;

import ru.skilanov.core.DataBase;

public class DataService {
    private final DataBase database;

    public DataService() {
        this.database = new DataBase();
    }

    public void saveData(String data) {
        database.save(data);
    }
}
