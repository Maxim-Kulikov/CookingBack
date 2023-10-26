package com.example.cooking.presentation.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResp {
    private Integer id;
    private String login;
    private String name;
    private String lastname;
    private boolean isUserBlocked;
    private String role;

}
