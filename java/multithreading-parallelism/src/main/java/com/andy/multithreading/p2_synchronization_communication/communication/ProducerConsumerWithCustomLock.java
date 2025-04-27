package com.andy.multithreading.p2_synchronization_communication.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a producer-consumer scenario using custom locking mechanisms.
 * - It uses an intrinsic lock ('lock') and 'wait()'/'notify()' for synchronization.
 * - The producer waits when the list is full, and the consumer waits when it's empty.
 */
public class ProducerConsumerWithCustomLock {

    static class Processor {

        private final List<Integer> list = new ArrayList<>();
        private static final int UPPER_LIMIT = 5;
        private final Object lock = new Object();
        private int val = 0;

        public void producer() throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    if (list.size() == UPPER_LIMIT + 1) {
                        System.out.println("Waiting for removing items...");
                        lock.wait();
                    } else {
                        System.out.println("Adding: " + val);
                        list.add(val);
                        val++;
                        // Signal the consumer when adding an item.
                        lock.notify();
                    }
                    Thread.sleep(500);
                }
            }
        }

        public void consumer() throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    if (list.isEmpty()) {
                        System.out.println("Waiting for adding items...");
                        lock.wait();
                    } else {
                        System.out.println("Removing: " + list.remove(list.size() - 1));
                        val--;
                        // Signal the producer when removing an item.
                        lock.notify();
                    }
                    Thread.sleep(500);
                }
            }
        }
    }

    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}
