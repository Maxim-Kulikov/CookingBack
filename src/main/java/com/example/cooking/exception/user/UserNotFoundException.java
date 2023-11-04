package com.example.cooking.exception.user;

import com.example.cooking.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(Integer id) {
        super("User with id = " + id + " not found");
    }

    public UserNotFoundException(String login) {super("User with login " + login + " not found");}
}
