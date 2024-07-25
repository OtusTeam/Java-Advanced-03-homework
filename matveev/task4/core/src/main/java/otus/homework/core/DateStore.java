package otus.homework.core;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DateStore {
    private final Map<String, ZonedDateTime> storage = new HashMap<>();

    public void save(String zoneId, ZonedDateTime zonedDateTime) {
        storage.put(zoneId, zonedDateTime);
        System.out.printf("Saved data with zoneId: %s, currentDate: %s %n", zoneId, zonedDateTime);
    }

    public List<String> getStorage() {
        return storage.keySet().stream()
                .map(key -> String.format("ZoneId: %s, Date: %s", key, storage.get(key)))
                .collect(Collectors.toList());
    }
}
