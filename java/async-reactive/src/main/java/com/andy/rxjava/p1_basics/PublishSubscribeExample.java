package com.andy.rxjava.p1_basics;

import java.util.ArrayList;
import java.util.List;

/**
 * This example demonstrates a reactive-style API for modeling spreadsheet cells and arithmetic cells.
 */
public class PublishSubscribeExample {

    public static void main(String[] args) {
        ArithmeticCell c3 = new ArithmeticCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);

        c1.onNext(10); // Update value of C1 to 10
        c2.onNext(20); // Update value of C2 to 20

        System.out.println();

        c1.onNext(15); // Update value of C1 to 15
    }

    /**
     * Interface for a cell that can publish values and subscribe to changes.
     */
    private interface Publisher<T> {
        void subscribe(Subscriber<? super T> subscriber);
    }

    /**
     * Interface for a subscriber that reacts to changes in a cell's value.
     */
    private interface Subscriber<T> {
        void onNext(T t);
    }

    /**
     * A simple cell that holds an integer value.
     */
    private static class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
        private int value = 0;
        private final String name;
        private final List<Subscriber> subscribers = new ArrayList<>();

        public SimpleCell(String name) {
            this.name = name;
        }

        @Override
        public void subscribe(Subscriber<? super Integer> subscriber) {
            subscribers.add(subscriber);
        }

        private void notifyAllSubscribers() {
            subscribers.forEach(subscriber -> subscriber.onNext(this.value));
        }

        @Override
        public void onNext(Integer newValue) {
            this.value = newValue;
            System.out.println(this.name + ":" + this.value);
            notifyAllSubscribers();
        }
    }

    /**
     * An arithmetic cell that can perform addition based on its left and right sides.
     */
    private static class ArithmeticCell extends SimpleCell {
        private int left;
        private int right;

        public ArithmeticCell(String name) {
            super(name);
        }

        public void setLeft(int left) {
            this.left = left;
            onNext(left + this.right);
        }

        public void setRight(int right) {
            this.right = right;
            onNext(right + this.left);
        }
    }
}
