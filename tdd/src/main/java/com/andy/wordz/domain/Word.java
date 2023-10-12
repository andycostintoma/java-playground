package com.andy.wordz.domain;

public class Word {

    private final String word;

    public Word(String word) {
        this.word = word;
    }

    public Score guess(String attempt) {
        Score score = new Score(word);
        score.assess(attempt);
        return score;
    }
}
