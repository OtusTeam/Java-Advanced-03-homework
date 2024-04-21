package otus.tabaev.task1.menu;

import otus.tabaev.task1.cache.Cache;
import otus.tabaev.task1.cache.exception.CacheLoadException;
import otus.tabaev.task1.cache.exception.DataSourceException;
import otus.tabaev.task1.cache.factory.CacheFactory;
import otus.tabaev.task1.util.PropertiesUtil;

import java.util.Scanner;

public class Emulator {

    private final Cache<String, String> referencesCache;

    public Emulator() {
        Long cleanUpInterval = Long.valueOf(PropertiesUtil.get("clean.up.interval"));
        Long refreshInterval = Long.valueOf(PropertiesUtil.get("refresh.interval"));
        String source = PropertiesUtil.get("directory.path");
        String cacheType = PropertiesUtil.get("cache.type");
        this.referencesCache = CacheFactory.createCache(source, cleanUpInterval, refreshInterval, cacheType);
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        changeDirectory(scanner);
                        break;
                    case "2":
                        loadDataToCache(scanner);
                        break;
                    case "3":
                        retrieveDataFromCache(scanner);
                        break;
                    case "4":
                        System.out.println("Выход из приложения.");
                        referencesCache.stop();
                        return;
                    default:
                        System.out.println("Неверный ввод. Попробуйте еще раз.");
                }
            } catch (DataSourceException | CacheLoadException e) {
                System.err.println(e.getMessage());
            }

        }


    }

    private void setSource(String source) throws DataSourceException {
        referencesCache.setDataSource(source);
    }

    private String getFromCache(String key) throws CacheLoadException {
        return referencesCache.get(key);
    }

    private String loadToCache(String key) throws CacheLoadException {
        return referencesCache.load(key);
    }

    private void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Изменить кэшируемую директорию");
        System.out.println("2. Загрузить данные в кэш");
        System.out.println("3. Получить данные из кэша");
        System.out.println("4. Выйти из приложения");
        System.out.print("Введите номер действия: ");
    }

    private void changeDirectory(Scanner scanner) throws DataSourceException {
        System.out.print("Введите новую кэшируемую директорию: ");
        String newDirectory = scanner.nextLine();
        setSource(newDirectory);
    }

    private void loadDataToCache(Scanner scanner) throws CacheLoadException {
        System.out.print("Введите ключ для загрузки данных в кэш: ");
        String keyToLoad = scanner.nextLine();
        String loadedData = loadToCache(keyToLoad);
        System.out.println("Данные успешно загружены в кэш: " + loadedData);
    }

    private void retrieveDataFromCache(Scanner scanner) throws CacheLoadException {
        System.out.print("Введите ключ для получения данных из кэша: ");
        String keyToRetrieve = scanner.nextLine();
        String cachedData = getFromCache(keyToRetrieve);
        System.out.println("Данные из кэша: " + cachedData);
    }

}
