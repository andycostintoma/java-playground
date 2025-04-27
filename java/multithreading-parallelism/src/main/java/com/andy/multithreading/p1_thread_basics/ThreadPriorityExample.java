package com.andy.multithreading.p1_thread_basics;

public class ThreadPriorityExample {

    static class Worker implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }
    }
    public static void main(String[] args) {

        Thread t = new Thread(new Worker());
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();

        // Thread with priority 5 - default -> executed first because of OS
        System.out.println("This is in the main thread...");
    }
}
