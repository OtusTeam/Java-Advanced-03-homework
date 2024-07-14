package ru.otus;

import ru.otus.services.emulator.Emulator;
import ru.otus.services.emulator.EmulatorImpl;
import ru.otus.services.filecache.FileCache;
import ru.otus.services.filecache.SoftRefFileCache;
import ru.otus.services.filecache.WeakRefFileCache;
import ru.otus.services.filereader.FileReader;
import ru.otus.services.filereader.FileReaderBB;
import ru.otus.services.filereader.FileReaderMBB;
import ru.otus.services.io.InputOutput;
import ru.otus.services.io.InputOutputImpl;

public class EmulatorApp {
    public static void main(String[] args) {
        boolean isWeakRefCache;

        try {
            isWeakRefCache = Boolean.parseBoolean(args[0]);
        } catch (Exception e) {
            isWeakRefCache = false;
        }

        InputOutput inputOutput = new InputOutputImpl(System.out, System.in);
        FileReader reader = new FileReaderMBB();
        FileCache cache = createFileCache(isWeakRefCache);

        Emulator emulator = new EmulatorImpl(inputOutput, reader, cache);
        emulator.run();
    }

    private static FileCache createFileCache(boolean isWeak) {
        return isWeak ? new WeakRefFileCache() : new SoftRefFileCache();
    }
}
