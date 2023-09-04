package com.andy.multithreading.p2_synchronization_communication.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements a producer-consumer scenario using ReentrantLock and Conditions.
 * - It uses a ReentrantLock ('lock') with 'await()' and 'signal()' for synchronization.
 * - The producer waits when the list is full ('notFull') and signals the consumer when adding an item.
 * - The consumer waits when the list is empty ('notEmpty') and signals the producer when removing an item.
 */
public class ProducerConsumerWithReentrantLock {

    static class Processor {

        private final List<Integer> list = new ArrayList<>();
        private static final int UPPER_LIMIT = 5;
        private final Lock lock = new ReentrantLock();
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();
        private int val = 0;

        public void producer() throws InterruptedException {
            lock.lock();
            try {
                while (true) {
                    if (list.size() == UPPER_LIMIT) {
                        System.out.println("Waiting for removing items...");
                        notFull.await();
                    } else {
                        System.out.println("Adding: " + val);
                        list.add(val);
                        val++;
                        // Signal the consumer that the list is not empty.
                        notEmpty.signal();
                    }
                    Thread.sleep(500);
                }
            } finally {
                lock.unlock();
            }
        }

        public void consumer() throws InterruptedException {
            lock.lock();
            try {
                while (true) {
                    if (list.isEmpty()) {
                        System.out.println("Waiting for adding items...");
                        notEmpty.await();
                    } else {
                        System.out.println("Removing: " + list.remove(list.size() - 1));
                        val--;
                        // Signal the producer that the list is not full.
                        notFull.signal();
                    }
                    Thread.sleep(500);
                }
            } finally {
                lock.unlock();
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
