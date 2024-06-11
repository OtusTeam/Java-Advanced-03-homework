package otus.homework.service;

import otus.homework.core.DateStore;

public class DateService {
    private final DateStore dateStore = new DateStore();

    public void saveData(String data) {
        dateStore.save(data);
    }
}
