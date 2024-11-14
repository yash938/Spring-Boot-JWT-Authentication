package com.JWT.JWT.Authnetication;

import com.JWT.JWT.Authnetication.Entity.Role;
import com.JWT.JWT.Authnetication.Entity.User;
import com.JWT.JWT.Authnetication.Repository.RoleRepo;
import com.JWT.JWT.Authnetication.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JwtAuthneticationApplication implements CommandLineRunner {

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthneticationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role roleAdmin = roleRepo.findByRoleName("ROLE_ADMIN").orElse(null);
		Role roleUser = roleRepo.findByRoleName("ROLE_USER").orElse(null);

		if(roleAdmin==null){
			roleAdmin = new Role();
			roleAdmin.setRoleName("ROLE_ADMIN");
			roleRepo.save(roleAdmin);
		}

		if(roleUser == null){
			roleUser  = new Role();
			roleUser.setRoleName("ROLE_USER");
			roleRepo.save(roleUser);
		}


		User user = userRepo.findByEmail("vaibhav@gmail.com").orElse(null);

		if(user == null){
			User user1 = new User();
			user1.setEmail("vaibhav@gmail.com");
			user1.setName("vaib");
			user1.setPassword("vaibhav");
			user1.setRoles(List.of(roleUser));

			userRepo.save(user1);
			System.out.println("user saved successfully");
		}else{
			System.out.println("user is already exist");
		}
	}
}
