package org.ksu.menu;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.ksu.storage.ByteBufferStorage;
import org.ksu.storage.MappedByteBufferStorage;

public class Emulator {

    private static Path filepath;
    private static int storageSize = 1024;

    public static void showMenu() {
        System.out.println("""
                1 - Enter file name to load
                2 - Set off-heap size in B
                3 - Get file content from ByteBuffer off-heap storage
                4 - Get file content from MappedByteBuffer off-heap storage
                5 - Exit
                """);

        Scanner input = new Scanner(System.in);
        String commandNumber = input.nextLine();

        switch (commandNumber) {
            case "1":
                enterFileName();
                break;
            case "2":
                setStorageSize();
                break;
            case "3":
                getContentFromByteBuffer();
                break;
            case "4":
                getContentFromMappedByteBuffer();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                System.out.println("Command doesn't exist");
        }
    }

    private static void getContentFromMappedByteBuffer() {
        MappedByteBufferStorage storage2 = null;
        try {
            storage2 = new MappedByteBufferStorage(Emulator.filepath, storageSize);
            System.out.println(storage2.getFromMappedByteBuffer());

            storage2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static void getContentFromByteBuffer() {
        try {
            ByteBufferStorage storage = new ByteBufferStorage(Emulator.filepath, storageSize);
            System.out.println(storage.getFromByteBuffer());

            storage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setStorageSize() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter storage size: ");
            storageSize = input.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void enterFileName() {
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter file name: ");
            String filename = input.nextLine();

            var url = Emulator.class.getClassLoader()
                    .getResource(filename);
            filepath = Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }
}
