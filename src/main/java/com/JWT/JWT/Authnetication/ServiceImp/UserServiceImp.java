package com.JWT.JWT.Authnetication.ServiceImp;


import Dto.UserDto;
import com.JWT.JWT.Authnetication.Entity.Role;
import com.JWT.JWT.Authnetication.Entity.User;
import com.JWT.JWT.Authnetication.Repository.RoleRepo;
import com.JWT.JWT.Authnetication.Repository.UserRepo;
import com.JWT.JWT.Authnetication.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserDto CreateUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);


        Role role = roleRepo.findByRoleName("ROLE_USER").orElseThrow(() -> new RuntimeException("role is not found"));
        user.setRoles(List.of(role));
        User save = userRepo.save(user);


        return modelMapper.map(save,UserDto.class);
    }

    @Override
    public List<UserDto> findByName(String name) {
        userRepo.findByEmail(name).orElseThrow(()->new RuntimeException("user cannot find"));
        return null;
    }

    @Override
    public List<UserDto> getAllUser() {
        return null;
    }

    @Override
    public Optional<UserDto> deleteUser(String name) {
        return Optional.empty();
    }
}
