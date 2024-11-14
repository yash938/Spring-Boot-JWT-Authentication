package com.JWT.JWT.Authnetication.Service;

import Dto.UserDto;
import com.JWT.JWT.Authnetication.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserDto CreateUser(UserDto user);
    public List<UserDto> findByName(String name);
    public List<UserDto> getAllUser();

    public Optional<UserDto> deleteUser(String name);
}
