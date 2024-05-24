package ru.skilanov.api;

import ru.skilanov.provider.DataProvider;
import ru.skilanov.service.DataService;

public class Main {
    public static void main(String[] args) {
        DataProvider provider = new DataProvider();
        DataService service = new DataService();

        String data = provider.provideData();
        service.saveData(data);

        System.out.println("Application was finished.");
    }
}