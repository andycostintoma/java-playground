package com.andy.multithreading.p5_concurrent_collections;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueExample {
    static class DelayedElement implements Delayed {
        private final String message;
        private final long duration;

        public DelayedElement(String message, long duration) {
            this.message = message;
            this.duration = System.currentTimeMillis() + duration;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long diff = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
            return Long.compare(diff, 0);
        }

        @Override
        public String toString() {
            return "Task " + message;
        }
    }

    public static void main(String[] args) {
        DelayQueue<DelayedElement> delayQueue = new DelayQueue<>();

        // Create and add delayed tasks to the queue
        delayQueue.put(new DelayedElement("A", 3000)); // 3-second delay
        delayQueue.put(new DelayedElement("B", 2000)); // 2-second delay
        delayQueue.put(new DelayedElement("C", 5000)); // 5-second delay

        while (!delayQueue.isEmpty()){
            try {
                System.out.println(delayQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
