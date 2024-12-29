package com.JWT.JWT.Authnetication.Service;

import com.JWT.JWT.Authnetication.Dto.PagebleResponse;
import com.JWT.JWT.Authnetication.Dto.UserDto;

import java.util.Optional;

public interface UserService {

    public UserDto createUser(UserDto userDto);

    public UserDto updateUser(UserDto userDto,Long id);
    public Optional<UserDto> findByName(String name);

    public PagebleResponse<UserDto> getAllUser(int pageNumber,int pageSize,String sortBy,String sortDir);
    public void deleteUser(Long id);
}
