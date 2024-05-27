package ru.otus.service;

import java.io.*;
import java.util.logging.Logger;


public class FileSystemCache extends AbstractCache<String, String> {

    private final Logger log = Logger.getLogger(FileSystemCache.class.getName());

    private File directory;

    public FileSystemCache(File directory) {
        this.directory = directory;
    }

    public void setDirectory(File newDirectory) {
        if (newDirectory.isDirectory())
            this.directory = newDirectory;

        throw new IllegalArgumentException(newDirectory + " - should be a directory");
    }

    @Override
    public void loadData(String key) {
        try {

            File file = new File(directory, key);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + file.getName());
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            this.put(key, content.toString());
        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public String getData(String key) {
        if (this.get(key) != null)
            return this.get(key);

        return null;
    }
}
