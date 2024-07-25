package com.example.otus.utils;

public class MemoryMonitor {


    private static final long KILOBYTE = 1024;
    private static final long MEGABYTE = KILOBYTE * KILOBYTE;

    public static void printMemoryUsage(String prefix) {
        long total = getTotalMemoryInMB();
        System.out.println(prefix + "Total Memory  : " + total + " MB");

        long max = getMaxMemoryInMB();
        System.out.println(prefix + "Max Memory    : " + max + " MB");

        long free = getFreeMemoryInMB();
        System.out.println(prefix + "Free Memory   : " + free + " MB");

        long usedMemoryInMB = total - free;
        System.out.println(prefix + "Used Memory   : " + usedMemoryInMB + " MB");
    }

    private static long getFreeMemoryInMB() {
        return Runtime.getRuntime().freeMemory() / MEGABYTE;
    }

    private static long getMaxMemoryInMB() {
        return Runtime.getRuntime().maxMemory() / MEGABYTE;
    }

    public static long getTotalMemoryInMB() {
        return Runtime.getRuntime().totalMemory() / MEGABYTE;
    }
}
