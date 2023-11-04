package com.example.cooking.exception.user;

import com.example.cooking.exception.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(Integer id) {
        super("Role with id = " + id + " not found");
    }
}
