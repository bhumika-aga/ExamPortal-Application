package com.examportal;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.examportal.model.Role;
import com.examportal.model.User;
import com.examportal.model.UserRole;
import com.examportal.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Exam Portal Application!");

		User user = new User();
		user.setFirstName("Bhumika");
		user.setLastName("Agarwal");
		user.setUsername("bhumika");
		user.setPassword(passwordEncoder.encode("bhumika"));
		user.setEmail("bhumika.aga@gmail.com");
		user.setPhone("9876543210");
		user.setProfile("default.png");

		Role role = new Role();
		role.setRoleId(44L);
		role.setRoleName("ADMIN");

		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(userRole);

		User user1 = userService.createUser(user, userRoles);
		System.out.println(user1);
	}
}