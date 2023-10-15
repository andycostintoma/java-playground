package com.andy.wordz.domain;

public class Wordz {

    private final GameRepository gameRepository;
    private final WordSelection wordSelection;

    public Wordz(GameRepository gameRepository, WordRepository wordRepository, RandomNumbers randomNumbers) {
        this.gameRepository = gameRepository;
        this.wordSelection = new WordSelection(wordRepository, randomNumbers);
    }

    public void newGame(Player player) {
        var word = wordSelection.chooseRandomWord();
        var game = Game.create(player, word);
        gameRepository.create(game);
    }

    public GuessResult assess(Player player, String guess) {
        Game game = gameRepository.fetchForPlayer(player).orElseThrow(IllegalStateException::new);

        if (game.isGameOver()) {
            return GuessResult.ERROR;
        }

        Score score = game.attempt(guess);

        gameRepository.update(game);

        return GuessResult.create(score, game.isGameOver());
    }

}
