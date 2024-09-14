package ru.otus.api;

import ru.otus.provider.ModuleProvider;
import ru.otus.service.ModuleService;

/**
 * @author Anton Ponomarev on 28.05.2024
 * @project Default (Template) Project
 */
public class ModuleApi {
    public static void main(String[] args) {

        ModuleProvider moduleProvider = new ModuleProvider();
        ModuleService moduleService = new ModuleService();

        var data = moduleProvider.createData();
        moduleService.saveData(data);
    }
}