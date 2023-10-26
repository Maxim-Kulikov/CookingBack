package com.example.cooking.exception.user;

import com.example.cooking.exception.IsExistedException;

public class UserByLoginIsExistedException extends IsExistedException {
    public UserByLoginIsExistedException(String login) {
        super("User with login " + login + " is existed");
    }
}
