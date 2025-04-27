package com.andy.multithreading.p2_synchronization_communication.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class demonstrates a simple producer-consumer scenario using ReentrantLock and Condition for synchronization.
 * - Explicit lock ownership.
 * - 'await()' and 'signal()' for waiting and signaling.
 */
public class SimpleProducerConsumerWithReentrantLock {

    static class Processor {
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();

        public void produce() throws InterruptedException {
            lock.lock();
            System.out.println("Producer method...");
            condition.await(); // Release the lock and wait for a signal from the consumer.
            System.out.println("Again the producer method...");
            lock.unlock();
        }

        public void consume() throws InterruptedException {
            Thread.sleep(2000); // Simulate some work before signaling the producer.
            lock.lock();
            System.out.println("Inside the consumer method...");
            condition.signal(); // Signal the waiting producer thread.
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
