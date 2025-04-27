package com.andy.examples.dice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiceRollTest {

    @Test
    void producesMessage() {
        var stub = new StubRandomNumbers();
        var roll = new DiceRoll(stub);

        var actual = roll.asText();
        assertThat(actual).contains("You rolled a 5");
    }

}