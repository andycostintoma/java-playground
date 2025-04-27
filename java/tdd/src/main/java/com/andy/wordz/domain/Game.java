package com.andy.wordz.domain;

public class Game {

    private static final int MAXIMUM_NUMBER_ALLOWED_GUESSES = 5;
    private final Player player;
    private final String targetWord;
    private boolean isGameOver;
    private int attemptNumber;

    public Game(Player player, String targetWord, int attemptNumber, boolean isGameOver) {
        this.player = player;
        this.targetWord = targetWord;
        this.attemptNumber = attemptNumber;
        this.isGameOver = isGameOver;
    }

    public static Game create(Player player, String correctWord) {
        return new Game(player, correctWord, 0, false);
    }

    public static Game create(Player player, String correctWord, int attemptNumber) {
        return new Game(player, correctWord, attemptNumber, false);
    }

    public Player getPlayer() {
        return player;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public Score attempt(String latestGuess) {
        trackNumberOfAttempts();
        var target = new Word(targetWord);
        Score score = target.guess(latestGuess);

        if (score.allCorrect()) {
            end();
        }

        return score;
    }

    private void trackNumberOfAttempts() {
        attemptNumber++;

        if (attemptNumber == MAXIMUM_NUMBER_ALLOWED_GUESSES) {
            end();
        }
    }

    public void end() {
        isGameOver = true;
    }
}
