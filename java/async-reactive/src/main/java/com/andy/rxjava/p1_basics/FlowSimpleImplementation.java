package com.andy.rxjava.p1_basics;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.TimeUnit;

/**
 * This class demonstrates a simple implementation of the Java Flow API
 * for reactive programming. It simulates temperature updates for different towns
 * and allows subscribers to receive and process these updates.
 */
public class FlowSimpleImplementation {
    /**
     * Main method to demonstrate temperature updates for New York and Los Angeles.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Subscribe to temperature updates for New York
        getTemperatures("New York").subscribe(new TempSubscriber());

        // Subscribe to temperature updates for Los Angeles in Celsius
        getCelsiusTemperatures("Los Angeles").subscribe(new TempSubscriber());
    }

    // Define a record to represent temperature information
    record TempInfo(String town, int temp) {
        // Random number generator for temperature simulation
        public static final Random random = new Random();

        /**
         * Simulates fetching the current temperature for a town.
         *
         * @param town The name of the town.
         * @return A TempInfo object with town name and a random temperature.
         * @throws InterruptedException If there's a delay during simulation.
         */
        public static TempInfo fetch(String town) throws InterruptedException {
            TimeUnit.MILLISECONDS.sleep(500);
            if (random.nextInt(10) == 0) throw new RuntimeException("Error!");
            return new TempInfo(town, random.nextInt(100));
        }

        /**
         * Provides a string representation of the TempInfo object.
         *
         * @return A string containing the town name and temperature.
         */
        @Override
        public String toString() {
            return town + " : " + temp;
        }
    }

    // Define a Subscription for temperature updates
    static class TempSubscription implements Subscription {
        private final Subscriber<? super TempInfo> subscriber;
        private final String town;
        private static final ExecutorService executor =
                Executors.newSingleThreadExecutor();

        /**
         * Creates a TempSubscription for a subscriber and town.
         *
         * @param subscriber The subscriber receiving temperature updates.
         * @param town       The name of the town for which updates are requested.
         */
        public TempSubscription(Subscriber<? super TempInfo> subscriber,
                                String town) {
            this.subscriber = subscriber;
            this.town = town;
        }

        /**
         * Requests temperature updates from the publisher.
         *
         * @param n The number of updates to request.
         */
        @Override
        public void request(long n) {
            executor.submit(() -> {
                for (long i = 0L; i < n; i++) {
                    try {
                        subscriber.onNext(TempInfo.fetch(town));
                    } catch (Exception e) {
                        subscriber.onError(e);
                        break;
                    }
                }
            });
        }

        /**
         * Cancels the subscription and notifies the subscriber that it's complete.
         */
        @Override
        public void cancel() {
            subscriber.onComplete();
        }
    }

    // Define a subscriber for temperature updates
    static class TempSubscriber implements Subscriber<TempInfo> {
        private Subscription subscription;

        /**
         * Initializes the subscriber and requests the first temperature update.
         *
         * @param subscription The subscription for temperature updates.
         */
        @Override
        public void onSubscribe(Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        /**
         * Receives and processes a temperature update.
         *
         * @param tempInfo The received temperature update.
         */
        @Override
        public void onNext(TempInfo tempInfo) {
            System.out.println(tempInfo);
            subscription.request(1);
        }

        /**
         * Handles and prints errors that occur during processing.
         *
         * @param t The Throwable representing the error.
         */
        @Override
        public void onError(Throwable t) {
            System.err.println(t.getMessage());
        }

        /**
         * Notifies the subscriber that the subscription is complete.
         */
        @Override
        public void onComplete() {
            System.out.println("Done!");
        }
    }

    // Define a processor for transforming temperature updates to Celsius
    static class TempProcessor implements Processor<TempInfo, TempInfo> {
        private Subscriber<? super TempInfo> subscriber;

        /**
         * Subscribes the processor to a subscriber.
         *
         * @param subscriber The subscriber to which the processor sends updates.
         */
        @Override
        public void subscribe(Subscriber<? super TempInfo> subscriber) {
            this.subscriber = subscriber;
        }

        /**
         * Receives a temperature update, converts it to Celsius, and sends it to the subscriber.
         *
         * @param temp The received temperature update in Fahrenheit.
         */
        @Override
        public void onNext(TempInfo temp) {
            subscriber.onNext(new TempInfo(temp.town(),
                    (temp.temp() - 32) * 5 / 9));
        }

        /**
         * Forwards the subscription to the upstream subscriber.
         *
         * @param subscription The subscription to forward.
         */
        @Override
        public void onSubscribe(Subscription subscription) {
            subscriber.onSubscribe(subscription);
        }

        /**
         * Handles and forwards errors to the upstream subscriber.
         *
         * @param throwable The Throwable representing the error.
         */
        @Override
        public void onError(Throwable throwable) {
            subscriber.onError(throwable);
        }

        /**
         * Notifies the upstream subscriber that processing is complete.
         */
        @Override
        public void onComplete() {
            subscriber.onComplete();
        }
    }

    /**
     * Creates a publisher for temperature updates of a specific town.
     *
     * @param town The name of the town for which updates are requested.
     * @return A publisher emitting temperature updates.
     */
    private static Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(
                new TempSubscription(subscriber, town));
    }

    /**
     * Creates a publisher for temperature updates in Celsius of a specific town.
     *
     * @param town The name of the town for which Celsius updates are requested.
     * @return A publisher emitting temperature updates in Celsius.
     */
    private static Publisher<TempInfo> getCelsiusTemperatures(String town) {
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubscription(processor, town));
        };
    }
}
