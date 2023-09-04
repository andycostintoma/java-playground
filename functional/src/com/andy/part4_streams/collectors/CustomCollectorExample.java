package com.andy.part4_streams.collectors;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CustomCollectorExample {

    static class Joinector implements Collector<CharSequence, StringJoiner, String> {

        private final CharSequence delimiter;

        public Joinector(CharSequence delimiter) {
            this.delimiter = delimiter;
        }

        @Override
        public Supplier<StringJoiner> supplier() {
            return () -> new StringJoiner(delimiter);
        }

        @Override
        public BiConsumer<StringJoiner, CharSequence> accumulator() {
            return StringJoiner::add;
        }

        @Override
        public BinaryOperator<StringJoiner> combiner() {
            return StringJoiner::merge;
        }

        @Override
        public Function<StringJoiner, String> finisher() {
            return StringJoiner::toString;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.emptySet();
        }
    }

    public static void main(String[] args) {

        CharSequence delimiter = " ";
        List<CharSequence> sample = Arrays.asList("This", "is", "a", "sample", "text");

        Joinector joinector1 = new Joinector(delimiter);

        String result1 = sample.stream()
                .collect(joinector1);
        System.out.println("Custom Collector Result 1: " + result1);

        Collector<CharSequence, StringJoiner, String> joinector2 =
                Collector.of(() -> new StringJoiner(delimiter), // supplier
                        StringJoiner::add,                 // accumulator
                        StringJoiner::merge,               // combiner
                        StringJoiner::toString);           // finisher


        String result2 = sample.stream()
                .collect(joinector2);
        System.out.println("Custom Collector Result 2: " + result2);


    }

}
