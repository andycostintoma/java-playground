package com.andy.multithreading.p1_thread_basics;



public class DaemonThreadsExample {

    static class DaemonWorker implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello from daemon thread");
        }
    }

    static class NormalWorker implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello from normal thread");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new DaemonWorker());
        Thread t2 = new Thread(new NormalWorker());

        t1.setDaemon(true);

        t1.start();
        t2.start();
    }
}
