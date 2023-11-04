package com.example.cooking.buisness.service.user;

import com.example.cooking.buisness.service.MainService;
import com.example.cooking.presentation.dto.dish.resp.DishResp;
import com.example.cooking.presentation.dto.user.UpdateUserReq;
import com.example.cooking.presentation.dto.user.UserCredentialsReq;
import com.example.cooking.presentation.dto.user.UserResp;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends MainService<UserCredentialsReq, UpdateUserReq, UserResp>, UserDetailsService {
    UserResp setRole(int idUser, int idRole);

    List<DishResp> getDishesByUserId(int idUser);
}
