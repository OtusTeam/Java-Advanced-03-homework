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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {FileRepositoryImpl.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FileRepositoryImplTest {
    public static final String VALID_FILES_PATH = "src/test/resources/files";
    public static final String INVALID_FILES_PATH = "src/test/resources/fil";
    @Autowired
    private FileRepository<File, String> fileRepository;

    @Mock
    private BufferedReader bufferedReader;

    @Test
    public void whenReadFileThenItReturns() throws IOException {
        String expectedContent = "test test";
        when(bufferedReader.readLine()).thenReturn(expectedContent, null);

        var result = fileRepository.readFile(new File("test.txt"), VALID_FILES_PATH);
        assertEquals(expectedContent, result);
    }

    @Test
    public void whenReadFileThenItThrowsException() {
        Throwable throwable = assertThrows(
                Throwable.class, () -> fileRepository.readFile(new File("te.txt"), VALID_FILES_PATH)
        );
        assertEquals(RuntimeException.class, throwable.getClass());
    }

    @Test
    public void whenGetAllFilesThenTheyReturn() {
        File file1 = new File("test.txt");
        File file2 = new File("test2.txt");

        File mockDirectory = mock(File.class);
        when(mockDirectory.listFiles()).thenReturn(new File[]{file1, file2});

        List<String> result = fileRepository.getAllFiles(new File(VALID_FILES_PATH)).stream()
                .map(File::getName)
                .collect(Collectors.toList());

        List<String> expectedFiles = Arrays.asList("test.txt", "test2.txt");
        assertEquals(expectedFiles, result);
    }

    @Test
    public void whenGetAllFilesThenItReturnEmptyList() {
        File file1 = new File("test.txt");
        File file2 = new File("test2.txt");

        File mockDirectory = mock(File.class);
        when(mockDirectory.listFiles()).thenReturn(new File[]{file1, file2});

        List<String> result = fileRepository.getAllFiles(new File(INVALID_FILES_PATH)).stream()
                .map(File::getName)
                .toList();

        assertTrue(result.isEmpty());
    }
}
