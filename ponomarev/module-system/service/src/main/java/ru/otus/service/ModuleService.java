package ru.otus.service;

import ru.otus.core.ModuleCore;
import ru.otus.provider.ModuleData;

/**
 * @author Anton Ponomarev on 28.05.2024
 * @project Default (Template) Project
 */
public class ModuleService {
    private final ModuleCore moduleCore;

    public ModuleService() {
        this.moduleCore = new ModuleCore();
    }

    public void saveData(ModuleData data) {
        moduleCore.save(data);
        System.out.println("Data saved: " + data.toString());
    }
}