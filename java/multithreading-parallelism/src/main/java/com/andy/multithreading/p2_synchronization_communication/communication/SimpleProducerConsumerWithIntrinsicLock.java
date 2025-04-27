package com.andy.multithreading.p2_synchronization_communication.communication;

/**
 * This class demonstrates a simple producer-consumer scenario using intrinsic locks for synchronization.
 * - Implicit lock ownership.
 * - 'wait()' and 'notify()' for waiting and signaling.
 */
public class SimpleProducerConsumerWithIntrinsicLock {

    static class Processor {

        public void produce() throws InterruptedException {
            synchronized (this) {
                System.out.println("Running the produce method...");
                // Release the intrinsic lock and wait for a notification from the consumer.
                wait();
                System.out.println("Again in the producer method...");
            }
        }

        public void consume() throws InterruptedException {
            Thread.sleep(1000); // Simulate some work before notifying the producer.
            synchronized (this) {
                System.out.println("Consume method is executed...");
                // Notify the other thread (producer) that it can continue with its execution.
                notify();
                // Further operations can be performed without the lock.
                Thread.sleep(1000);
            }
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
                System.out.println(e.getMessage());
            }
        });

        t1.start();
        t2.start();
    }
}
