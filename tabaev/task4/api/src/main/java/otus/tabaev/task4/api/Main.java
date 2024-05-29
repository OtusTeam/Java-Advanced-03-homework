package otus.tabaev.task4.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args) {

        Monitor monitor = Monitor.getInstance();

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> monitor.updateQuotes("GAZP"), 1, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(() -> monitor.updateQuotes("SBER"), 1, 1, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> monitor.printQuotesInfo("GAZP"), 1, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(() -> monitor.printQuotesInfo("SBER"), 1, 1, TimeUnit.SECONDS);

        scheduler.schedule(() -> {
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);

    }

}
