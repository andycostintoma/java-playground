package com.andy.multithreading.p2_synchronization_communication.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class demonstrates the use of the 'ReentrantLock' class for synchronization.
 * It provides more flexibility and control than the 'synchronized' keyword, allowing for explicit locking and unlocking.
 */


public class SynchronizationWithReentrantLock {

    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();

    private static void increment() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++)
                counter++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(SynchronizationWithReentrantLock::increment);
        Thread t2 = new Thread(SynchronizationWithReentrantLock::increment);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(counter);

    }
}
