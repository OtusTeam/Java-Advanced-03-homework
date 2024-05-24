package com.memory;

import com.memory.menu.Emulator;

public class Main {

    public static void main(String[] args) {
        Emulator emulator = new Emulator();

        while (true) {
            emulator.showMenu();
        }
    }
}
