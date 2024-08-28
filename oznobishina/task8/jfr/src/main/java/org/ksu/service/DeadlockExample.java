package org.ksu.service;

import static java.lang.Thread.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadlockExample {

    private final Lock lock1 = new ReentrantLock(true);
    private final Lock lock2 = new ReentrantLock(true);

    public void operation1() {
        lock1.lock();
        log.info("lock1 acquired, waiting to acquire lock2.");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }

        lock2.lock();
        log.info("lock2 acquired");

        log.info("executing first operation.");

        lock2.unlock();
        lock1.unlock();
    }

    public void operation2() {
        lock2.lock();
        log.info("lock2 acquired, waiting to acquire lock1.");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
        }

        lock1.lock();
        log.info("lock1 acquired");

        log.info("executing second operation.");

        lock1.unlock();
        lock2.unlock();
    }
}
