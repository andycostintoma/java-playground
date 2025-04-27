package com.andy.multithreading.p2_synchronization_communication.communication;

import java.util.concurrent.Exchanger;

/**
 * <p><strong>Exchanger Example:</strong></p>
 *
 * <p><strong>Purpose:</strong> The Exchanger class is used for two threads to exchange data at a synchronization point, waiting for each other until both threads are ready to proceed.</p>
 *
 * <p><strong>Usage:</strong> Exchangers are useful when two threads need to cooperate by synchronizing and exchanging data between them.</p>
 *
 * <p><strong>Key Concepts:</strong></p>
 * <ul>
 *   <li>Two threads, the producer and the consumer, use an Exchanger to exchange data.</li>
 *   <li>Each thread calls the exchange() method, which blocks until both threads have arrived at the exchange point.</li>
 *   <li>Once both threads have called exchange(), they swap data, and both can continue execution.</li>
 * </ul>
 */
public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        // Producer thread
        new Thread(() -> {
            try {
                String dataToSend = "Hello from Producer!";
                System.out.println("Producer sends: " + dataToSend);

                // Wait for the consumer to arrive at the exchange point
                String receivedData = exchanger.exchange(dataToSend);

                System.out.println("Producer received: " + receivedData);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                Thread.sleep(1000); // Simulate some processing time
                String dataToSend = "Hello from Consumer!";
                System.out.println("Consumer sends: " + dataToSend);

                // Wait for the producer to arrive at the exchange point
                String receivedData = exchanger.exchange(dataToSend);

                System.out.println("Consumer received: " + receivedData);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }).start();
    }
}
