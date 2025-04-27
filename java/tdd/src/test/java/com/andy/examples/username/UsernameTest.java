package com.andy.examples.username;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class UsernameTest {
    @Test
    public void convertsToLowerCase() throws InvalidUsernameException {
        var username = new Username("SirJakington35179");
        String actual = username.asLowerCase();
        assertThat(actual).isEqualTo("sirjakington35179");
    }

    @Test
    public void rejectsShortName() {
        assertThatExceptionOfType(InvalidUsernameException.class)
                .isThrownBy(() -> new Username("Abc"));
    }

    @Test
    public void acceptsMinimumLengthName() {
        assertThatNoException()
                .isThrownBy(() -> new Username("Abcd"));
    }


}