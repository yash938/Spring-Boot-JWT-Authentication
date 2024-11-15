package com.JWT.JWT.Authnetication.Service;

import Dto.PagebleResponse;
import Dto.UserDto;
import com.JWT.JWT.Authnetication.Entity.User;

import java.util.Optional;

public interface UserService {

    public UserDto createUser(UserDto userDto);

    public UserDto updateUser(UserDto userDto,Long id);
    public Optional<UserDto> findByName(String name);

    public PagebleResponse<UserDto> getAllUser(int pageNumber,int pageSize,String sortBy,String sortDir);
    public void deleteUser(Long id);
}
