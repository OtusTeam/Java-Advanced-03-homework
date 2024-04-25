package cache.menu;

import cache.model.AbstractCacheData;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.ref.Reference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Anton on 20.04.2024
 * @project memory-management(Otus Java Developer. Advanced)
 */
public class Emulator {
    private String directory = "";
    private final AbstractCacheData<String, Reference> data;

    public static final String EXC_MESSAGE = "Произошла ошибка. Просим повторить попытку";
    public static final String DESC_MESSAGE = """
            Выберите один из следующих пунктов:
            1. Указать кэшируемую директорию
            2. Загрузить содержимое файла в кэш
            3. Получить содержимое файла из кэша
            4. Завершить приложение
            Введите требуемое вам действие:
            """;
    private final List<String> options = List.of("1", "2", "3", "4");

    public Emulator(AbstractCacheData data) {
        this.data = data;
    }

    public void runEmulator() {
        System.out.println(DESC_MESSAGE);
        try {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            String str = reader.readLine();
            if (!options.contains(str)) {
                System.out.println(EXC_MESSAGE);
                runEmulator();
            }
            switch (str) {
                case "1" -> cacheDirectory();
                case "2" -> uploadFile();
                case "3" -> getFile();
                case "4" -> reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cacheDirectory() {
        System.out.println("укажите директорию с файлами");

        try {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            String dir = reader.readLine();
            Path path = Paths.get(dir);
            if (path.toFile().exists() && path.toFile().isDirectory()) {
                if (!dir.endsWith("\\")) {
                    directory = dir + "\\";
                }
            } else if (path.toFile().exists() && path.toFile().isFile()) {
                directory = path.toFile().getParent() + "\\";
            } else {
                System.out.println(EXC_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        runEmulator();
    }

    private void uploadFile() {
        if (StringUtils.isEmpty(directory)) {
            cacheDirectory();
            return;
        } else {
            System.out.println("Введите название файла для кеширования: ");
            listFilesForFolder(Paths.get(directory).toFile());
        }

        try {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            String filename = reader.readLine();
            Path path = Paths.get(directory + filename);
            File file = path.toFile();
            if (file.exists()) {
                if (!data.containsFile(filename)) {
                    data.uploadFile(filename, file);
                }
            } else {
                System.out.println("Введенный файл не обнаружен");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        runEmulator();
    }

    private void getFile() {
        if (StringUtils.isEmpty(directory)) {
            cacheDirectory();
            return;
        } else {
            System.out.println("Введите название файла для отображения: ");
            listFilesForFolder(Paths.get(directory).toFile());
        }

        try {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            String filename = reader.readLine();
            Path path = Paths.get(directory + filename);
            File file = path.toFile();
            if (file.exists()) {
                if (!data.containsFile(filename)) {
                    System.out.println("В кеше отсутсвует файл, загружаем его из директории...");
                    var reference = data.uploadFile(filename, file);
                    if (reference == null) {
                        System.out.println(EXC_MESSAGE);
                    } else {
                        System.out.println(data.getDataFile(filename).get());
                    }
                } else {
                    System.out.println("Получаем файл из кеша...");
                    System.out.println(data.getDataFile(filename).get());
                }
            } else {
                System.out.println("Введенный файл не обнаружен");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        runEmulator();
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }
}
