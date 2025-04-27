package com.andy.multithreading.p3_multithreading_concepts;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class demonstrates a simple livelock scenario.
 * Two threads repeatedly attempt to acquire locks, responding to each other's actions,
 * but remain stuck in a loop without making progress.
 * Livelock occurs when threads actively try to resolve a situation by releasing locks
 * but end up in an action-reaction loop, preventing real progress.
 */

public class LivelockExample {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        LivelockExample livelockExample = new LivelockExample();
        Thread thread1 = new Thread(livelockExample::worker1, "worker1");
        Thread thread2 = new Thread(livelockExample::worker2, "worker2");

        thread1.start();
        thread2.start();
    }

    // The worker1 method repeatedly tries to acquire lock1 and then lock2.
    public void worker1() {
        while (true) {
            try {
                // Attempt to acquire lock1 with a timeout.
                if (lock1.tryLock(500, TimeUnit.MILLISECONDS)) {
                    System.out.println("Worker1 acquires the lock1...");
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Worker1 tries to get lock2...");

            if (lock2.tryLock()) {
                System.out.println("Worker1 acquires the lock2...");
                lock2.unlock();
            } else {
                System.out.println("Worker1 can not acquire lock2...");
                continue; // Retry acquiring lock2.
            }
            break; // Exit the loop after successfully acquiring both locks.
        }
        lock1.unlock();
        lock2.unlock();
    }

    // The worker2 method repeatedly tries to acquire lock2 and then lock1.
    public void worker2() {
        while (true) {
            try {
                // Attempt to acquire lock2 with a timeout.
                if (lock2.tryLock(500, TimeUnit.MILLISECONDS)) {
                    System.out.println("Worker2 acquires the lock2...");
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Worker2 tries to get lock1...");

            if (lock2.tryLock()) {
                System.out.println("Worker2 acquires the lock1...");
                lock2.unlock();
            } else {
                System.out.println("Worker2 can not acquire lock1...");
                continue; // Retry acquiring lock1.
            }
            break; // Exit the loop after successfully acquiring both locks.
        }
        lock1.unlock();
        lock2.unlock();
    }
}
