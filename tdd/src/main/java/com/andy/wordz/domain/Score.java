package com.andy.wordz.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score {
    private final String correct;
    private final List<Letter> results = new ArrayList<>();
    private int position;

    public Score(String correct) {
        this.correct = correct;
    }

    public Letter letter(int position) {
        return results.get(position);
    }

    public boolean allCorrect() {
        return results.stream().allMatch(Letter.CORRECT::equals);
    }

    public List<Letter> letters() {
        return Collections.unmodifiableList(results);
    }

    public void assess(String attempt) {
        for (char current : attempt.toCharArray()) {
            results.add(scoreFor(current));
            position++;
        }
    }

    private Letter scoreFor(char current) {
        if (isCorrectLetter(current)) {
            return Letter.CORRECT;
        }

        if (occursInWord(current)) {
            return Letter.PART_CORRECT;
        }

        return Letter.INCORRECT;
    }

    private boolean occursInWord(char current) {
        return correct.contains(String.valueOf(current));
    }

    private boolean isCorrectLetter(char currentLetter) {
        return correct.charAt(position) == currentLetter;
    }
}
