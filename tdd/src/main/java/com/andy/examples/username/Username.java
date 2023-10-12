package com.andy.examples.username;


public final class Username {
    private final String username;

    public Username(String username) throws InvalidUsernameException {
        if (username.length() < 4) {
            throw new InvalidUsernameException();
        }
        this.username = username;
    }

    public String asLowerCase() {
        return username.toLowerCase();
    }
}
