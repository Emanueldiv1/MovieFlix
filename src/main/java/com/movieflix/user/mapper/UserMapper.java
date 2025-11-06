package com.movieflix.user.mapper;

import com.movieflix.user.controller.request.UserRequest;
import com.movieflix.user.controller.response.UserResponse;
import com.movieflix.user.entity.User;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequest userRequest){
        return User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public static UserResponse toUserReponse(User user){
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

}
