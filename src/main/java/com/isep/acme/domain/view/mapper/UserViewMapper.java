package com.isep.acme.domain.view.mapper;

import org.mapstruct.Mapper;

import com.isep.acme.domain.model.User;
import com.isep.acme.domain.view.UserView;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);
}
