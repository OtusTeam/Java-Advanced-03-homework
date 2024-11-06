package ru.otus.services.emulator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.otus.services.filecache.FileCache;
import ru.otus.services.filereader.FileReader;
import ru.otus.services.io.InputOutput;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@AllArgsConstructor
@Getter
@Setter
public class EmulatorImpl implements Emulator {
    private final InputOutput inputOutput;
    private final FileReader reader;
    private final FileCache cache;

    private final ExecutorService executor;

    public EmulatorImpl(InputOutput inputOutput,
                        FileReader reader,
                        FileCache cache) {
        this.inputOutput = inputOutput;
        this.reader = reader;
        this.cache = cache;
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void run() {
        executor.submit(() -> {
            do {
                String path = inputOutput.readLn("Enter files path");
                if (path.equalsIgnoreCase("exit")) {
                    stop();
                } else {
                    try {
                        reader.setPath(path);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
            } while (!reader.hasPath());

            do {
                String fileName = inputOutput.readLn("Enter file name");
                if (fileName.equalsIgnoreCase("exit")) {
                    stop();
                } else {
                    String content;
                    if (cache.hasContent(fileName)) {
                        content = cache.getContent(fileName);
                    } else {
                        ByteBuffer buffer = reader.read(fileName);
                        cache.loadContent(fileName, buffer);
                        content = cache.getContent(fileName);
                    }
                    inputOutput.out(content);
                }
            } while (!executor.isShutdown());
        });
    }

    private void stop() {
        inputOutput.close("Application will be stopped");
        executor.shutdown();
    }
}
