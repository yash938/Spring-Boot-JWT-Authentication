package com.JWT.JWT.Authnetication.Repository;

import Dto.UserDto;
import com.JWT.JWT.Authnetication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    public Optional<User> findByEmail(String email);

    public Optional<UserDto> findByName(String name);

}
