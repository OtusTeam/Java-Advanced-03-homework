package ru.skilanov.emulator;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.skilanov.service.CacheRunnerService;
import ru.skilanov.service.IOService;

import java.util.List;

@ShellComponent
public class ApplicationRunner {
    private final IOService ioService;
    private final CacheRunnerService cacheRunnerService;

    public ApplicationRunner(IOService ioService, CacheRunnerService cacheRunnerService) {
        this.ioService = ioService;
        this.cacheRunnerService = cacheRunnerService;
    }

    @ShellMethod(value = "Show menu", key = {"show_menu", "sm"})
    public void showMenu() {
        ioService.printMessage("You can set directory using command: sd <directory>\n");
        ioService.printMessage("You can get all files for caching using command: gfl\n");
        ioService.printMessage("You can cache file using command: cf <file name>\n");
        ioService.printMessage("You can get cached file using command: gf <file name>\n");
        ioService.printMessage("You can exit using command: exit\n");
    }

    @ShellMethod(value = "Specify directory", key = "sd")
    public void specifyDirectory(@ShellOption(value = "path") String path) {
        cacheRunnerService.setDirectory(path);
    }

    @ShellMethod(value = "Get files list", key = "gfl")
    public List<String> setDirAndGetAllFiles() {
        return cacheRunnerService.getAllFiles();
    }

    @ShellMethod(value = "Cache file", key = "cf")
    public void cacheFile(@ShellOption(value = "fileName") String fileName) {
        cacheRunnerService.cacheFile(fileName);
    }

    @ShellMethod(value = "Read cached file", key = "gf")
    public String readCachedFile(@ShellOption(value = "fileName") String fileName) {
        return cacheRunnerService.getFileByName(fileName);
    }
}
