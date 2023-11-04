package com.example.cooking.presentation.controller.auth;

import com.example.cooking.buisness.service.auth.AuthService;
import com.example.cooking.presentation.dto.user.UserCredentialsReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody UserCredentialsReq req) {
        return authService.login(req);
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestBody UserCredentialsReq req) {
        return authService.signIn(req);
    }


}
