package ru.skilanov.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skilanov.exception.DirectoryDoesNotSpecifiedException;
import ru.skilanov.repository.FileRepository;

import java.io.File;
import java.util.List;

@Service
public class CacheRunnerServiceImpl implements CacheRunnerService {

    Logger logger = LoggerFactory.getLogger(CacheRunnerServiceImpl.class);

    private String path;
    private final CacheService<String, String> cacheService;
    private final FileRepository<File, String> fileRepository;

    public CacheRunnerServiceImpl(CacheService<String, String> cacheService, FileRepository<File, String> fileRepository) {
        this.cacheService = cacheService;
        this.fileRepository = fileRepository;
    }

    @Override
    public void setDirectory(String path) {
        this.path = path;
    }

    @Override
    public List<String> getAllFiles() {
        validatePath();
        return fileRepository.getAllFiles(new File(this.path)).stream()
                .map(File::getName)
                .toList();
    }

    @Override
    public void cacheFile(String fileName) {
        validatePath();
        cacheService.load(this.path, fileName);
    }

    @Override
    public String getFileByName(String fileName) {
        validatePath();
        var result = cacheService.get(this.path, fileName);
        if (result == null) {
            logger.atDebug().log("File doesn't exist");
            return String.format("%s %s", "File doesn't exist: ", fileName);
        }
        return result;
    }

    private void validatePath() {
        if (this.path == null) {
            throw new DirectoryDoesNotSpecifiedException("Directory wasn't specified");
        }
    }
}
