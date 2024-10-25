package ru.otus.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.services.filereader.FileReader;
import ru.otus.services.filereader.FileReaderMBB;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;

public class FileReaderMBBTest {

    @DisplayName("Чтение содержимого файла")
    @Test
    void readFileTest() {
        //given
        String path = "src/test/resources";
        String file = "read_test.txt";
        FileReader reader = new FileReaderMBB(path);

        //when
        ByteBuffer buffer = reader.read(file);
        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
        String content = charBuffer.toString();

        //then
        assert content.equals("READING WAS SUCCESSFUL");
    }
}
