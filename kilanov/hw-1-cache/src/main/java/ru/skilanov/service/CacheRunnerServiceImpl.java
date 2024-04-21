package ru.skilanov.service;

import org.springframework.stereotype.Service;
import ru.skilanov.exception.DirectoryDoesNotSpecifiedException;
import ru.skilanov.exception.FileDoesntExistException;
import ru.skilanov.repository.FileRepository;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CacheRunnerServiceImpl implements CacheRunnerService {
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
                .collect(Collectors.toList());
    }

    @Override
    public void cacheFile(String fileName) {
        validatePath();
        cacheService.load(this.path, fileName);
    }

    @Override
    public String getFileByName(String fileName) {
        validatePath();
        var result = cacheService.get(fileName);
        if (result == null) {
            throw new FileDoesntExistException("File doesnt exist");
        }
        return result;
    }

    private void validatePath() {
        if (this.path == null) {
            throw new DirectoryDoesNotSpecifiedException("Directory wasn't specified");
        }
    }
}
