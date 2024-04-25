package task1.fileUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomFileReader {

    public String readFile(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            return new String(bytes, Charset.defaultCharset());
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't read file");
        }
    }
}
