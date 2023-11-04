package com.example.cooking.buisness.service.auth;

import com.example.cooking.presentation.dto.user.UserCredentialsReq;

public interface AuthService {
    String login(UserCredentialsReq req);

    String signIn(UserCredentialsReq req);
}
