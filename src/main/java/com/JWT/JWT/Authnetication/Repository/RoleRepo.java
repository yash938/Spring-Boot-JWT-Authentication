package com.JWT.JWT.Authnetication.Repository;

import com.JWT.JWT.Authnetication.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByRoleName(String name);
}
