package task10.menu;

import lombok.Setter;
import task10.cache.SoftRefCache;
import task10.cache.WeakRefCache;
import task10.fileUtils.ByteBufferFileService;
import task10.fileUtils.FileService;
import task10.fileUtils.MappedByteBufferFileService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Emulator {

    private FileService fileService;

    @Setter
    private boolean menuState = true;

    private void choseCacheType() {
        System.out.println("Select type of cache: 1- softRef, 2 - weakRef");
        Scanner scanner = new Scanner(System.in);
        int input = 0;
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input, try again");
            choseCacheType();
        }
        switch (input) {
            case 1 -> fileService = new ByteBufferFileService(new SoftRefCache());
            case 2 -> fileService = new MappedByteBufferFileService(new WeakRefCache());
            default -> {
                System.out.printf("Incorrect input: %s, default Weak cache was created \n", input);
                fileService = new ByteBufferFileService(new WeakRefCache());
            }
        }
    }

    public void dialog() {
        choseCacheType();
        while (menuState) {
            System.out.println("""
                    =====================
                    Choose action:
                        1) Get file
                        2) Load file
                        3) Get cached files
                        4) Clean cache
                        5) Quit
                    =====================
                    """
            );
            Scanner scanner = new Scanner(System.in);
            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.println("Write file name:");
                    scanner = new Scanner(System.in);
                    fileService.get(scanner.nextLine());
                }
                case 2 -> {
                    System.out.println("Write file name:");
                    scanner = new Scanner(System.in);
                    fileService.put(scanner.nextLine());
                }
                case 3 -> fileService.getCachedFiles();

                case 4 -> fileService.removeWeakLinksAndClearCache();

                case 5 -> setMenuState(false);

                default -> System.out.println("Wrong action");
            }
        }
    }
}
