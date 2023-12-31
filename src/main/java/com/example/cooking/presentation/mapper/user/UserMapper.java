package com.example.cooking.presentation.mapper.user;

import com.example.cooking.data.model.postgres.user.User;
import com.example.cooking.presentation.dto.user.UserCredentialsReq;
import com.example.cooking.presentation.dto.user.UserResp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toUser(UserCredentialsReq req);

    @Mapping(target = "role", source = "role.role")
    UserResp toUserResp(User user);

    List<UserResp> toUserResps(List<User> users);
}
