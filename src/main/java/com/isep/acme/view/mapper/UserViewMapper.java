package com.isep.acme.view.mapper;

import org.mapstruct.Mapper;

import com.isep.acme.model.User;
import com.isep.acme.view.UserView;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);
}
