package ru.otus.core;

import ru.otus.provider.ModuleData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton Ponomarev on 28.05.2024
 * @project Default (Template) Project
 */
public class ModuleCore {
    private final List<ModuleData> dataList;

    public ModuleCore() {
        this.dataList = new ArrayList<>();
    }


    public void save(ModuleData data) {
        dataList.add(data);
        System.out.println("Data added to data list: "+ data.toString());
    }
}