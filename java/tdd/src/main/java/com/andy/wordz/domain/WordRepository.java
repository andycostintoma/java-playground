package com.andy.wordz.domain;

public interface WordRepository {
    String fetchWordByNumber(int number);

    int highestWordNumber();
}
