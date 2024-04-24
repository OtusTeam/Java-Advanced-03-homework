package cache.menu;

import cache.model.impl.CacheDataSoftRefImpl;
import cache.model.impl.CacheDataWeakRefImpl;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Anton Ponomarev on 21.04.2024
 * @project memory-management(Otus Java Developer. Advanced)
 */
public class Main {
    public static void main(String[] args) {
        Emulator emulator;
        if (Arrays.asList(args).contains("weak")) {
            System.out.println("User configuration with WeakReference");
            var data = new CacheDataWeakRefImpl(new HashMap<>());
            emulator = new Emulator(data);
        } else if (Arrays.asList(args).contains("soft")) {
            System.out.println("User configuration with SoftReference");
            var data = new CacheDataSoftRefImpl(new HashMap<>());
            emulator = new Emulator(data);
        } else {
            System.out.println("Default configuration with SoftReference");
            var data = new CacheDataSoftRefImpl(new HashMap<>());
            emulator = new Emulator(data);
        }
        emulator.runEmulator();
    }
}