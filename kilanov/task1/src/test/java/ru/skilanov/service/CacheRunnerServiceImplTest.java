package ru.skilanov.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import ru.skilanov.exception.DirectoryDoesNotSpecifiedException;
import ru.skilanov.repository.FileRepository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {CacheRunnerServiceImpl.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CacheRunnerServiceImplTest {

    public static final String PATH = "c:/test";
    public static final String FILE_NAME = "file.txt";
    public static final String RESULT = "result string";
    @Autowired
    private CacheRunnerServiceImpl cacheRunnerService;

    @MockBean
    private CacheService<String, String> cacheService;

    @MockBean
    private FileRepository<File, String> fileRepository;

    @Test
    public void whenGetAllFilesThenTheyReturn() {
        var file = new File("file");
        var file2 = new File("file2");
        var file3 = new File("file3");
        List<File> files = new ArrayList<>();
        files.add(file);
        files.add(file2);
        files.add(file3);
        cacheRunnerService.setDirectory(PATH);
        when(fileRepository.getAllFiles(new File(PATH))).thenReturn(files);
        var result = cacheRunnerService.getAllFiles();

        Assertions.assertThat(result)
                .containsExactlyInAnyOrder(file.getName(), file2.getName(), file3.getName());
    }

    @Test
    public void whenGetAllFilesWithoutSetDirThenThrowsException() {
        Throwable throwable = assertThrows(Throwable.class, () -> cacheRunnerService.getAllFiles());
        assertEquals(DirectoryDoesNotSpecifiedException.class, throwable.getClass());
    }

    @Test
    public void whenCacheFileThenItCached() {
        cacheRunnerService.setDirectory(PATH);
        doNothing().when(cacheService).load(PATH, FILE_NAME);
        cacheRunnerService.cacheFile(FILE_NAME);
        verify(cacheService, times(1)).load(PATH, FILE_NAME);
    }

    @Test
    public void whenCacheFileWithoutDirThenThrowsException() {
        Throwable throwable = assertThrows(Throwable.class, () -> cacheRunnerService.cacheFile(FILE_NAME));
        assertEquals(DirectoryDoesNotSpecifiedException.class, throwable.getClass());
    }

    @Test
    public void whenGetFileByNameThenItReturns() {
        cacheRunnerService.setDirectory(PATH);
        when(cacheService.get(FILE_NAME)).thenReturn(RESULT);
        var fileContent = cacheRunnerService.getFileByName(FILE_NAME);

        assertEquals(RESULT, fileContent);
    }

    @Test
    public void whenGetFileByNameWithoutDirThenThrowsException() {
        Throwable throwable = assertThrows(Throwable.class, () -> cacheRunnerService.getFileByName(FILE_NAME));
        assertEquals(DirectoryDoesNotSpecifiedException.class, throwable.getClass());
    }
}
