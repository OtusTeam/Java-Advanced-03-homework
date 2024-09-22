package org.ksu.menu;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.ksu.storage.ByteBufferStorage;
import org.ksu.storage.MappedByteBufferStorage;
import org.ksu.storage.Storage;

public class Emulator {

    private static int storageSize = 1024;

    private static Map<String, Storage> bufferMap = new HashMap<>();


    public static void showMenu() {
        System.out.println("""
                1 - Enter file name to load in ByteBuffer
                2 - Enter file name to load in MappedByteBuffer
                3 - Set off-heap size in B
                4 - Get file content from off-heap storage
                5 - Exit
                """);

        Scanner input = new Scanner(System.in);
        String commandNumber = input.nextLine();
        String filename;

        switch (commandNumber) {
            case "1":
                filename = enterFileName();
                createByteBuffer(filename);
                break;
            case "2":
                filename = enterFileName();
                createMappedByteBuffer(filename);
                break;
            case "3":
                setStorageSize();
                break;
            case "4":
                getContentFromByteBuffer();
                break;
            case "5":
                System.exit(0);
                break;
            default:
                System.out.println("Command doesn't exist");
        }
    }

    private static void createMappedByteBuffer(String filename) {
        Path path = getPath(filename);

        MappedByteBufferStorage mapppedByteBufferStorage = new MappedByteBufferStorage(path, storageSize);

        bufferMap.put(filename, mapppedByteBufferStorage);

        System.out.println("MappedByteBufferStorage created for " + filename);
    }

    private static void createByteBuffer(String filename) {
        Path path = getPath(filename);

        ByteBufferStorage byteBufferStorage = new ByteBufferStorage(path, storageSize);

        bufferMap.put(filename, byteBufferStorage);

        System.out.println("ByteBufferStorage created for" + filename);
    }

    private static void getContentFromByteBuffer() {
        Storage storage = null;
        String filename = enterFileName();
        storage = bufferMap.get(filename);
        System.out.println(storage.getFromByteBuffer());
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

    private static String enterFileName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter file name: ");
        return input.nextLine();
    }

    private static Path getPath(String filename) {
        try {
            var url = Emulator.class.getClassLoader()
                    .getResource(filename);
            return Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
