package com.example.cooking.buisness.service.auth.impl;

import com.example.cooking.buisness.service.auth.AuthService;
import com.example.cooking.buisness.service.jwt.JwtService;
import com.example.cooking.buisness.service.user.UserService;
import com.example.cooking.presentation.dto.user.UserCredentialsReq;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public String login(UserCredentialsReq req) {
        User user = (User) userService.loadUserByUsername(req.getLogin());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getLogin(), req.getPassword()));
        return jwtService.generateToken(user);
    }

    @Override
    @Transactional
    public String signIn(UserCredentialsReq req) {
        userService.save(req);
        return login(req);
    }
}
