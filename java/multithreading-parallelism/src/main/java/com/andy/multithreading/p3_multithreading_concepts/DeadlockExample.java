package com.andy.multithreading.p3_multithreading_concepts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class demonstrates a simple livelock scenario.
 * Two threads repeatedly attempt to acquire locks, responding to each other's actions,
 * but remain stuck in a loop without making progress.
 * Livelock occurs when threads actively try to resolve a situation by releasing locks
 * but end up in an action-reaction loop, preventing real progress.
 */

public class DeadlockExample {

    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        DeadlockExample deadlockExample = new DeadlockExample();
        Thread thread1 = new Thread(deadlockExample::worker1, "worker1");
        Thread thread2 = new Thread(deadlockExample::worker2, "worker2");

        // Start both threads to create a potential deadlock scenario.
        thread1.start();
        thread2.start();
    }

    // The worker1 method attempts to acquire lock1 first and then lock2.
    public void worker1() {
        lock1.lock();
        System.out.println("Worker1 acquired the lock1...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // To create a deadlock, worker1 attempts to acquire lock2 after acquiring lock1.
        lock2.lock();
        System.out.println("Worker1 acquired the lock2...");

        // Release both locks to avoid a real deadlock.
        lock1.unlock();
        lock2.unlock();
    }

    // The worker2 method attempts to acquire lock2 first and then lock1.
    public void worker2() {
        lock2.lock();
        System.out.println("Worker2 acquired the lock2...");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // To create a deadlock, worker2 attempts to acquire lock1 after acquiring lock2.
        lock1.lock();
        System.out.println("Worker2 acquired the lock1...");

        // Release both locks to avoid a real deadlock.
        lock1.unlock();
        lock2.unlock();
    }
}
