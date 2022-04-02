package io.srv.userservice;

import io.srv.userservice.domain.User;
import io.srv.userservice.dto.request.RoleRequestDTO;
import io.srv.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserService userService) {
		return args -> {
			userService.saveUser(new User(null, "Sourav", "srvkmr699", "srvKmr@123", new ArrayList<>()));
			userService.saveUser(new User(null, "Asif", "asif699", "asif@123", new ArrayList<>()));
			userService.saveUser(new User(null, "Niraj", "niraj699", "niraj@123", new ArrayList<>()));

			userService.saveRole(new RoleRequestDTO("manager"));
			userService.saveRole(new RoleRequestDTO("admin"));
			userService.saveRole(new RoleRequestDTO("user"));

			userService.addRoleToUser("srvkmr699", "admin");
			userService.addRoleToUser("asif699", "manager");
			userService.addRoleToUser("niraj699","user");
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
