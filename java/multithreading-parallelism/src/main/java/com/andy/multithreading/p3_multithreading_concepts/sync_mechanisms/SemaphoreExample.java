package com.andy.multithreading.p3_multithreading_concepts.sync_mechanisms;

import java.util.concurrent.Semaphore;

/**
 * <p><strong>Purpose:</strong> A semaphore is a more general synchronization mechanism used to control access to a resource with limited capacity. It allows multiple threads to coordinate their activities and manage access to a shared resource, which may have a specified limit on the number of concurrent users.</p>
 * <p><strong>Usage:</strong> Semaphores are used in situations where you want to control the number of threads that can access a resource simultaneously, implement producer-consumer patterns, or synchronize threads with different tasks.</p>
 * <p><strong>Properties:</strong></p>
 * <ul>
 * <li>Semaphores have an associated count that represents the number of available permits (units).</li>
 * <li>Threads can acquire permits from the semaphore (decreasing the count) and release them (increasing the count).</li>
 * <li>Semaphores can be used to implement various synchronization patterns, such as binary semaphores (mutexes) and counting semaphores.</li>
 * </ul>
 */

public class SemaphoreExample {
    static class ParkingLot {
        private final Semaphore semaphore;

        public ParkingLot(int totalSpots) {
            semaphore = new Semaphore(totalSpots, true); // Use a fair semaphore
        }

        public void enter() {
            try {
                semaphore.acquire(); // Acquire a permit (lock) from the semaphore
                System.out.println(Thread.currentThread().getName() + " has entered the parking lot.");
                Thread.sleep(1000); // Simulate the car's stay in the parking lot
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } finally {
                semaphore.release(); // Release the permit (unlock) back to the semaphore
                System.out.println(Thread.currentThread().getName() + " has exited the parking lot.");
            }
        }
    }

    public static void main(String[] args) {
        int totalParkingSpots = 3; // Total available parking spots
        ParkingLot parkingLot = new ParkingLot(totalParkingSpots);

        // Simulate multiple cars trying to enter the parking lot
        for (int i = 1; i <= 5; i++) {
            Thread carThread = new Thread(parkingLot::enter, "Car " + i);
            carThread.start();
        }
    }
}
