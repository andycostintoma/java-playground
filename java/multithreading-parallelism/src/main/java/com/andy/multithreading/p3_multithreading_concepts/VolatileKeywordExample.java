package com.andy.multithreading.p3_multithreading_concepts;

/**
 * This class demonstrates the use of the 'volatile' keyword to control thread termination.
 */
public class VolatileKeywordExample {

    static class Worker implements Runnable {

        // The 'volatile' keyword ensures that the 'terminated' variable is read from and written to the main memory.
        // Without 'volatile,' the variable might be cached, leading to potential visibility issues.
        private volatile boolean terminated;

        @Override
        public void run() {
            while (!terminated) {
                System.out.println("Working class is running...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public void setTerminated(boolean terminated) {
            this.terminated = terminated;
        }
    }

    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();

        try {
            // Sleep for 3 seconds to allow the worker thread to run.
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        // Set the 'terminated' flag to true, signaling the worker thread to terminate.
        worker.setTerminated(true);
        System.out.println("Algorithm is terminated...");
    }
}
