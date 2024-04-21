package ru.skilanov.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {FileRepositoryImpl.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FileRepositoryImplTest {
    @Autowired
    private FileRepository<File, String> fileRepository;

    @Mock
    private BufferedReader bufferedReader;

    @Test
    public void whenReadFileThenItReturns() throws IOException {
        String expectedContent = "test test\n";
        when(bufferedReader.readLine()).thenReturn(expectedContent, null);

        var result = fileRepository.readFile(new File("test.txt"), "src/main/resources/files");
        assertEquals(expectedContent, result);
    }

    @Test
    public void whenGetAllFilesThenTheyReturn() {
        File file1 = new File("test.txt");
        File file2 = new File("test2.txt");

        File mockDirectory = mock(File.class);
        when(mockDirectory.listFiles()).thenReturn(new File[]{file1, file2});

        List<String> result = fileRepository.getAllFiles(new File("src/main/resources/files")).stream()
                .map(File::getName)
                .collect(Collectors.toList());

        List<String> expectedFiles = Arrays.asList("test.txt", "test2.txt");
        assertEquals(expectedFiles, result);
    }
}
