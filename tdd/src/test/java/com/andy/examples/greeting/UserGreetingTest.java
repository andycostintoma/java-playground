package com.andy.examples.greeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserGreetingTest {

    private static final UserId USER_ID = new UserId(1234);

    @Mock
    private UserProfiles profiles;

    @Test
    void formatsGreetingWithName() {
        var greeting = new UserGreeting(profiles);

        // stubbing with Mockito
        when(profiles.fetchNicknameFor(USER_ID)).thenReturn("Alan");

        String actual = greeting.formatGreeting(USER_ID);

        assertThat(actual).isEqualTo("Hello and Welcome, Alan");
    }

    @Test
    void formatsGreetingWithNameUsingArgumentMatchers() {
        var greeting = new UserGreeting(profiles);

        // stubbing with Mockito using ArgumentMatchers
        when(profiles.fetchNicknameFor(any())).thenReturn("Alan");

        String actual = greeting.formatGreeting(new UserId(anyLong()));

        assertThat(actual).isEqualTo("Hello and Welcome, Alan");
    }

}