package com.JWT.JWT.Authnetication.ServiceImp;


import Dto.PagebleResponse;
import Dto.UserDto;
import com.JWT.JWT.Authnetication.Entity.Role;
import com.JWT.JWT.Authnetication.Entity.User;
import com.JWT.JWT.Authnetication.Exception.UserNotFoundException;
import com.JWT.JWT.Authnetication.Repository.RoleRepo;
import com.JWT.JWT.Authnetication.Repository.UserRepo;
import com.JWT.JWT.Authnetication.Service.UserService;
import com.JWT.JWT.Authnetication.helper.Helper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepo roleRepo;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        Role role = roleRepo.findByRoleName("ROLE_USER").orElseThrow(() -> new UserNotFoundException("name is not found"));
        user.setRoles(List.of(role));



        User savedUser = userRepo.save(user);
        logger.info("User created with ID: {}", savedUser.getUserId());

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User id is not found"));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNo(userDto.getPhoneNo());

        User saveduser = userRepo.save(user);

        return modelMapper.map(saveduser,UserDto.class);
    }

    @Override
    public Optional<UserDto> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public PagebleResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> all = userRepo.findAll(pageRequest);
        PagebleResponse<UserDto> pageble = Helper.getPageble(all, UserDto.class);
        return pageble;
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }


}
