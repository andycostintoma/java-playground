package com.andy.multithreading.p1_thread_basics;

public class ThreadBasics {

    public static void main(String[] args) {

        class Runner1 implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Thread1: " + i);
                }
            }
        }

        class Runner3 extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Thread3: " + i);
                }
            }
        }

        Runner1 runner1 = new Runner1();

        Thread t1 = new Thread(runner1);
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread2: " + i);
            }
        });
        Thread t3 = new Runner3();

        t1.start();
        t2.start();
        t3.start();

        // we can wait for threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("End of application");

    }
}
