package ru.otus.java.advanced.filereader;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractFileReader {

    protected String dir;

    public void setDir(String dir) {
        this.dir = dir;
    }

    protected Path getPath(String fileName) {
        return Paths.get(dir + "/" + fileName);
    }

    public abstract ByteBuffer readFile(String fileName);

}
