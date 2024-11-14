package com.JWT.JWT.Authnetication.ServiceImp;


import Dto.PagebleResponse;
import Dto.RoleDto;
import Dto.UserDto;
import com.JWT.JWT.Authnetication.Entity.Role;
import com.JWT.JWT.Authnetication.Entity.User;
import com.JWT.JWT.Authnetication.Exception.UserNotFoundException;
import com.JWT.JWT.Authnetication.Repository.RoleRepo;
import com.JWT.JWT.Authnetication.Repository.UserRepo;
import com.JWT.JWT.Authnetication.Service.UserService;
import com.JWT.JWT.Authnetication.helper.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepo roleRepo;


    @Override
    public UserDto createUser(UserDto userDto) {

        User user = modelMapper.map(userDto, User.class);

        Role role = roleRepo.findByRoleName("ROLE_USER").orElseThrow(() -> new UserNotFoundException("role is not found"));
        user.setRoles(List.of(role));

        User saveUser = userRepo.save(user);
        return modelMapper.map(saveUser,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        return null;
    }

    @Override
    public Optional<UserDto> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public List<UserDto> deleteUser(Long id) {
        return null;
    }
}
