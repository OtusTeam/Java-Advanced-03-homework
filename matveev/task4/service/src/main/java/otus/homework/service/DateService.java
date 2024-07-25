package otus.homework.service;

import otus.homework.core.DateStore;
import otus.homework.provider.DataProvider;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class DateService {
    private final DateStore dateStore = new DateStore();

    public ZonedDateTime saveData(String zoneId) {
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of(DataProvider.defaultUtcProvider()));
        try {
            currentDate = ZonedDateTime.now(ZoneId.of(zoneId));
        } catch (Exception e) {
            System.out.println("Can't read zoneId, using default:" + DataProvider.defaultUtcProvider());
        }
        dateStore.save(zoneId, currentDate);
        return currentDate;
    }

    public List<String> getAllData() {
        return dateStore.getStorage();
    }}
