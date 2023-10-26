package com.example.cooking.presentation.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserReq {
    private String login;
    private String password;
    private String name;
    private String lastname;
}
