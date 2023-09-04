package com.andy.multithreading.p6_problems;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This program simulates the Dining Philosophers problem using multithreading and locks.
 * The Dining Philosophers problem involves a group of philosophers sitting at a round table
 * with a bowl of spaghetti. Each philosopher thinks and eats spaghetti. To eat, a philosopher
 * must pick up the two adjacent forks (chopsticks). The challenge is to avoid deadlock and
 * ensure that each philosopher can eat safely without conflicts.
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>There are a fixed number of philosophers and chopsticks.</li>
 *   <li>Each philosopher thinks and eats in a continuous loop.</li>
 *   <li>To eat, a philosopher must pick up both the left and right chopsticks.</li>
 *   <li>Locks (ReentrantLock) are used to ensure exclusive access to chopsticks.</li>
 *   <li>Philosophers use tryLock to avoid deadlock, and they put down chopsticks when done.</li>
 *   <li>The program tracks how many times each philosopher has eaten.</li>
 * </ul>
 */


public class DiningPhilosophers {

    public static final int NUMBER_OF_PHILOSOPHERS = 5;
    public static final int NUMBER_OF_CHOPSTICKS = 5;
    public static final int SIMULATION_RUNNING_TIME = 5000;

    static class Chopstick {
        private final int id;
        private final Lock lock = new ReentrantLock();

        public Chopstick(int id) {
            this.id = id;
        }

        public boolean pickUp(Philosopher philosopher, State state) throws InterruptedException {
            // Use tryLock to prevent deadlock
            if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
                System.out.printf("%s picked up %s %s%n", philosopher, state, this);
                return true;
            }
            return false;
        }

        public void putDown(Philosopher philosopher, State state) {
            lock.unlock();
            System.out.println(philosopher + " put down " + state + " " + this);
        }

        @Override
        public String toString() {
            return "Chopstick " + id;
        }
    }

    static class Philosopher implements Runnable {
        private final int id;
        private volatile boolean full;
        private final Chopstick leftChopstick;
        private final Chopstick rightChopstick;
        private final Random random = new Random();
        private int eatingCounter;

        public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
            this.id = id;
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
        }

        @Override
        public void run() {
            try {
                while (!full) {
                    think();
                    if (leftChopstick.pickUp(this, State.LEFT)) {
                        if (rightChopstick.pickUp(this, State.RIGHT)) {
                            eat();
                            rightChopstick.putDown(this, State.RIGHT);
                        }
                        leftChopstick.putDown(this, State.LEFT);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        private void think() throws InterruptedException {
            System.out.println(this + " is thinking...");
            Thread.sleep(random.nextInt(1000));
        }

        private void eat() throws InterruptedException {
            System.out.println(this + " is eating...");
            eatingCounter++;
            Thread.sleep(random.nextInt(1000));
        }

        public int getEatingCounter() {
            return eatingCounter;
        }

        public void setFull(boolean full) {
            this.full = full;
        }

        @Override
        public String toString() {
            return "Philosopher " + id;
        }
    }

    enum State {
        LEFT,
        RIGHT
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);
        Philosopher[] philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
        Chopstick[] chopsticks = new Chopstick[NUMBER_OF_CHOPSTICKS];

        try {
            for (int i = 0; i < NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new Chopstick(i);
            }

            for (int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % NUMBER_OF_PHILOSOPHERS]);
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(SIMULATION_RUNNING_TIME);

            for (Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }
        } finally {
            executorService.shutdown();
            while (!executorService.isTerminated()) {
                Thread.sleep(1000);
            }

            for (Philosopher philosopher : philosophers) {
                System.out.println(philosopher + " has eaten " + philosopher.getEatingCounter() + " times");
            }
        }
    }
}
