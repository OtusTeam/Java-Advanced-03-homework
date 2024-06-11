package otus.homework.core;

import otus.homework.provider.DataProvider;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class DateStore {
    private final List<String> storage = new ArrayList<>();

    public ZonedDateTime save(String zoneId) {
        ZonedDateTime currentDate = ZonedDateTime.now(ZoneId.of(DataProvider.defaultUtcProvider()));
        try {
            currentDate = ZonedDateTime.now(ZoneId.of(zoneId));
        } catch (Exception e) {
            System.out.println("Can't read zoneId, using default:" + DataProvider.defaultUtcProvider());
        }
        storage.add(zoneId + ": " + currentDate);
        System.out.printf("Saved data with id: %s, currentDate: %s %n", zoneId, currentDate);
        return currentDate;
    }

    public List<String> getAllData() {
        return storage;
    }
}
