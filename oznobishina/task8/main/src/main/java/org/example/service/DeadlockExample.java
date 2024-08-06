package org.example.service;

import static java.lang.Thread.sleep;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadlockExample {


    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        DeadlockExample deadlock = new DeadlockExample();
        new Thread(deadlock::operation1, "T1").start();
        new Thread(deadlock::operation2, "T2").start();
    }

    public void operation1() {
        boolean executed = false;
        while (!executed) {
            if (lock1.tryLock() && lock2.tryLock()) {
                log.debug("lock1 acquired, waiting to acquire lock2.");
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("lock2 acquired");

                log.debug("executing first operation.");

                lock2.unlock();
                lock1.unlock();
            } else {
                lock1.unlock();
            }
        }
    }

    public void operation2() {
        boolean executed = false;

        while (!executed) {
            if (lock1.tryLock() && lock2.tryLock()) {
                log.debug("lock2 acquired, waiting to acquire lock1.");
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("lock1 acquired");

                log.debug("executing second operation.");

                lock1.unlock();
                lock2.unlock();
            }else {
                lock1.unlock();
            }
        }
    }

}
