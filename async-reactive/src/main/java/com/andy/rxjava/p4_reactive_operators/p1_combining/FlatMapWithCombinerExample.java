package com.andy.rxjava.p4_reactive_operators.p1_combining;

import io.reactivex.rxjava3.core.Observable;

public class FlatMapWithCombinerExample {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .flatMap(
                        s -> Observable.fromArray(s.split("")),
                        (original, letter) -> original + "-" + letter
                )
                .subscribe(System.out::println);
    }
}
