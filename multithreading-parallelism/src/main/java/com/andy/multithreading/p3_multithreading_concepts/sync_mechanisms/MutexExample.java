package com.andy.multithreading.p3_multithreading_concepts.sync_mechanisms;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p> Mutex (Mutual Exclusion)</p>
 * <p><strong>Purpose:</strong> A mutex is primarily used to achieve mutual exclusion, ensuring that only one thread at a time can access a specific resource or section of code. It allows a thread to lock and unlock a resource to prevent multiple threads from simultaneously modifying it.</p>
 * <p><strong>Usage:</strong> Mutexes are typically used to protect critical sections of code or shared resources where concurrent access must be controlled, such as data structures, files, or hardware devices.</p>
 * <p><strong>Properties:</strong></p>
 * <ul>
 * <li>Mutexes are binary, meaning they have only two states: locked and unlocked.</li>
 * <li>Only the thread that successfully acquires the mutex (locks it) can release it (unlock it).</li>
 * <li>Mutexes are often associated with ownership, where the thread that locks it must be the one to unlock it.</li>
 * </ul>
 */

public class MutexExample {
    static class BankAccount {
        private double balance = 1000.0;
        private final Lock lock = new ReentrantLock();

        public void deposit(double amount) {
            lock.lock(); // Acquire the lock
            try {
                balance += amount;
                System.out.println("Deposited: " + amount);
            } finally {
                lock.unlock(); // Release the lock
            }
        }

        public void withdraw(double amount) {
            lock.lock(); // Acquire the lock
            try {
                if (balance >= amount) {
                    balance -= amount;
                    System.out.println("Withdrawn: " + amount);
                } else {
                    System.out.println("Insufficient funds.");
                }
            } finally {
                lock.unlock(); // Release the lock
            }
        }

        public double getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        // Simulate multiple threads performing transactions
        Thread thread1 = new Thread(() -> account.deposit(200.0));
        Thread thread2 = new Thread(() -> account.withdraw(300.0));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Final Balance: " + account.getBalance());
    }
}
