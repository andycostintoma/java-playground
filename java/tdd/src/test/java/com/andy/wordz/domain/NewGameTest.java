package com.andy.wordz.domain;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NewGameTest {

    public static final Player PLAYER = new Player("Andy");
    @Mock
    private GameRepository gameRepository;
    @Mock
    private WordRepository wordRepository;
    @Mock
    private RandomNumbers randomNumbers;
    @InjectMocks
    private Wordz wordz;

    @Test
    void startsNewGame() {
        givenWordToSelect("ARISE");

        wordz.newGame(PLAYER);

        var game = getGameInRepository();
        assertThat(game.getTargetWord()).isEqualTo("ARISE");
        assertThat(game.getAttemptNumber()).isZero();
        assertThat(game.getPlayer()).isSameAs(PLAYER);
    }


    private void givenWordToSelect(String wordToSelect) {
        int wordNumber = 2;
        when(randomNumbers.next(anyInt())).thenReturn(wordNumber);
        when(wordRepository.fetchWordByNumber(wordNumber)).thenReturn(wordToSelect);
    }

    private Game getGameInRepository() {
        var gameArgument = ArgumentCaptor.forClass(Game.class);
        verify(gameRepository).create(gameArgument.capture());
        return gameArgument.getValue();
    }
}
