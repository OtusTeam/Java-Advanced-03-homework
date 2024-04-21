package task1.menu;

import lombok.Setter;
import task1.cache.SoftRefCache;
import task1.cache.WeakRefCache;
import task1.fileUtils.FileService;
import java.util.Scanner;

public class Emulator {

    private FileService fileService;

    @Setter
    private boolean menuState = true;

    private void choseCacheType() {
        System.out.println("Select type of cache: 1- softRef, 2 - weakRef");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextInt()) {
            case 1 -> {
                fileService = new FileService(new SoftRefCache());
            }
            case 2 -> {
                fileService = new FileService(new WeakRefCache());
            }
        }
    }

    public void dialog() {
        choseCacheType();
        while (menuState) {
            System.out.println("""
                    =====================
                    Choose action:
                        1) Set directory path
                        2) Get directory path
                        3) Get file
                        4) Load file
                        5) Get cached files
                        6) Clean cache
                        7) Quit
                    =====================
                    """
            );
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.println("Set path: ");
                    scanner = new Scanner(System.in);
                    fileService.setFileDir(scanner.nextLine());
                }
                case 2 -> System.out.println(fileService.getFileDir());

                case 3 -> {
                    System.out.println("Write file name:");
                    scanner = new Scanner(System.in);
                    fileService.get(scanner.nextLine());
                }
                case 4 -> {
                    System.out.println("Write file name:");
                    scanner = new Scanner(System.in);
                    fileService.put(scanner.nextLine());
                }
                case 5 -> fileService.getCachedFiles();

                case 6 -> fileService.removeWeakLinksAndClearCache();

                case 7 -> setMenuState(false);

                default -> System.out.println("Wrong action");

            }
        }
    }
}
