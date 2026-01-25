package com.example.demoProject.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found exception " + id);
    }
}
