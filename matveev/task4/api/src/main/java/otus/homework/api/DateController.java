package otus.homework.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import otus.homework.core.DateStore;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/date")
public class DateController {
    DateStore dateStore = new DateStore();

    @PostMapping("")
    public ResponseEntity<ZonedDateTime> saveDate(@RequestBody String zoneId) {
        ZonedDateTime date = dateStore.save(zoneId);
        return ResponseEntity.ok(date);
    }

    @GetMapping("")
    public ResponseEntity<List<String>> getStore() {
        return ResponseEntity.ok(dateStore.getAllData());
    }

}
