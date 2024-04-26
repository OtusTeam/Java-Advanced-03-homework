package ru.otus.evgrez;

import ru.otus.evgrez.service.WordsFileService;

import java.util.List;
import java.util.Scanner;

import static ru.otus.evgrez.service.WordsFileFactory.getWordsFileServiceWeakRef;

public class Emulator {

    public static void main(String[] args) {
        System.out.println("Enter folder name: ");
        Scanner in = new Scanner(System.in);
        String folderName = in.nextLine();
        WordsFileService wordsFileService = getWordsFileServiceWeakRef(folderName);
        String fileName = null;
        while (true) {
            System.out.println("Enter file name: ");
            fileName = in.nextLine();
            if (fileName == null || fileName.isBlank()) {
                break;
            }
            print(wordsFileService.getWordsByFileName(fileName));
            System.gc();
        }
        System.out.println("Exit");
    }

    private static void print(List<String> stringList) {
        System.out.println("first 5 elements");
        int i = 0;
        for (var value : stringList) {
            System.out.println(value);
            i++;
            if (i >= 5) {
                break;
            }
        }
        System.out.printf("size: %s%n", stringList.size());
    }
}
