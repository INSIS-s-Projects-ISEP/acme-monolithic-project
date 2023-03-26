package com.isep.acme.domain.view.mapper;

import org.springframework.stereotype.Component;

import com.isep.acme.domain.model.User;
import com.isep.acme.domain.view.UserView;

@Component
public class UserViewMapper {

    public UserView toUserView(User user){
        UserView userView = new UserView();
        userView.setUserId(user.getUserId().toString());
        userView.setFullName(user.getFullName());
        userView.setUsername(user.getUsername());
        return userView;
    }
}
