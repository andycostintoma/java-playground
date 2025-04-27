package com.andy.examples.username;

public class InvalidUsernameException extends Exception {
    public InvalidUsernameException() {
        super("Invalid username: Name is too short");
    }
}
